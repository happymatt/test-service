package com.jhqc.pxsj.shopsign.part;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jhqc.pxsj.shopsign.common.Constants;
import com.jhqc.pxsj.shopsign.common.ErrorInfo;
import com.jhqc.pxsj.shopsign.common.PackageReq;
import com.jhqc.pxsj.shopsign.common.PackageResp;
import com.jhqc.pxsj.shopsign.domain.BuildingFileContent;
import com.jhqc.pxsj.shopsign.domain.BuildingInfo;
import com.jhqc.pxsj.shopsign.domain.BuildingShop;
import com.jhqc.pxsj.shopsign.domain.ShopInfo;
import com.jhqc.pxsj.shopsign.util.SignHttpUtil;

/**
 * 解析资源文件并打图集
 */
public class PackageShopSignsMain {

	private static ObjectMapper objectMapper = new ObjectMapper();
	
	private static Logger logger = null;
	// 请求参数常量预组装
	private static Map<String, Object> jsonObjectMap = Maps.newHashMap();
	
	private static final String qingUrl = "http://artistwork.pek3a.qingstor.com/atlas";
	
	private static final String awsUrl = "http://s3.cn-north-1.amazonaws.com.cn/businessresandroid/shop/atlas";

	static {
		System.setProperty("packageVersion", Constants.PACKAGE_VERSION);
		logger = LoggerFactory.getLogger(PackageShopSignsMain.class);
		
		jsonObjectMap.put("type", Constants.PACKAGE_TYPE);
		jsonObjectMap.put("city", Constants.CITY_ID);
		jsonObjectMap.put("bundleCrc", Constants.PACKAGE_VERSION);
		jsonObjectMap.put("platforms",Constants.PLATFORMS);
	}
	//待处理的部分建筑id
	private static List<String> bIds = Lists.newArrayList();
	static {
		File file = new File(Constants.BUILDING_IDS);
		try {
		BufferedReader fr = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = fr.readLine()) != null) {
			System.out.println(line);
			bIds.add(line);
		}
		fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static int c1 = 0;
	static int c2 = 0;
	static int c3 = 0;
	private static int count = 0;
	public static void main(String[] args) {
		logger.info("图集打包处理开始......");
		StopWatch sw = new StopWatch("打图集任务" + Constants.PACKAGE_VERSION);
		sw.start("解析资源元数据");
		// 解析原资源数据为对象列表
		List<BuildingShop> buildingShops = parseResourceToList(Constants.RESOURCE_PATH_DIR);
		
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		sw.stop();
		logger.info("总建筑数量为{},解析数据总共耗时{}毫秒，需要打图集的建筑{}个（排除店招图片为空的数据）", count, sw.getLastTaskTimeMillis(),buildingShops.size());
		// 构建打包请求列表
		sw.start("构建打图集请求列表");
		
		List<PackageReq> reqs = createPackageSignsReq(buildingShops);
		sw.stop();
		logger.info("构造了{}个打图集请求，耗时{}毫秒", reqs.size(), (sw.getLastTaskTimeMillis()));
		
		sw.start("正式发送图集打包请求");
		// 正式发送打包请求
		List<ErrorInfo> errorList = batchPackageSignAndWriteTilUrlToFile(reqs);
		sw.stop();
		logger.info("发送图集打包请求完毕，耗时:{}毫秒", sw.getLastTaskTimeMillis());
		if (errorList.size() > 0) {
			logger.warn("有{}个需要重新打", errorList.size());
			// 产生异常需要重新打的数据信息
			for (ErrorInfo ei : errorList) {
				logger.info("产生异常的buildingId:{}，msg:{}", ei.getReq().getBuildingId(), ei.getMsg());
			}
		}
		logger.info(sw.prettyPrint());
	}

	/**
	 * 解析资源数据，拼接正确的打图集请求。
	 * 
	 * @param resourcePath
	 */
	private static List<BuildingShop> parseResourceToList(String resourcePath) {
		List<BuildingShop> list = Lists.newArrayList();
		try {
			File file = new File(resourcePath);
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File f : files) {
					boolean tempStatus =
							f.getName().endsWith(".txt") && 
							!f.getName().contains("delete") && 
							!f.getName().equals("bounds.txt") && 
							!f.getName().contains("street");
					if (tempStatus) {
						BuildingFileContent fc = objectMapper.readValue(f,BuildingFileContent.class);
						list.addAll(createBuildingShopList(fc));
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		HashSet<String> set = Sets.newHashSet();

		for (BuildingShop bs : list) {
			if (bs.getShopInfos().size() > 0) {
				set.add(bs.getBuildingId());
			}
		}
		if (set.size() != list.size()) {
			throw new RuntimeException("list中数据有重复建筑id或对应建筑下图集信息为空，list.size=" + list.size() + "，set.size()=" + set.size());
		}
		return list;
	}

	
	private static List<BuildingShop> createBuildingShopList(BuildingFileContent fc) {
		List<BuildingShop> list = Lists.newArrayList();
		for (BuildingInfo bi : fc.getBuildings()) {
			if (bi.getGuid().equals("e133d601-22b7-42bc-8f03-818ff024a255")) {
				System.out.println(fc.getBuildings());
			}
			if (bIds.contains(bi.getGuid())) {
				c1 ++;
				List<ShopInfo> infos = getShopInfosByBuildingId(bi.getGuid(), fc);
				if (infos.size() > 0) {
					c3 ++;
					list.add(new BuildingShop(bi.getGuid(), infos));
				}
				else {
					System.out.println(bi.getGuid() + "============");
					System.out.println(fc);
				}
			}
			else {
				c2 ++;
			}
		}
		return list;
	}

	private static List<ShopInfo> getShopInfosByBuildingId(String buildingId, BuildingFileContent fc) {
		List<ShopInfo> list = Lists.newArrayList();
		for (ShopInfo si : fc.getShop_signs()) {
			if (si.getBuildingId().equals(buildingId)) {
				if (StringUtils.isNotBlank(si.getPicUrl())){
					list.add(si);
				}
				else {
					System.out.println(si.getBuildingId());
				}
			}
		}
		return list;
	}

	private static List<PackageReq> createPackageSignsReq(List<BuildingShop> list) {
		List<PackageReq> result = Lists.newArrayList();
		for (BuildingShop bs : list) {
			PackageReq req = new PackageReq();
			req.setBuildingId(bs.getBuildingId());
			req.setUrl(parseToReqUrl(bs));
			result.add(req);
		}
		return result;

	}

	private static List<ErrorInfo> batchPackageSignAndWriteTilUrlToFile(List<PackageReq> reqs) {
		File tilsDir = null;
		BufferedWriter bw = null;
		try {
			tilsDir = new File(Constants.TIL_DIR + Constants.PACKAGE_VERSION + ".txt");
			bw = new BufferedWriter(new FileWriter(tilsDir));
		} catch (Exception e) {
			throw new RuntimeException("创建写文件出错", e);
		}

		List<ErrorInfo> errorList = Lists.newArrayList();
		int sc = 0;
		
		// 循环打图集
		for (PackageReq req : reqs) {
			try {
				String resp = doPackageShopSign(req);
				PackageResp pr = objectMapper.readValue(resp, PackageResp.class);
				if (pr != null && pr.getStatus().equals("success")) {
					String url = String.format("%s/%s_%s.til", awsUrl, req.getBuildingId(), Constants.PACKAGE_VERSION);
					bw.write(url + "\n");
					bw.flush();
					sc++;
					System.out.println("成功发送第" + sc + "个打图集请求。");
				} else {
					errorList.add(new ErrorInfo(req, "请求结果为null或请求失败"));
					logger.error("请求结果为null或请求失败,resp:{},bulidignId:{}", resp, req.getBuildingId());
				}
			} catch (Exception e) {
				errorList.add(new ErrorInfo(req, e.getMessage()));
				logger.error(e.getMessage(), e);
			}
		}
		try {
			bw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		logger.info("成功打完{}个图集", sc);
		return errorList;
	}

	private static String doPackageShopSign(PackageReq req) throws Exception {
		try {
			System.out.println(req.getUrl());
			
			String mm = SignHttpUtil.doGetSign(req.getUrl());
			// String mm = "{\"name\":\"test\",\"status\":\"success\"}";
			
			return mm;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		

	}

	public static String parseToReqUrl(BuildingShop bs) {
		JSONObject jo = new JSONObject(jsonObjectMap);
		jo.put("name", bs.getBuildingId());
		jo.put("bundleName", bs.getBuildingId());
		JSONObject joDepends = new JSONObject();
		for (ShopInfo shopInfo : bs.getShopInfos()) {
			String url = shopInfo.getPicUrl();
			String name = "";
			if (url.contains("jhtest")) {
				name = url.substring(url.lastIndexOf("/") + 1) + ".jpg";
			} else {
				String imgEnd = url.substring(url.lastIndexOf("_") + 1);
				name = url.substring(url.lastIndexOf("/") + 1,url.lastIndexOf(".")) + "_" + imgEnd + ".jpg";
			}
			joDepends.put(name, url);
		}
		jo.put("depends", joDepends);
		String result = jo.toJSONString().replace("\"", "%22").replace("{", "%7b").replace("}", "%7d");
		return Constants.PACKAGE_URL + "?tag=" + result;
	}

}

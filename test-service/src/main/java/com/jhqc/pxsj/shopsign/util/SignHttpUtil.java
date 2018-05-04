package com.jhqc.pxsj.shopsign.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

/**
 * @author HuChao restful接口调用工具
 */
public class SignHttpUtil {

	private static int count = 0;

	public static void main2(String[] args) {
		// doPost2("http://localhost:8310/v1/user/saveUser",null);
		int length = 1000;
		ExecutorService es = Executors.newFixedThreadPool(length);
		for (int i = 0; i < length; i++) {
			es.execute(new Runnable() {
				@Override
				public void run() {
					doGet("http://localhost:8302/v1/shop/queryShopByShopId?shopId=24");
					System.out.println("========count:" + (++count));
					/*
					 * if(count % 3 != 5) doGet(
					 * "http://139.198.2.65:10302/v1/shop/queryShopByShopId?shopId=24"
					 * ); else if(count % 3 == 1) doGet(
					 * "http://139.198.2.65:10302/v1/shop/queryShopByShopCode?shopCode=123123"
					 * ); else doGet(
					 * "http://139.198.2.65:10305/v1/userVcity/queryUserVcityByUserId?userId=111"
					 * );
					 */
				}
			});
		}

		es.shutdown();
		while (true) {
			if (es.isTerminated()) {
				System.out
						.println("<<<<<<<<<<<all threads finished>>>>>>>>>>>");
				break;
			}
		}

	}

	public static String doGetSign(String url) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httpget.
		HttpGet httpget = new HttpGet(url);
		// System.out.println("executing get request " + httpget.getURI());
		// 执行get请求.
		CloseableHttpResponse response = httpclient.execute(httpget);
		// 获取响应实体
		HttpEntity entity = response.getEntity();
		// System.out.println("--------------------------------------");
		StatusLine statusLine = response.getStatusLine();
		if (statusLine.getStatusCode() == 200) {
			return EntityUtils.toString(entity);
		}
		System.out.println(statusLine);
		return null;
	}

	public static String doGet(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			// System.out.println("executing get request " + httpget.getURI());
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				// System.out.println("--------------------------------------");
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == 200) {
					return EntityUtils.toString(entity);
				} else {
					System.out.println(statusLine);
					return null;
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public static String doPost(String url, String jsonStr) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httppost.
			HttpPost httpPost = new HttpPost(url);
			System.out.println("executing post request " + httpPost.getURI());
			httpPost.setHeader("Content-Type",
					"application/json; charset=utf-8");
			if (StringUtils.isNotBlank(jsonStr))
				httpPost.setEntity(new StringEntity(jsonStr, Charset
						.forName("UTF-8")));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				System.out.println("--------------------------------------");
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == 200) {
					String contentString = EntityUtils.toString(entity);
					return contentString;
				} else {
					System.out.println(statusLine);
					return null;
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public static String doPost2(String url, Map<String, String> map) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httppost.
			HttpPost httpPost = new HttpPost(url);
			System.out.println("executing request " + httpPost.getURI());
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			for (Entry<String, String> entry : map.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
			// nvps.add(new BasicNameValuePair("name", "aobama"));
			// nvps.add(new BasicNameValuePair("id","123"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			// httpPost.setHeader("Content-Type", "text/html; charset=utf-8");
			/*
			 * httpPost.setEntity(new StringEntity(jsonStr,
			 * Charset.forName("UTF-8")));
			 */
			/* httpPost.setEntity(new BasicNameValuePair("", )); */
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				System.out.println("--------------------------------------");
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == 200) {
					String contentString = EntityUtils.toString(entity);
					return contentString;
				} else {
					System.out.println(statusLine);
					return null;
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	// 使用POST方法发送XML数据
	public static String sendXMLDataByPost(String url, String xmlData) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		parameters.add(new BasicNameValuePair("xml", xmlData));
		try {
			post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
			CloseableHttpResponse response = httpclient.execute(post);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == 200) {
					String contentString = EntityUtils.toString(entity);
					return contentString;
				} else {
					return null;
				}
			} finally {
				response.close();
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static String buildQuerys(Map<String, Object> params) {
		if (params == null || params.size() == 0)
			return "";
		StringBuilder sb = new StringBuilder("?");
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (entry.getValue() != null)
				sb.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
		sb.deleteCharAt(sb.lastIndexOf("&"));
		return sb.toString();
	}

	public static void main(String[] args) {
		StringBuilder url = new StringBuilder(
				"http://139.198.2.58:8000/pack/soa_pack");
		// ObjectMapper objectMapper = new ObjectMapper();
		try {

			// String jsonContent2 = objectMapper.writeValueAsString(new
			// AssetBundleReq());
			String jsonContent = objToJson(new AssetBundleReq());
			System.out.println(jsonContent);
			String str = jsonContent.replace("\"", "%22").replace("{", "%7b")
					.replace("}", "%7d");
			// jsonContent
			// ="{\"name\": \"guid_name\", \"type\": \"shop\", \"city\": \"0280\",\"bundleCrc\":132344,\"returnUrl\":\"http://callback_url/interface\",\"bundleName\": \"guid_name\", \"platforms\": [\"android\", \"windows\"], \"depends\": [ \"1234567.png\":\"http://artistwork.pek3a.qingstor.com/filecrc/1234567.png\", \"7654321.png\":\"http://artistwork.pek3a.qingstor.com/filecrc/7654321.png' ] }";
			// jsonContent
			// ="{name:guid_name, type: shop, city:0280,bundleCrc:132344,returnUrl:http://callback_url/interface,bundleName: guid_name, platforms: [android,windows],depends: [1234567.png:http://artistwork.pek3a.qingstor.com/filecrc/1234567.png,7654321.png:http://artistwork.pek3a.qingstor.com/filecrc/7654321.png ] }";
			url.append("?tag=").append(str);
			System.out.println(str);
			String mm = doGet(url.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sdf.format(new Date()) + "=>" + mm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String objToJson(AssetBundleReq req) {
		JSONObject jo = new JSONObject();
		jo.put("name", req.getName());
		jo.put("type", req.getType());
		jo.put("city", req.getCity());
		jo.put("bundleCrc", req.getBundleCrc());
		jo.put("bundleName", req.getBundleName());
		jo.put("platforms", req.getPlatforms());
		String jsonStr = jo.toJSONString();

		/*
		 * jo.put("depends", createDepends(req.getDepends()).replace("\"", ""));
		 * return jo.toJSONString();
		 */
		return addDepends(jsonStr, req.getDepends());
	}

	private static String quotationMark = "\"";

	private static String addDepends(String jsonStr, List<String> depends) {
		String result = jsonStr.replace("}", "");
		StringBuilder sb = new StringBuilder(result);
		sb.append(",").append("\"depends\"").append(":").append("{");
		for (String url : depends) {
			String name = url.substring(url.lastIndexOf("/") + 1) + ".jpg";
			sb.append(quotationMark).append(name).append(quotationMark)
					.append(":").append(quotationMark).append(url)
					.append(quotationMark).append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("}").append("}");
		return sb.toString();
	}

}

class AssetBundleReq {

	private String name = "028001001a01"; // 该tag的唯一标识, user 端可以作为pc order
	private String type = "shop";// 处理类型
	private String city = "0280";// 城市ID
	private String bundleCrc = "9999";// 版本号
	private String bundleName = "028001001a01"; // 打包后bundle文件名
	private List<String> platforms = Lists.newArrayList("android", "windows");// 请求的需要打包的平台
	/*
	 * private List<String> depends = Lists.newArrayList(
	 * "https://city-source.pek3a.qingstor.com//5865156fe9223GgfLuQMZgR.jpg",
	 * "https://city-source.pek3a.qingstor.com//586b156527d18I2eWgGNw1R.jpg");
	 */
	/*
	 * private List<String> depends =
	 * Lists.newArrayList("http://artistwork.pek3a.qingstor.com/1.jpg",
	 * "http://artistwork.pek3a.qingstor.com/2.jpg",
	 * "http://artistwork.pek3a.qingstor.com/3.jpg",
	 * "http://artistwork.pek3a.qingstor.com/4.jpg");
	 */
	private List<String> depends = Lists.newArrayList(
			"http://jhtest.pek3a.qingstor.com/artist_work/1474858427334",
			"http://jhtest.pek3a.qingstor.com/artist_work/1474858427334");
	private ImageInfo imageInfo = new ImageInfo("123", "http://123.com");

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBundleCrc() {
		return bundleCrc;
	}

	public void setBundleCrc(String bundleCrc) {
		this.bundleCrc = bundleCrc;
	}

	public String getBundleName() {
		return bundleName;
	}

	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}

	public List<String> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<String> platforms) {
		this.platforms = platforms;
	}

	public List<String> getDepends() {
		return depends;
	}

	public void setDepends(List<String> depends) {
		this.depends = depends;
	}

	public ImageInfo getImageInfo() {
		return imageInfo;
	}

	public void setImageInfo(ImageInfo imageInfo) {
		this.imageInfo = imageInfo;
	}

}

class ImageInfo {

	private String name;
	private String url;

	public ImageInfo() {
	}

	public ImageInfo(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

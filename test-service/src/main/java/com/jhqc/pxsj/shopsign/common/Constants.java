package com.jhqc.pxsj.shopsign.common;

import java.util.List;

import com.google.common.collect.Lists;

public class Constants {
	
	
	//资源包名，前几位为城市id,后几位作为版本号0875_20180112110844，0280_20171117212522
	public static final String RESOURCE_PACKAGE_NAME = "0772_20180428153719";
	
	//城市id，打不同的城市需要手动更新
	public static final String CITY_ID = RESOURCE_PACKAGE_NAME.split("_")[0];
	
	//打图集版本号，使用资源包名后缀
	public static final String PACKAGE_VERSION = RESOURCE_PACKAGE_NAME.split("_")[1];
	
	//资源本地路径
	public static final String RESOURCE_PATH_DIR = "F:\\shopsign\\tars\\" + CITY_ID + "\\" + RESOURCE_PACKAGE_NAME + "\\output\\";
	
	//图集url信息存放文件夹
	public static final String TIL_DIR = "F:\\shopsign\\tilurls\\" + CITY_ID + "\\";
	
	public static final String BUILDING_IDS = "F:\\shopsign\\buildings\\ids.txt";
	//图集til下载失败的建筑id
	public static final String TIL_DOWNLOAD_ERROR = "F:\\shopsign\\downloaderror\\"+ CITY_ID + "\\";
		
	//图集til下载文件内容存放文件夹
	public static final String TIL_DATA_DIR = "F:\\shopsign\\tilsData\\" + CITY_ID + "\\" + PACKAGE_VERSION + "\\";
	
	//打图集平台android
	public static final String PLATFORM_ANDROID = "android";
	
	//打图集平台ios
	public static final String PLATFORM_IOS = "ios";
	
	//打图集平台windows
	public static final String PLATFORM_WINDOWS = "windows";
	
	//打图集平台webgl
	public static final String PLATFORM_WEBGL = "webgl";
	
	public static final List<String> PLATFORMS = Lists.newArrayList(
			PLATFORM_ANDROID, PLATFORM_IOS,PLATFORM_WINDOWS/*,PLATFORM_WEBGL*/ );
	
	//打图集接口地址
	public static final String PACKAGE_URL = "http://139.198.2.58:8000/pack/soa_pack";
	
	//打图集类型
	public static final String PACKAGE_TYPE = "shop";

}

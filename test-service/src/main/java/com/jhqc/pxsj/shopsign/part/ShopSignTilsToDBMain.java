package com.jhqc.pxsj.shopsign.part;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhqc.pxsj.shopsign.common.Constants;
import com.jhqc.pxsj.shopsign.common.MyDataSourceConfig;
import com.jhqc.pxsj.shopsign.domain.TilData;


/**
 * 解析打图集til信息入库
 */
public class ShopSignTilsToDBMain {
	
	
	public static ObjectMapper mapper = new ObjectMapper();
	private static int scount =  0;
	private static int fcount =  0;
	private static JdbcTemplate jtm = new JdbcTemplate(getDataSource());
	public static void main(String[] args) throws IOException, InterruptedException {
		long cs = System.currentTimeMillis();
		File file = new File(Constants.TIL_DATA_DIR);
		for (File fl : file.listFiles()) {
			BufferedReader br = new BufferedReader(new FileReader(fl));
			StringBuilder sb = new StringBuilder();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			String buildingId = fl.getName().substring(0,fl.getName().lastIndexOf("_"));
			String version = fl.getName().substring(fl.getName().lastIndexOf("_") + 1,fl.getName().lastIndexOf("."));
			TilData td = mapper.readValue(fl, TilData.class);
			
			int m = update(buildingId, td, version);
			Thread.sleep(10);
			if (m > 0) {
				scount ++ ;
				if (scount % 200 == 0) {
					System.out.println("成功导入第" + scount + "条，buildingCode:" + buildingId);
				}
			} else {
				fcount ++ ;
				System.out.println("导入失败第" + fcount + "条，buildingCode:" + buildingId);
			}
			br.close();
		}
		
		System.out.println(String.format("导入数据库总计耗时%s毫秒，成功导入%s条，导入失败%s条", (System.currentTimeMillis() - cs), scount, fcount));
		
	}


	private static int update(String buildingId, TilData td, String version) throws JsonProcessingException {
	    String bss = "{ \"atlas\" :" + mapper.writeValueAsString(td.getAtlas()) + "}";
	    String sql = "update vcity_shop.t_d_building set building_sign_set = ?, bundle_crc = ? ,update_time = now() where building_code = ?";
	   
	    int result = jtm.update(sql,bss,version,buildingId);
	    return result;
	}

	private static DataSource getDataSource() {

		DataSource pool = new DataSource();
		MyDataSourceConfig config = new MyDataSourceConfig();
		pool.setDriverClassName(config.getDriverClassName());
		pool.setUrl(config.getUrl());
		if (config.getUsername() != null) {
			pool.setUsername(config.getUsername());
		}
		if (config.getPassword() != null) {
			pool.setPassword(config.getPassword());
		}
		// this.pool.setDefaultCatalog(config.getCatalog());
		pool.setDefaultAutoCommit(config.isDefaultAutoCommit());
		pool.setInitialSize(config.getInitialSize());
		pool.setMaxActive(config.getMaxActive());
		pool.setMaxIdle(config.getMaxIdle());
		pool.setMinIdle(config.getMinIdle());
		pool.setTestOnBorrow(config.isTestOnBorrow());
		pool.setTestOnReturn(config.isTestOnReturn());
		pool.setTestWhileIdle(config.isTestWhileIdle());
		pool.setValidationQuery(config.getValidationQuery());
		pool.setValidationInterval(config.getValidationInterval());
		pool.setTimeBetweenEvictionRunsMillis(config
				.getTimeBetweenEvictionRunsMillis());
		pool.setMinEvictableIdleTimeMillis(config
				.getMinEvictableIdleTimeMillis());
		pool.setNumTestsPerEvictionRun(config.getNumTestsPerEvictionRun());
		pool.setRemoveAbandoned(config.isRemoveAbandoned());
		pool.setRemoveAbandonedTimeout(config.getRemoveAbandonedTimeout());
		pool.setLogAbandoned(config.isLogAbandoned());
		pool.setDefaultTransactionIsolation(config
				.getDefaultTransactionIsolation());
		// this.pool.setJdbcInterceptors(config.getJdbcInterceptors());
		return pool;

	}

}

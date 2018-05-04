package com.jhqc.pxsj.shopsign;

import java.io.IOException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jhqc.pxsj.common.MyDataSourceConfig;


/**
 * 解析打图集til信息入库
 */
public class DataSourceTest {
	
	
	private static JdbcTemplate jtm = new JdbcTemplate(getDataSource());
	public static void main(String[] args) throws IOException, InterruptedException {
		String shopName = getShopName(300L);
		System.out.println(shopName);
	}

	
	private static String getShopName(long shopId) {
		String sql = "select shop_name from vcity_shop.t_d_shop where shop_id = ?";
		String ret = jtm.queryForObject(sql, String.class, shopId);
		return ret;
	}
	
	

	private static DataSource getDataSource() {

		DataSource dataSource = new DataSource();
		MyDataSourceConfig config = new MyDataSourceConfig();
		dataSource.setDriverClassName(config.getDriverClassName());
		dataSource.setUrl(config.getUrl());
		dataSource.setUsername(config.getUsername());
		dataSource.setPassword(config.getPassword());
		// this.dataSource.setDefaultCatalog(config.getCatalog());
		dataSource.setDefaultAutoCommit(config.isDefaultAutoCommit());
		dataSource.setInitialSize(config.getInitialSize());
		dataSource.setMaxActive(config.getMaxActive());
		dataSource.setMaxIdle(config.getMaxIdle());
		dataSource.setMinIdle(config.getMinIdle());
		dataSource.setTestOnBorrow(config.isTestOnBorrow());
		dataSource.setTestOnReturn(config.isTestOnReturn());
		dataSource.setTestWhileIdle(config.isTestWhileIdle());
		dataSource.setValidationQuery(config.getValidationQuery());
		dataSource.setValidationInterval(config.getValidationInterval());
		dataSource.setTimeBetweenEvictionRunsMillis(config
				.getTimeBetweenEvictionRunsMillis());
		dataSource.setMinEvictableIdleTimeMillis(config
				.getMinEvictableIdleTimeMillis());
		dataSource.setNumTestsPerEvictionRun(config.getNumTestsPerEvictionRun());
		dataSource.setRemoveAbandoned(config.isRemoveAbandoned());
		dataSource.setRemoveAbandonedTimeout(config.getRemoveAbandonedTimeout());
		dataSource.setLogAbandoned(config.isLogAbandoned());
		dataSource.setDefaultTransactionIsolation(config.getDefaultTransactionIsolation());
		// this.dataSource.setJdbcInterceptors(config.getJdbcInterceptors());
		return dataSource;

	}

}

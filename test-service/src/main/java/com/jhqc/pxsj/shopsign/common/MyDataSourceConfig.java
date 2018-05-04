package com.jhqc.pxsj.shopsign.common;

import com.jhqc.pxsj.shopsign.encrypt.PxsjDecrypt;

public class MyDataSourceConfig {
    
    private String driverClassName ="com.mysql.jdbc.Driver";
    public final static String DS="mysqldb.datasource"; 
    
    //对应配置文件里的配置键
    private String url = "jdbc:mysql://192.168.31.246:3306/vcity_shop?characterEncoding=utf8&noAccessToProcedureBodies=true&tcpRcvBuf=1024000&autoReconnect=true"; 
    private String username = "pxsjuser";
    private String password = "pxsj_123";
    private int maxActive = 100;
    private int maxIdle = 30;
    private int minIdle = 10;
    private int initialSize = 10;
    
  //private boolean defaultReadOnly = false;
    private boolean defaultAutoCommit = true;
    private String validationQuery = "select 1";//<!-- 验证连接是否可用，使用的SQL语句 -->
    private boolean testWhileIdle = true;//指明连接是否被空闲连接回收器(如果有)进行检验.如果检测失败,则连接将被从池中去除.
    private boolean testOnBorrow = true;//<!-- 借出连接时不要测试，否则很影响性能 -->
    private boolean testOnReturn = true;
    
    private int validationInterval = 10000;
    private int timeBetweenEvictionRunsMillis = 30000;//<!-- 每60秒运行一次空闲连接回收器 -->
    private int minEvictableIdleTimeMillis = 30000;//<!-- 池中的连接空闲30分钟后被回收,默认值就是30分钟 -->
    private int numTestsPerEvictionRun = 30;//<!-- 在每次空闲连接回收器线程(如果有)运行时检查的连接数量，默认值是3. -->
    private boolean removeAbandoned = true;
    private int removeAbandonedTimeout = 60;
    private boolean logAbandoned = true;
    private int defaultTransactionIsolation = 2;
    


	public boolean isDefaultAutoCommit() {
		return defaultAutoCommit;
	}

	public void setDefaultAutoCommit(boolean defaultAutoCommit) {
		this.defaultAutoCommit = defaultAutoCommit;
	}

	public int getValidationInterval() {
		return validationInterval;
	}

	public void setValidationInterval(int validationInterval) {
		this.validationInterval = validationInterval;
	}

	public boolean isRemoveAbandoned() {
		return removeAbandoned;
	}

	public void setRemoveAbandoned(boolean removeAbandoned) {
		this.removeAbandoned = removeAbandoned;
	}

	public int getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}

	public void setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	public boolean isLogAbandoned() {
		return logAbandoned;
	}

	public void setLogAbandoned(boolean logAbandoned) {
		this.logAbandoned = logAbandoned;
	}

	public int getDefaultTransactionIsolation() {
		return defaultTransactionIsolation;
	}

	public void setDefaultTransactionIsolation(int defaultTransactionIsolation) {
		this.defaultTransactionIsolation = defaultTransactionIsolation;
	}

	public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    //TODO 应该直接把参数配置在url上
    public String getUrl() {
    	url = PxsjDecrypt.decrypt(url);
    	if(url != null && url.contains("?")) url = url +"&useSSL=false";
    	return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return PxsjDecrypt.decrypt(username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return PxsjDecrypt.decrypt(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }
}
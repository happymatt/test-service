package com.jhqc.pxsj;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
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
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.tags.EscapeBodyTag;


/**
 * @author HuChao
 * restful接口调用工具
 */
public class HttpUtil {

    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);
    
    private static int count = 200;
    static ExecutorService es = Executors.newFixedThreadPool(count);
    
    static String url = "http://localhost:8302/v1/shop/queryShopByShopId?shopId=300";
    //static String url = "http://localhost:8361/noauth/v1/user/system/checkVersion";
    private static int sCount = 0;
    private static int fCount = 0;
    public static void main(String[] args) throws InterruptedException {
    	while (true) {
			Thread.sleep(1);
			for (int i = 0; i < count; i++) {
				es.execute(() -> {
					String ret = doGet(url,3000);
					if (ret != null) {
						sCount ++;
					} else {
						fCount ++;
					}
				});
			}
		}
		

	}
    
    /**
     * http调用默认超时时间为3秒
     */
    private final static Integer DEFAULT_TIME_OUT = 3000;
    
    /***
     * 使用http client GET请求<p>
     * @param url
     * @return <p>
     * String
     */
    public static String doGet(String url){
        return doGet(url , DEFAULT_TIME_OUT);
    }
    
    public static String doPost(String url, String json) {
    	return doPost(url, json, DEFAULT_TIME_OUT);
    }

    /***
     * 方法 修改 2017-11-05 增加超时响应时间<p>
     * @param url
     * @param timeOut
     * @return <p>
     * String
     */
	public static String doGet(String url, Integer timeOut){
	    
	    log.info("get请求:" + url);
	    
	    HttpClientBuilder httpClientBuilder = HttpClients.custom();
	    if(timeOut != null){
	        httpClientBuilder.setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(timeOut).build());
	    }
	    CloseableHttpClient httpclient = httpClientBuilder.build();
	    
        try {  
            // 创建httpget.    
            HttpGet httpget = new HttpGet(url);  
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {  
            	 // 获取响应实体    
                HttpEntity entity = response.getEntity();  
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == 200){
                	return EntityUtils.toString(entity);
                }
                else{
                    log.info("请求异常 返回:" + statusLine);
                	return null;
                } 
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
        	throw new RuntimeException(e.getMessage());
        }  catch (IOException e) {  
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
	public static String doPost(String url, String jsonStr, Integer timeOut){
	    log.info("post请求:" + url + "  ;post请求参数:" + jsonStr);
	    
	    HttpClientBuilder httpClientBuilder = HttpClients.custom();
	    if (timeOut != null){
	        httpClientBuilder.setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(timeOut).build());
	    }
	    CloseableHttpClient httpclient = httpClientBuilder.build(); 
		
        try {  
            // 创建httppost.    
            HttpPost httpPost = new HttpPost(url);  
            httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
            if(StringUtils.isNotBlank(jsonStr))
            	httpPost.setEntity(new StringEntity(jsonStr, Charset.forName("UTF-8")));
            CloseableHttpResponse response = httpclient.execute(httpPost);  
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();  
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == 200){
                	String contentString =EntityUtils.toString(entity);
                	return contentString;
                }
                else{
                    log.info("请求异常 返回:" + statusLine);
                	return null;
                }
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
        	throw new RuntimeException(e.getMessage());
        }  catch (IOException e) {  
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
	
	public static String doPost2(String url,Map<String, String> map){
	    log.info("post请求:" + url + "  ;post请求参数:" + map);
		CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            // 创建httppost.    
            HttpPost httpPost = new HttpPost(url);  
            List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
            for (Entry<String, String> entry : map.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
			}
//            nvps.add(new BasicNameValuePair("name", "aobama")); 
//            nvps.add(new BasicNameValuePair("id","123"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName("UTF-8")));
//            httpPost.setHeader("Content-Type", "text/html; charset=utf-8");
            /*httpPost.setEntity(new StringEntity(jsonStr, Charset.forName("UTF-8")));*/
         /*   httpPost.setEntity(new BasicNameValuePair("", ));*/
            CloseableHttpResponse response = httpclient.execute(httpPost);  
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();  
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == 200){
                	String contentString =EntityUtils.toString(entity);
                	return contentString;
                }
                else{
                    log.info("请求异常 返回:" + statusLine);
                	return null;
                }
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
        	throw new RuntimeException(e.getMessage());
        }  catch (IOException e) {  
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
			post.setEntity(new UrlEncodedFormEntity(parameters,"UTF-8"));
			CloseableHttpResponse response = httpclient.execute(post);  
			try {
	            // 获取响应实体    
	            HttpEntity entity = response.getEntity();  
	            StatusLine statusLine = response.getStatusLine();
	            if(statusLine.getStatusCode() == 200){
	            	String contentString = EntityUtils.toString(entity);
	            	return contentString;
	            }
	            else{
	            	return null;
	            }
	        } finally {  
	            response.close();  
	        }
        } catch (UnsupportedEncodingException e) {
        	throw new RuntimeException(e.getMessage());
		}catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
    }  

	
	
	public static String buildQuerys(Map<String, Object> params){
        if(params == null || params.size() == 0) return "";
        StringBuilder sb = new StringBuilder("?");
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if(entry.getValue() != null)
            sb.append(entry.getKey()+"="+entry.getValue()+"&");
        }
        sb.deleteCharAt(sb.lastIndexOf("&"));
        return sb.toString();
    }
}

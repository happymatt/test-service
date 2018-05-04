package com.jhqc.pxsj;

import java.io.IOException;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.RemoteConnectFailureException;
import org.springframework.remoting.RemoteInvocationFailureException;
import org.springframework.remoting.RemoteLookupFailureException;
import org.springframework.remoting.RemoteProxyFailureException;
import org.springframework.remoting.rmi.RmiServiceExporter;

@SpringBootApplication
public class Main {
	
	private static int threadNum = 200;
	private static String url = "http://192.168.31.251:8302/v1/city/getDetailProvinces";
	private static String url2 = "http://localhost:8302/v1/shop/queryShopByShopId?shopId=1";
	private static int scount = 0;
	private static int fcount = 0;
	public static void main(String[] args) throws RemoteException {
		String en = URLEncoder.encode("{");
		System.out.println(en);
		String temp = doGet(url2);
		System.out.println(temp);
		
		ExecutorService es1 = Executors.newFixedThreadPool(5);
		es1.submit((Callable<Boolean>)()-> {return true;});
		
		
			RemoteException re;
			RemoteAccessException rae;
			RemoteConnectFailureException ecfe;
			RemoteLookupFailureException rlfe;
			RemoteInvocationFailureException rife;
			RemoteProxyFailureException rpfe;
			
			RmiServiceExporter rse = new RmiServiceExporter();
			rse.setServiceName("shopService");
			rse.setServicePort(1099);
			rse.setService(new Object());
			rse.prepare();
		ExecutorService es = Executors.newFixedThreadPool(threadNum);
		for (int i = 0; i < threadNum; i++) {
			es.execute(new Runnable() {
				@Override
				public void run() {
					String result=doGet(url);
					if(null != result && !result.equals("")){
						System.out.println("调用成功"+(++scount));
					}
					else{
						System.out.println("调用失败"+(++fcount));
					}
				}
			});
		}
		
		//SpringApplication.run(Main.class, args);
	}
	public static String doGet(String url){
		CloseableHttpClient httpclient = HttpClients.createDefault();  
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
                	return null;
                } 
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
            return null;
        }  catch (IOException e) {  
            e.printStackTrace();  
            return null;
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
	}
  

}

package com.jhqc.pxsj.remoting;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

public class RemotingMain {
	
	public static void main(String[] args) throws RemoteException {
		ShopService ss = new ShopServiceImpl();
		LocateRegistry.createRegistry(1099);
		RmiServiceExporter rse = new RmiServiceExporter();
		rse.setService(ss);
		rse.setServiceInterface(ShopService.class);
		rse.setServiceName("shopService");
		rse.prepare();
		UriTemplate ut;
		RestTemplate rt;
	}

}

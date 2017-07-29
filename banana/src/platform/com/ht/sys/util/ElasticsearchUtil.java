package com.ht.sys.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;


public class ElasticsearchUtil {
	
	public final static TransportClient  client =  build();
	
	/**
	 * 创建一次
	 * @return
	 */
	private static TransportClient  build(){
		if(null != client){
			return client;
		}
	 	Settings settings = Settings.settingsBuilder()
//	 			.put("client.transport.sniff", true)
	 			.put("node.client", true)
	 			.put("cluster.name", "linkip_elasticsearch")
	 	        .build();
	 	
	 	TransportClient client = null;
		try {
			client = TransportClient.builder().settings(settings).build()
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("117.78.31.247"), 9300))
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("117.78.45.186"), 9300))
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("117.78.46.216"), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	 	
		return client;
	}
	
	/**
	 * 关闭
	 */
	public static void close(){
		if(null != client){
			try {
				client.close();
			} catch (Exception e) {
				
			}
		}
	}
}


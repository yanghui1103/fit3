package com.bw.fit.common.util;

import java.io.File;
import java.util.*;

import javax.activation.MimetypesFileTypeMap;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.util.treeHandler.JsonTreeHandler;
import com.bw.fit.system.model.DataDict;

public class Test { 
	private static String s ="123";
	public static Object getInstance(String name) throws Exception{
		Class<?> clazz = Class.forName(name);
		
		return clazz.newInstance() ;
	}
	public static void main(String[] args) throws Exception, Exception, Throwable{ 
		
//		Jedis j = new Jedis("192.168.189.12",6379);
//		j.auth("123456");
//		System.out.println(j.ping());
		File f = new File("gumby.gif");
	    System.out.println("Mime Type of " + f.getName() + " is " +
	                         new MimetypesFileTypeMap().getContentType(f));
	    
	}
	 
}

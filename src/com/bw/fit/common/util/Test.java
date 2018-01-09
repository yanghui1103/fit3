package com.bw.fit.common.util;

import java.util.*;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.util.treeHandler.JsonTreeHandler;
import com.bw.fit.system.model.DataDict;

public class Test { 
	public static void main(String[] args) throws Exception, Exception, Throwable{ 
		
		Jedis j = new Jedis("192.168.189.12",6379);
		j.auth("123456");
		System.out.println(j.ping());
	}
	 
}

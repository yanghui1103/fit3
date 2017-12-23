package com.bw.fit.common.util;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.util.treeHandler.JsonTreeHandler;
import com.bw.fit.system.model.DataDict;

public class Test { 
	public static void main(String[] args) throws Exception, Exception, Throwable{
		List<DataDict> list = new ArrayList<>();
		DataDict d1 = new DataDict(); 
		d1.setFdid("0");
		d1.setDict_name("顶级");
		d1.setParent_id("");
		list.add(d1);
		d1 = new DataDict();
		d1.setFdid("1");
		d1.setDict_name("系统管理");
		d1.setParent_id("0");
		list.add(d1);
		d1 = new DataDict();
		d1.setFdid("11");
		d1.setDict_name("权限类");
		d1.setParent_id("1");
		list.add(d1);
		d1 = new DataDict();
		d1.setFdid("111");
		d1.setDict_name("组织管理");
		d1.setParent_id("11");
		list.add(d1);
		d1 = new DataDict();
		d1.setFdid("112");
		d1.setDict_name("用户管理");
		d1.setParent_id("11");
		list.add(d1); 
		
		d1 = new DataDict();
		d1.setFdid("2");
		d1.setDict_name("应用管理");
		d1.setParent_id("0");
		list.add(d1);
		d1 = new DataDict();
		d1.setFdid("21");
		d1.setDict_name("系统类");
		d1.setParent_id("2");
		list.add(d1);
		d1 = new DataDict();
		d1.setFdid("211");
		d1.setDict_name("数据字典管理");
		d1.setParent_id("21"); 
		list.add(d1);
		  
		List dataList = new ArrayList();
		for(DataDict d:list){
			HashMap dataRecord1 = new HashMap();
			dataRecord1.put("id", d.getFdid());
			dataRecord1.put("text", d.getDict_name());
			dataRecord1.put("parentId", d.getParent_id());
			dataRecord1.put("can_add", d.getCan_add());
			dataList.add(dataRecord1);
		} 
		JsonTreeHandler.getJSONTree(dataList);
	}
	 
}

package com.bw.fit.common.util.treeHandler;

import java.util.*;

import com.alibaba.fastjson.JSONArray; 
import com.bw.fit.system.model.Company;

public class CompanyJsonTreeHandler {
	public static void main(String[] args) {
		// 读取层次数据结果集列表
		List dataList = VirtualDataGenerator.getVirtualResult();
		getJSONTree(dataList);
	}

	public static Company getJSONTree(List dataList) {

		  // 节点列表（散列表，用于临时存储节点对象）
		  HashMap nodeList = new HashMap();
		  // 根节点
		  Company root = null;
		  // 根据结果集构造节点列表（存入散列表）
		  for (Iterator it = dataList.iterator(); it.hasNext();) {
		   Map dataRecord = (Map) it.next();
		   Company node = new Company();
		   node.setFdid((String)dataRecord.get("id")); 
		   node.setCompany_name((String)dataRecord.get("text"));  
		   node.setParent_id((String)dataRecord.get("parentId"));
		   nodeList.put(node.getFdid(), node);
		  }
		  // 构造无序的多叉树
		  Set entrySet = nodeList.entrySet();
		  for (Iterator it = entrySet.iterator(); it.hasNext();) {
		   Company node = (Company) ((Map.Entry) it.next()).getValue();
		   if (node.getParent_id() == null || node.getParent_id().equals("")) {
		    root = node;
		   } else {
		    ((Company) nodeList.get(node.getParent_id())).addChild(node);
		   }
		  }
		  // 对多叉树进行横向排序
		  root.sortChildren();
		  return root;
		 } 
}
  
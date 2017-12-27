package com.bw.fit.common.util.treeHandler;

import java.util.Comparator;

import com.bw.fit.common.util.Node;
import com.bw.fit.system.model.Company;
import com.bw.fit.system.model.DataDict;

public class CompanyNumComparator implements Comparator {
	// 按照节点编号比较
	public int compare(Object o1, Object o2) { 
		int j1 = Integer.parseInt(((Company) o1).getCompany_order());
		int j2 = Integer.parseInt(((Company) o2).getCompany_order());
		return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));
	}

}

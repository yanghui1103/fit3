package com.bw.fit.system.dao;

import java.util.*;

import com.bw.fit.system.model.Company;

/****
 * 组织持久层
 * @author yangh
 *
 */
public interface CompanyDao {

	/*****
	 * 根据条件 ,查询组织列表
	 * @param c
	 * @return
	 */
	public List<Company> getCompanyList(Company c);
}

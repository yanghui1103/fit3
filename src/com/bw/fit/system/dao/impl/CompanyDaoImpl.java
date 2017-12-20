package com.bw.fit.system.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bw.fit.common.dao.DaoTemplete;
import com.bw.fit.system.dao.CompanyDao;
import com.bw.fit.system.model.Company;

@Repository
public class CompanyDaoImpl implements CompanyDao {

	@Autowired
	private DaoTemplete daoTemplete;
	@Override
	public List<Company> getCompanyList(Company c) {
		return daoTemplete.getListData("companySql.getCompanyList", c);
	}

}

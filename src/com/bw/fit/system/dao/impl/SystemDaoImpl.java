package com.bw.fit.system.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bw.fit.common.dao.DaoTemplete;
import com.bw.fit.system.dao.SystemDao;
import com.bw.fit.system.entity.TdataDict;
@Repository
public class SystemDaoImpl implements SystemDao {

	@Autowired
	private DaoTemplete daoTemplete;
	@Override
	public TdataDict getDictByValue(String dict_value) {
		return (TdataDict)daoTemplete.getOneData("systemSql.getDictByValue", dict_value);
	}
	@Override
	public List<TdataDict> getDataDictList(String parent_id) {
		return daoTemplete.getListData("systemSql.getDataDictList", parent_id);
	}

}

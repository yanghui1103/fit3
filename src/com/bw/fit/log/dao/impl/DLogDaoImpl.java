package com.bw.fit.log.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bw.fit.common.dao.DaoTemplete;
import com.bw.fit.log.dao.DLogDao;
import com.bw.fit.log.entity.TLogInfo;

@Repository
public class DLogDaoImpl implements DLogDao {

	@Autowired
	private DaoTemplete daoTemplete ;
	@Override
	public void notice(TLogInfo l) {
		// TODO Auto-generated method stub 
	}

	@Override
	public TLogInfo getLogInfoById(String fdid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getFdidByInfo(TLogInfo l) {
		// TODO Auto-generated method stub
		return null;
	}

}

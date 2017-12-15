package com.bw.fit.log.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.dao.DaoTemplete;
import com.bw.fit.log.dao.DLogDao;
import com.bw.fit.log.entity.TLogInfo;
import com.bw.fit.log.service.ILogService;

@Service
public class ILogServiceImpl implements ILogService {

	@Autowired
	private DLogDao dLogDao ;
	
	@Override
	public void notice(TLogInfo l) {
		// TODO Auto-generated method stub 
		dLogDao.notice(l);
	}

	@Override
	public TLogInfo getLogInfoById(String fdid) {
		// TODO Auto-generated method stub
		return dLogDao.getLogInfoById(fdid);
	}

	@Override
	public List<String> getFdidByInfo(TLogInfo l) {
		// TODO Auto-generated method stub
		return dLogDao.getFdidByInfo(l);
	}

}

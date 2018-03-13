package com.bw.fit.log.service.impl;

import static com.bw.fit.common.util.PubFun.copyProperties;
import static com.bw.fit.common.util.PubFun.returnFailJson;
import static com.bw.fit.common.util.PubFun.returnSuccessJson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.dao.DaoTemplete;
import com.bw.fit.common.model.RbackException;
import com.bw.fit.log.dao.DLogDao;
import com.bw.fit.log.entity.TLogInfo;
import com.bw.fit.log.service.ILogService;
import com.bw.fit.system.entity.Tpostion;

@Service
public class ILogServiceImpl implements ILogService {

	@Autowired
	private DLogDao dLogDao ;
	
	@Override
	public JSONObject notice(TLogInfo l) throws RbackException {
		JSONObject j = new JSONObject();
		returnSuccessJson(j);
		try{
			dLogDao.notice(l);
		}catch(RbackException e){
			j = new JSONObject();
			returnFailJson(j,e.getMsg());
			throw e;
		}finally{
			return j ;
		}
	
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

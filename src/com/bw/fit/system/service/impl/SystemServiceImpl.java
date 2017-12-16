package com.bw.fit.system.service.impl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.system.model.LogUser;
import com.bw.fit.system.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {

	@Override
	public JSONObject getUserCheckResult(LogUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getOnLineSituation(HttpSession session, LogUser user,
			ServletContext servletContext) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.bw.fit.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.dao.DaoTemplete;
import com.bw.fit.common.util.PropertiesUtil;
import com.bw.fit.system.entity.Tuser;
import com.bw.fit.system.model.LogUser;
import com.bw.fit.system.model.User;
import com.bw.fit.system.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {

	@Autowired
	private DaoTemplete daoTemplete ;
	@Override
	public JSONObject getUserCheckResult(LogUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getOnLineSituation(HttpSession session, LogUser user,
			ServletContext servletContext) {
		List<LogUser> showList = (ArrayList) (servletContext
				.getAttribute("onLineUserList"));
		JSONObject json = new JSONObject();
		if (showList == null || showList.size() < 1) {
			json.put("res", "2");
			json.put("msg", "此帐号此IP可以登录使用");
			return json;
		}
		int LogUserMaxCnt = Integer.valueOf(PropertiesUtil
				.getValueByKey("user.login.maxcnt"));
		if (showList != null && (showList.size() >= LogUserMaxCnt)) {
			json.put("res", "1");
			json.put("msg", "在线人数已经超出上限数目:" + LogUserMaxCnt);
			return json;
		}
		List<LogUser> afterList = showList
				.parallelStream()
				.filter((n) -> n.getUser_cd().equalsIgnoreCase(
						user.getUser_cd())
						&& n.getIp().equalsIgnoreCase(user.getIp()))
				.collect(Collectors.toList());
		if (afterList != null || afterList.size() > 0) {
			json.put("res", "1");
			json.put("msg", "此帐号已经在别的地方登录");
			return json;
		} 
		json.put("res", "2");
		json.put("msg", "此帐号此IP可以登录使用");
		return json;
	}

	@Override
	public User getCurrentUserInfo(String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

}

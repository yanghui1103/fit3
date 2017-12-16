package com.bw.fit.system.service;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.system.model.LogUser;

/****
 * 系统管理模块
 * @author yangh
 *
 */
public interface SystemService {

	/*****
	 * 验证登录用户信息，并反馈信息
	 * @param user 登录用户
	 * @return 验证结果信息
	 */
	public JSONObject getUserCheckResult(LogUser user);
}

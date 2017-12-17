package com.bw.fit.system.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.system.model.LogUser;
import com.bw.fit.system.model.User;

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
	/*****
	 * 查看当前用户帐号登录情况，检查是否多地登录
	 * @param session
	 * @param user 登录用户领域模型
	 * @param servletContext 上下文
	 * @return
	 */
	public JSONObject getOnLineSituation(HttpSession session,LogUser user,ServletContext servletContext);
	/*****
	 * 查询用户（领域模型）所有信息
	 * @param user_id
	 * @return
	 */
	public User getCurrentUserInfo(String user_id); 
}

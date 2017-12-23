package com.bw.fit.system.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
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
	public User getCurrentUserInfo(String user_cd); 
	/****
	 * 根据用户id查询其拥有的菜单
	 * @param user_id
	 * @return 返回JSONArray
	 */
	public JSONArray getMenuTreeJsonByUserId(String user_id);
	/****
	 * 根据用户id,和菜单
	 * @param user_id
	 * @param menuId
	 * @return
	 */
	public JSONObject getOperationsByMenuId(String user_id,String menuId);
	/*****
	 * 获取这个节点（parnet_id）下所有数据字典信息
	 * @param parent_id
	 * @return
	 */
	public JSONArray getAllDataDict(String parent_id) throws Exception;
	
}

package com.bw.fit.system.dao;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.system.entity.Tpostion;
import com.bw.fit.system.entity.Trole;
import com.bw.fit.system.entity.Tuser;
import com.bw.fit.system.model.Menu;

/****
 * 用户持久层
 * @author yangh
 *
 */
public interface UserDao {

	/*****
	 * 根据用户登录帐号，查询用户id
	 * @param user 登录用户
	 * @return  返回用户id
	 */
	public String getUserIdByCd(String user_cd);	
	/****
	 * 根据用户id查询用户基础资料
	 * @param user_id
	 * @return
	 */
	public Tuser getUserById(String user_id);
	/****
	 * 根据用户id查询其角色
	 * @param user_id
	 * @return
	 */
	public List<Trole> getUserRoleInfo(String user_id);
	/****
	 * 根据用户id查询其岗位
	 * @param user_id
	 * @return
	 */
	public List<Tpostion> getUserPostionInfo(String user_id);
	/*****
	 * 根据用户id查询拥有的所有菜单
	 * @param user_id
	 * @return
	 */
	public List<Menu> getMenuInfoByUserId(String user_id);
	
}

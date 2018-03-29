package com.bw.fit.system.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.shiro.session.Session;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.model.RbackException;
import com.bw.fit.common.util.Node;
import com.bw.fit.system.entity.Tpostion;
import com.bw.fit.system.model.Attachment;
import com.bw.fit.system.model.Company;
import com.bw.fit.system.model.DataDict;
import com.bw.fit.system.model.ElementLevel;
import com.bw.fit.system.model.LogUser;
import com.bw.fit.system.model.Postion;
import com.bw.fit.system.model.RoleAllot;
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
	public JSONObject getOnLineSituation(Session session,LogUser user,ServletContext servletContext);
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
	 * 根据角色id查询其拥有的菜单
	 * @param role_id
	 * @return 返回JSONArray
	 */
	public JSONArray getMenuAuthTreeJsonByRoleId(String role_id);
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
	public DataDict getAllDataDict(String parent_id) ;
	/*****
	 * 获取这个节点（parnet_id）下所有子孙机构tree
	 * @param parent_id
	 * @return
	 */
	public Company getCompanyTree(String parent_id)  ;
	
	/****
	 * 根据父节点，查询所有子孙的节点
	 * @param parent_id
	 * @return
	 */
	public List<DataDict> getChildrenDictList(String parent_id);
	/***
	 * 查询，菜单页面相关的权限的配置情况
	 * @param e
	 * @return
	 */
	public List<ElementLevel> getElementLevelList(ElementLevel e);
	/***
	 * 获取岗位列表
	 * @param e
	 * @return
	 */
	public List<Postion> getPostionList(Postion e);
	/****
	 * 给一个角色，分配权限
	 * 如果此角色如果有子孙角色
	 * 且如果取消权限，就会将子孙角色上对应的这个权限也一并取消
	 * @param roleAllot
	 * @throws RbackException
	 */
	public void allotOrUpdateRole(RoleAllot roleAllot) throws RbackException;
	/****
	 * 新建一个用户
	 * @param user
	 * @throws RbackException
	 */
	public void createUser(User user) throws RbackException;
	/*****
	 * 增加岗位
	 * @param p
	 * @throws RbackException
	 */
	public JSONObject createPostion(Postion p) throws RbackException;
	/****
	 * 上传附件，并做记录
	 * @param a
	 * @return
	 * @throws RbackException
	 */
	public JSONObject createNewAttachment(Attachment a) throws RbackException;
	
}

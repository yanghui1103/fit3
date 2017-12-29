package com.bw.fit.system.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.model.RbackException;
import com.bw.fit.system.entity.Tcompany;
import com.bw.fit.system.entity.TdataDict;
import com.bw.fit.system.entity.TelementLevel;
import com.bw.fit.system.entity.Tpostion;
import com.bw.fit.system.entity.Trole;
import com.bw.fit.system.entity.Tuser;
import com.bw.fit.system.model.ElementLevel;
import com.bw.fit.system.model.Menu;
import com.bw.fit.system.model.Postion;

/****
 * 系统基础持久层
 * @author yangh
 *
 */
public interface SystemDao {
	 
	/****
	 * 新建一个数据字典记录
	 * @param d 
	 */
	public void createDataDict(TdataDict d)  throws RbackException ;
	public void updateDataDict(TdataDict d)  throws RbackException ;

	/****
	 * 根据值获取数据字典对象
	 * @param dict_value
	 * @return
	 */
	public TdataDict getDictByValue(String dict_value);
	/****
	 * 获取父节点下所有子孙节点
	 * @param c
	 * @return
	 */
	public List<TdataDict> getDataDictList(String parent_id);
	public List<Tcompany> getCompanyTreeList(String parent_id);
	/****
	 * 此节点信息(数据字典)
	 * @param fdid
	 * @return
	 */
	public TdataDict getThisDataDictInfo(String fdid);
	/****
	 * 此节点信息(数据字典)
	 * @param dict_value 值
	 * @return
	 */
	public TdataDict getThisDataDictByValue(String dict_value);
	
	public void deleteDict(String fdid) throws RbackException;
	
	/****
	 * 根据父节点，查询所有子孙的节点
	 * @param parent_id
	 * @return
	 */
	public List<TdataDict> getChildrenDictList(String parent_id);
	
	/****
	 * 给角色对某个页面进行
	 * 数据权限分配
	 * @param role_id 角色
	 * @param menuId 菜单页面ID
	 * @param level_code 权限级别
	 * @throws RbackException
	 */
	public void createRoleElementLevel(String role_id,String menuId,String level_code) throws RbackException;
	/****
	 * 查询数据库功能权限级别列表
	 * @param e
	 * @return
	 */
	public List<ElementLevel> getElementLevelList(ElementLevel e);

	/***
	 * 获取岗位列表
	 * @param e
	 * @return
	 */
	public List<Tpostion> getPostionList(Tpostion e);
	
	public List<Trole> getRoleList(Trole t);
	public List<Tuser> getUserList(Tuser t);
	/****
	 * 保存岗位
	 * @param p
	 * @throws RbackException
	 */
	public void createPostion(Tpostion p) throws RbackException;
	/***
	 * 删除岗位
	 * @param fdid 岗位ID
	 * @throws RbackException
	 */
	public void deletePostion(String fdid) throws RbackException;
	/***
	 * 获取岗位
	 * @param fdid id
	 * @return
	 */
	public Tpostion getPostion(String fdid)  ;
	/***
	 * 保存新组织
	 * @param c
	 * @throws RbackException
	 */
	public void createCompany(Tcompany c)  throws RbackException;
	
	public Tcompany getCompany(String fdid);
	public void updateCompany(Tcompany p)  throws RbackException;
	public List<Menu> getMenuListByRoleId(String role_id);
	/****
	 * 查询这组数据字典
	 * 的所有值
	 * @param dict_remark
	 * @return
	 */
	public List<TdataDict> getALLPageAuths(String dict_remark);
	/****
	 * 保存页面权限
	 * @param t
	 * @throws RbackException
	 */
	public void createElementLevel(TelementLevel t)   throws RbackException;
	/****
	 * 删除页面权限 （真删）
	 * @param fdid
	 * @throws RbackException
	 */
	public void deleteELE(String fdid)  throws RbackException;
	/***
	 * 增加一个新角色
	 * @param role
	 * @throws RbackException
	 */
	public void createRole(Trole role) throws RbackException;
	public Trole getRole(String fdid) ;
}

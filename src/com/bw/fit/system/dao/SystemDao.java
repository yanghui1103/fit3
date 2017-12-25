package com.bw.fit.system.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.model.RbackException;
import com.bw.fit.system.entity.TdataDict;
import com.bw.fit.system.model.ElementLevel;

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
}

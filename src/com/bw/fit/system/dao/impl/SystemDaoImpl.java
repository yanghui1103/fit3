package com.bw.fit.system.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.dao.DaoTemplete;
import com.bw.fit.common.model.RbackException;
import com.bw.fit.common.util.PubFun;
import com.bw.fit.system.dao.SystemDao;
import com.bw.fit.system.entity.Tcompany;
import com.bw.fit.system.entity.TdataDict;
import com.bw.fit.system.entity.TelementLevel;
import com.bw.fit.system.entity.Toperation;
import com.bw.fit.system.entity.TpageElement;
import com.bw.fit.system.entity.Tpostion;
import com.bw.fit.system.entity.Trole;
import com.bw.fit.system.entity.Tuser;
import com.bw.fit.system.model.ElementLevel;
import com.bw.fit.system.model.Menu;
import com.bw.fit.system.model.RoleAllot;
@Repository
public class SystemDaoImpl implements SystemDao {

	@Autowired
	private DaoTemplete daoTemplete;
	@Override
	public TdataDict getDictByValue(String dict_value) {
		return (TdataDict)daoTemplete.getOneData("systemSql.getDictByValue", dict_value);
	}
	@Override
	public List<TdataDict> getDataDictList(String parent_id) {
		return daoTemplete.getListData("systemSql.getDataDictList", parent_id);
	}
	@Override
	public TdataDict getThisDataDictInfo(String fdid) {
		return (TdataDict)daoTemplete.getOneData("systemSql.getThisDataDictInfo", fdid);
	}
	@Override
	public void deleteDict(String fdid) throws RbackException {
		daoTemplete.delete("systemSql.deleteDict", fdid);	
	}
	@Override
	public List<TdataDict> getChildrenDictList(String parent_id) {
		return daoTemplete.getListData("systemSql.getChildrenDictList", parent_id);
	}


	@Override
	public void createRoleElementLevel(String role_id, String menuId,
			String level_code) throws RbackException {
		TelementLevel e = new TelementLevel();
		e.setFdid(PubFun.getUUID());
		e.setRole_id(role_id);
		e.setMenu_id(menuId);
		e.setLevel_code(level_code);
		daoTemplete.insert("systemSql.createRoleElementLevel", e);
	}
	@Override
	public List<ElementLevel> getElementLevelList(ElementLevel e) {
		return daoTemplete.getListData("systemSql.getElementLevelList", e);
	}
	@Override
	public TdataDict getThisDataDictByValue(String dict_value) {
		return (TdataDict)daoTemplete.getOneData("systemSql.getThisDataDictByValue", dict_value);
	}
	@Override
	public void createDataDict(TdataDict d) throws RbackException {
		daoTemplete.insert("systemSql.createDataDict", d); 
	}
	@Override
	public void updateDataDict(TdataDict d) throws RbackException {
		daoTemplete.update("systemSql.updateDataDict", d); 
	}
	@Override
	public List<Tpostion> getPostionList(Tpostion e) {
		return daoTemplete.getListData("postionSql.getPostionList", e);
	}
	@Override
	public List<Trole> getRoleList(Trole t) {
		return daoTemplete.getListData("roleSql.getRoleList", t);
	}
	@Override
	public List<Tuser> getUserList(Tuser t) {
		return daoTemplete.getListData("userSql.getUserList", t);
	}
	@Override
	public void createPostion(Tpostion p) throws RbackException {
		daoTemplete.insert("postionSql.createPostion", p);
	}
	@Override
	public void deletePostion(String fdid) throws RbackException {
		daoTemplete.delete("postionSql.deletePostion", fdid);
	}
	@Override
	public Tpostion getPostion(String fdid) {
		return (Tpostion)daoTemplete.getOneData("postionSql.getPostion", fdid);
	}
	@Override
	public List<Tcompany> getCompanyTreeList(String parent_id) {
		return daoTemplete.getListData("companySql.getCompanyTreeList", parent_id);
	}
	@Override
	public void createCompany(Tcompany c) throws RbackException {
		daoTemplete.insert("companySql.createCompany", c);
	}
	@Override
	public Tcompany getCompany(String fdid) {
		return (Tcompany)daoTemplete.getOneData("companySql.getCompany", fdid);
	}
	@Override
	public void updateCompany(Tcompany p) throws RbackException {
		daoTemplete.update("companySql.updateCompany", p);
	}
	@Override
	public List<Menu> getMenuListByRoleId(String role_id) {
		return daoTemplete.getListData("systemSql.getMenuListByRoleId", role_id);
	}
	@Override
	public List<TdataDict> getALLPageAuths(String dict_remark) {
		return daoTemplete.getListData("systemSql.getALLPageAuths", dict_remark);
	}
	@Override
	public void createElementLevel(TelementLevel t) throws RbackException {
		daoTemplete.insert("systemSql.createElementLevel", t);
	}
	@Override
	public void deleteELE(String fdid) throws RbackException {
		daoTemplete.delete("systemSql.deleteELE", fdid);
	}
	@Override
	public void createRole(Trole role) throws RbackException {
		daoTemplete.insert("roleSql.createRole", role);
	}
	@Override
	public Trole getRole(String fdid) {
		Trole tp = new Trole();
		tp = (Trole)daoTemplete.getOneData("roleSql.getRole", fdid);
		if(tp!=null){
			Trole t = ((Trole)daoTemplete.getOneData("roleSql.getRole", tp.getParent_id()));
			tp.setParent_role_name(t!=null?t.getRole_name():"");
			return tp ;
		}else{
			return null;
		}
	}
	@Override
	public void deleteRole(String fdid) throws RbackException {
		daoTemplete.update("roleSql.deleteRole", fdid);
	}
	@Override
	public List<Menu> getMenuAuthTreeJsonByRoleId(String role_id) {
		return daoTemplete.getListData("roleSql.getMenuAuthTreeJsonByRoleId",role_id);
	}
	@Override
	public List<Toperation> getOperationsByMenuId(String menu_id, String role_id ) {
		Trole t = new Trole();
		t.setFdid(role_id);
		t.setKeyWords(menu_id);
		return daoTemplete.getListData("roleSql.getOperationsByMenuId", t);
	}
	@Override
	public List<TpageElement> getElementsByMenuId(String menu_id, String role_id ) {
		Trole t = new Trole();
		t.setFdid(role_id);
		t.setKeyWords(menu_id);
		return daoTemplete.getListData("roleSql.getElementsByMenuId", t);
	}
	@Override
	public List<RoleAllot> getChildRoleAllotsByRoleId(List item) {
		return daoTemplete.getListData("roleSql.getChildRoleAllotsByRoleId", item);
	}
	@Override
	public List<Trole> getChildrenRoles(String role_id) {
		return daoTemplete.getListData("roleSql.getChildrenRoles", role_id);
	}
	@Override
	public void deleteUser(String fdid) throws RbackException {
		daoTemplete.update("userSql.deleteUser", fdid);
	}
	
	
	
}

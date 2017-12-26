package com.bw.fit.system.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.dao.DaoTemplete;
import com.bw.fit.common.model.RbackException;
import com.bw.fit.common.util.PubFun;
import com.bw.fit.system.dao.SystemDao;
import com.bw.fit.system.entity.TdataDict;
import com.bw.fit.system.entity.TelementLevel;
import com.bw.fit.system.entity.Tpostion;
import com.bw.fit.system.entity.Trole;
import com.bw.fit.system.entity.Tuser;
import com.bw.fit.system.model.ElementLevel;
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
}

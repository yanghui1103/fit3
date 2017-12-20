package com.bw.fit.system.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.dao.DaoTemplete;
import com.bw.fit.system.dao.UserDao;
import com.bw.fit.system.entity.Tpostion;
import com.bw.fit.system.entity.Trole;
import com.bw.fit.system.entity.Tuser;
import com.bw.fit.system.model.Menu;
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private DaoTemplete daoTemplete ;
	@Override
	public String getUserIdByCd(String user_cd) {
		// TODO Auto-generated method stub
		return (String)daoTemplete.getOneData("userSql.getUserIdByCd", user_cd);
	}
	@Override
	public Tuser getUserById(String user_id) {
		// TODO Auto-generated method stub
		return (Tuser)daoTemplete.getOneData("userSql.getUserById", user_id);
	}
	@Override
	public List<Trole> getUserRoleInfo(String user_id) {
		return daoTemplete.getListData("userSql.getUserRoleInfo", user_id);
	}
	@Override
	public List<Tpostion> getUserPostionInfo(String user_id) {
		return daoTemplete.getListData("userSql.getUserPostionInfo", user_id);
	}
	@Override
	public List<Menu> getMenuInfoByUserId(String user_id) {
		return daoTemplete.getListData("userSql.getMenuInfoByUserId", user_id);
	}

}

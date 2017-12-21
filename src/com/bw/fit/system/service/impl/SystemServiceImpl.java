package com.bw.fit.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import static com.bw.fit.common.util.PubFun.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.dao.DaoTemplete;
import com.bw.fit.common.util.PropertiesUtil;
import com.bw.fit.common.util.PubFun;
import com.bw.fit.system.dao.SystemDao;
import com.bw.fit.system.dao.UserDao;
import com.bw.fit.system.entity.TdataDict;
import com.bw.fit.system.entity.Toperation;
import com.bw.fit.system.entity.Tpostion;
import com.bw.fit.system.entity.Trole;
import com.bw.fit.system.entity.Tuser;
import com.bw.fit.system.model.LogUser;
import com.bw.fit.system.model.Menu;
import com.bw.fit.system.model.Postion;
import com.bw.fit.system.model.Role;
import com.bw.fit.system.model.User;
import com.bw.fit.system.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {

	@Autowired
	private SystemDao systemDao;
	@Autowired
	private DaoTemplete daoTemplete;
	@Autowired
	private UserDao userDao;

	@Override
	public JSONObject getOnLineSituation(HttpSession session, LogUser user,
			ServletContext servletContext) {
		List<LogUser> showList = (ArrayList) (servletContext
				.getAttribute("onLineUserList"));
		JSONObject json = new JSONObject();
		if (showList == null || showList.size() < 1) {
			json.put("res", "2");
			json.put("msg", "此帐号此IP可以登录使用");
			return json;
		}
		int LogUserMaxCnt = Integer.valueOf(PropertiesUtil
				.getValueByKey("user.login.maxcnt"));
		if (showList != null && (showList.size() >= LogUserMaxCnt)) {
			json.put("res", "1");
			json.put("msg", "在线人数已经超出上限数目:" + LogUserMaxCnt);
			return json;
		}
		List<LogUser> afterList = showList
				.parallelStream()
				.filter((n) -> n.getUser_cd().equalsIgnoreCase(
						user.getUser_cd())
						&& n.getIp().equalsIgnoreCase(user.getIp()))
				.collect(Collectors.toList());
		if (afterList != null || afterList.size() > 0) {
			json.put("res", "1");
			json.put("msg", "此帐号已经在别的地方登录");
			return json;
		}
		json.put("res", "2");
		json.put("msg", "此帐号此IP可以登录使用");
		return json;
	}

	@Override
	public User getCurrentUserInfo(String user_cd) {
		User user = new User();
		String user_id = userDao.getUserIdByCd(user_cd);
		copyProperties(user, userDao.getUserById(user_id));
		List<Role> roles = new ArrayList<>();
		List<Trole> rls = userDao.getUserRoleInfo(user_id);
		for (Trole r : rls) {
			Role r1 = new Role();
			copyProperties(r1, r);
			roles.add(r1);
		}
		if (roles != null) {
			user.setRole_list(roles);
		}
		List<Postion> postions1 = new ArrayList<>();
		List<Tpostion> Postions = userDao.getUserPostionInfo(user_id);
		for (Tpostion p : Postions) {
			Postion r1 = new Postion();
			copyProperties(r1, p);
			postions1.add(r1);
		}
		if (postions1 != null) {
			user.setPostion_list(postions1);
		}
		return user;
	}

	@Override
	public JSONArray getMenuTreeJsonByUserId(String user_id) {
		JSONArray array = new JSONArray();
		List<Menu> list = userDao.getMenuInfoByUserId(user_id);
		list = list
				.stream()
				.sorted((x, y) -> (x.getMenu_level().compareTo(y
						.getMenu_level()))).collect(Collectors.toList());
		JSONArray json = new JSONArray();
		List<Menu> nodeList = list;

		// start
		List<Menu> levelList = nodeList.stream()
				.filter((n) -> "1".equals(n.getMenu_level()))
				.collect(Collectors.toList());
		json = getJSON(nodeList, levelList);
		return json;
	}

	private boolean getExisteNode(List<Menu> list, Menu c) {
		List ls = list.stream()
				.filter((n) -> (n.getParent_id()).equals(c.getFdid()))
				.collect(Collectors.toList());
		if (ls.size() < 1)
			return false;
		return true;
	}

	private JSONArray getJSON(List<Menu> list2, List<Menu> list) {
		JSONArray array1 = new JSONArray();
		for (Menu cc : list) {
			JSONObject json2 = new JSONObject();
			json2.put("text", cc.getMenu_name());
			String fdid = cc.getFdid();
			List<Menu> childs = list2.stream()
					.filter((n) -> (n.getParent_id()).equals(fdid))
					.collect(Collectors.toList());
			if (childs.size() > 0) {
				json2.put("children", getChildJSON(childs, fdid, list2));
			}
			array1.add(json2);
		}
		return array1;
	}

	private JSONArray getChildJSON(List<Menu> list, String fdid,
			List<Menu> alllist) {
		JSONArray array = new JSONArray();
		List<Menu> list2 = alllist.stream()
				.filter((n) -> fdid.equals(n.getParent_id()))
				.collect(Collectors.toList());

		for (Menu cc : list2) {
			JSONObject json2 = new JSONObject();
			json2.put("id", cc.getFdid());
			json2.put("text", cc.getMenu_name());
			JSONArray arra2 = getChildJSON(list, cc.getFdid(), alllist);
			if (arra2.size() > 0) {
				json2.put("children", arra2);
			}
			if (!getExisteNode(list, cc)) {
				array.add(json2);
			}
		}
		return array;

	}

	@Override
	public JSONObject getOperationsByMenuId(String user_id, String menuId) {
		JSONObject json = new JSONObject();
		Toperation t = new Toperation();
		t.setCreator_id(user_id);
		t.setForeign_id(menuId);
		List<Toperation> list = daoTemplete.getListData("systemSql.getOperationsByMenuId", t);
		if (list.size() < 1) {
			json.put("res", "1");
			json.put("msg", "无按钮操作权限，请与管理员联系申请");
			return json;
		}
		json.put("res", "2");
		json.put("msg", "有按钮操作权限");
		JSONArray array = new JSONArray();
		array = (JSONArray)JSONArray.toJSON(list);
		json.put("list", array);
		return json;
	}

	@Override
	public JSONArray getAllDataDict(String parent_id) {
		List<TdataDict> list = systemDao.getDataDictList(parent_id);
		return null;
	}

}

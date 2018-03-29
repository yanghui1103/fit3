package com.bw.fit.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import static com.bw.fit.common.util.PubFun.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.dao.DaoTemplete;
import com.bw.fit.common.entity.BaseEntity;
import com.bw.fit.common.model.RbackException;
import com.bw.fit.common.util.Node;
import com.bw.fit.common.util.PropertiesUtil;
import com.bw.fit.common.util.PubFun;
import com.bw.fit.common.util.treeHandler.CompanyJsonTreeHandler;
import com.bw.fit.common.util.treeHandler.DataDictJsonTreeHandler;
import com.bw.fit.common.util.treeHandler.JsonTreeHandler;
import com.bw.fit.system.dao.SystemDao;
import com.bw.fit.system.dao.UserDao;
import com.bw.fit.system.entity.TAttachment;
import com.bw.fit.system.entity.Tcompany;
import com.bw.fit.system.entity.TdataDict;
import com.bw.fit.system.entity.Toperation;
import com.bw.fit.system.entity.TpageElement;
import com.bw.fit.system.entity.Tpostion;
import com.bw.fit.system.entity.Trole;
import com.bw.fit.system.entity.Tuser;
import com.bw.fit.system.model.Attachment;
import com.bw.fit.system.model.Company;
import com.bw.fit.system.model.DataDict;
import com.bw.fit.system.model.ElementLevel;
import com.bw.fit.system.model.LogUser;
import com.bw.fit.system.model.Menu;
import com.bw.fit.system.model.Postion;
import com.bw.fit.system.model.Role;
import com.bw.fit.system.model.RoleAllot;
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
	public JSONObject getOnLineSituation(Session session, LogUser user,
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
		Tuser t = userDao.getUserById(user_id);
		copyProperties(user, t);
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
	public JSONArray getMenuAuthTreeJsonByRoleId(String role_id) {
		JSONArray array = new JSONArray();
		List<Menu> list = systemDao.getMenuAuthTreeJsonByRoleId(role_id);
		if (list == null)
			return null;
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
			json2.put("id", cc.getFdid());
			json2.put("text", cc.getMenu_name());
			json2.put("state", cc.getState());

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
			json2.put("state", cc.getState());
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
		List<Toperation> list = daoTemplete.getListData(
				"systemSql.getOperationsByMenuId", t);
		if (list == null) {
			json.put("res", "1");
			json.put("msg", "无按钮操作权限，请与管理员联系申请");
			return json;
		}
		json.put("res", "2");
		json.put("msg", "有按钮操作权限");
		JSONArray array = new JSONArray();
		array = (JSONArray) JSONArray.toJSON(list);
		json.put("list", array);
		return json;
	}

	@Override
	public Company getCompanyTree(String parent_id) {
		List<Tcompany> list = systemDao.getCompanyTreeList(parent_id);
		List<Company> lis = new ArrayList<>();
		for (Tcompany d : list) {
			Company dd = new Company();
			PubFun.copyProperties(dd, d);
			if (parent_id.equals(dd.getParent_id())) {
				dd.setParent_id("");
			}
			lis.add(dd);
		}

		List dataList = new ArrayList();
		for (Company d : lis) {
			HashMap dataRecord1 = new HashMap();
			dataRecord1.put("id", d.getFdid());
			dataRecord1.put("text", d.getCompany_name());
			dataRecord1.put("parentId", d.getParent_id());
			dataRecord1.put("company_order", d.getCompany_order());
			dataList.add(dataRecord1);
		}
		Company node = CompanyJsonTreeHandler.getJSONTree(dataList);
		return node;
	}

	@Override
	public DataDict getAllDataDict(String parent_id) {
		List<TdataDict> list = systemDao.getDataDictList(parent_id);
		List<DataDict> lis = new ArrayList<>();
		for (TdataDict d : list) {
			DataDict dd = new DataDict();
			PubFun.copyProperties(dd, d);
			if ("0".equals(dd.getParent_id())) {
				dd.setParent_id("");
			}
			lis.add(dd);
		}

		List dataList = new ArrayList();
		for (DataDict d : lis) {
			HashMap dataRecord1 = new HashMap();
			dataRecord1.put("fdid", d.getFdid());
			dataRecord1.put("dict_value", d.getDict_value());
			dataRecord1.put("dict_remark", d.getDict_remark());
			dataRecord1.put("dict_name", d.getDict_name());
			dataRecord1.put("parent_id", d.getParent_id());
			dataRecord1.put("can_add", d.getCan_add());
			dataRecord1.put("can_edit", d.getCan_edit());
			dataRecord1.put("can_del", d.getCan_del());
			dataRecord1.put("num", d.getNum());
			dataList.add(dataRecord1);
		}
		DataDict node = DataDictJsonTreeHandler.getJSONTree(dataList);
		return node;
	}

	@Override
	public List<DataDict> getChildrenDictList(String parent_id) {
		List<TdataDict> list = systemDao.getChildrenDictList(parent_id);
		List<DataDict> lis = new ArrayList<>();
		for (TdataDict dd : list) {
			DataDict d2 = new DataDict();
			PubFun.copyProperties(d2, dd);
			lis.add(d2);
		}
		return lis;
	}

	@Override
	public List<ElementLevel> getElementLevelList(ElementLevel e) {
		List<ElementLevel> lis = systemDao.getElementLevelList(e);
		List<ElementLevel> list = new ArrayList<>();
		if (lis == null)
			return null;
		for (ElementLevel el : lis) {
			TdataDict d = systemDao.getThisDataDictByValue(el.getLevel_code());
			if (d != null) {
				el.setLevel_desp(d.getDict_name());
			}
			d = systemDao.getThisDataDictByValue(el.getElement_type());
			if (d != null) {
				el.setElement_type_name(d.getDict_name());
			}

			list.add(el);
		}
		return list;
	}

	@Override
	public List<Postion> getPostionList(Postion e) {
		Tpostion t = new Tpostion();
		copyProperties(t, e);
		List<Tpostion> lis = systemDao.getPostionList(t);
		List<Postion> list = new ArrayList<>();
		for (Tpostion p : lis) {
			Postion pp = new Postion();
			copyProperties(pp, p);
			list.add(pp);
		}
		return list;
	}

	public void allotOrUpdateRole2(RoleAllot roleAllot) throws RbackException {
		// 角色下原有的权限
		List<Trole> roles_child = systemDao.getChildrenRoles(roleAllot
				.getRole_id());
		List<RoleAllot> roleAllotsChilds = new ArrayList<>();
		if (roles_child != null) {// 有子孙角色
			List<String> item = new ArrayList<>();
			for (Trole r : roles_child) {
				item.add(r.getFdid());
			}
			roleAllotsChilds = systemDao.getChildRoleAllotsByRoleId(item);
		}
		List<String> item = new ArrayList<>();
		item.add(roleAllot.getRole_id());
		List<RoleAllot> roleAllotsThis = systemDao
				.getChildRoleAllotsByRoleId(item);
		/****
		 * 1，先检查菜单 如果此次授权有operation，说明是增加或者原来就有此菜单权限
		 */
		List<RoleAllot> thisLis = roleAllotsThis
				.stream()
				.filter(x -> x.getRole_id().equalsIgnoreCase(
						roleAllot.getRole_id())
						&& x.getMenu_id().equalsIgnoreCase(
								roleAllot.getMenu_id()))
				.collect(Collectors.toList());
		String[] operations = roleAllot.getOperation_ids();
		for (String s : operations) {
			Optional<RoleAllot> op = thisLis.stream()
					.filter(x -> x.getOperation_id().equalsIgnoreCase(s))
					.findAny();
			if (!op.isPresent()) {
				BaseEntity en = new BaseEntity();
				en.setFdid(op.get().getRole_id());
				en.setKeyWords(op.get().getOperation_id());
				systemDao.createAuthoryOperation(en);
			}
		}
		String[] elments = roleAllot.getElement_ids();
		for (String s : elments) {
			Optional<RoleAllot> op = thisLis.stream()
					.filter(x -> x.getElement_id().equalsIgnoreCase(s))
					.findAny();
			if (!op.isPresent()) {
				BaseEntity en = new BaseEntity();
				en.setFdid(op.get().getRole_id());
				en.setKeyWords(op.get().getElement_id());
				en.setCreator(op.get().getMenu_id());
				systemDao.createAuthoryElement(en);
			}
		}

	}

	@Override
	public void createUser(User user) throws RbackException {
		user.setFdid(getUUID());
		Tuser tuser = new Tuser();
		copyProperties(tuser, user);
		systemDao.createUser(tuser);
		systemDao.createUser2Company(tuser);
		for (String role_id : user.getRole_ids()) {
			tuser.setRole_id(role_id);
			systemDao.createUser2Role(tuser);
		}
		for (String postion_id : user.getPostion_ids()) {
			tuser.setPostion_id(postion_id);
			systemDao.createUser2Postion(tuser);
		}
	}

	@Override
	public JSONObject createPostion(Postion p) throws RbackException {
		JSONObject j = new JSONObject();
		returnSuccessJson(j);
		try {
			Tpostion pp = new Tpostion();
			copyProperties(pp, p);
			systemDao.createPostion(pp);
		} catch (RbackException e) {
			j = new JSONObject();
			returnFailJson(j, e.getMsg());
			throw e;
		} finally {
			return j;
		}
	}

	@Override
	public void allotOrUpdateRole(RoleAllot roleAllot) throws RbackException {

		BaseEntity enw = new BaseEntity();
		enw.setFdid(roleAllot.getRole_id());
		enw.setCreator(roleAllot.getMenu_id());
		if (systemDao.getAuthoritymenu(enw) < 1) {
			systemDao.createAuthoritymenu(enw);
		}

		List<Toperation> operations = systemDao.getOperationsByMenuId(
				roleAllot.getMenu_id(), roleAllot.getRole_id());
		String[] ops = roleAllot.getOperation_ids();
		if (operations != null) {
			for (String s : ops) {
				Optional<Toperation> opt = operations.stream()
						.filter(x -> x.getFdid().equals(s)).findAny();
				if (!opt.isPresent()) {
					BaseEntity en = new BaseEntity();
					en.setFdid(roleAllot.getRole_id());
					en.setKeyWords(opt.get().getFdid());
					systemDao.createAuthoryOperation(en);
				} else {
					BaseEntity en = new BaseEntity();
					en.setFdid(roleAllot.getRole_id());
					en.setKeyWords(s);
					systemDao.createAuthoryOperation(en);
				}
			}
		} else {
			for (String s : ops) {
				BaseEntity en = new BaseEntity();
				en.setFdid(roleAllot.getRole_id());
				en.setKeyWords(s);
				systemDao.createAuthoryOperation(en);
			}
		}

		if (operations != null) {
			List<String> operationStrs = operations.stream()
					.map(Toperation::getFdid).collect(Collectors.toList());
			for (String ss : operationStrs) {
				if (!Arrays.asList(ops).contains(ss)) {
					BaseEntity en = new BaseEntity();
					en.setFdid(roleAllot.getRole_id());
					en.setKeyWords(ss);
					systemDao.delAuthoryOperation(en);
				}
			}
		}

		List<TpageElement> telements = systemDao.getElementsByMenuId(
				roleAllot.getMenu_id(), roleAllot.getRole_id());
		String[] elements = roleAllot.getElement_ids();
		if (telements != null) {
			for (String s : elements) {
				Optional<TpageElement> opt = telements.stream()
						.filter(x -> x.getFdid().equals(s)).findAny();
				if (!opt.isPresent()) {
					BaseEntity en = new BaseEntity();
					en.setFdid(roleAllot.getRole_id());
					en.setKeyWords(opt.get().getFdid());
					en.setCreator(roleAllot.getMenu_id());
					systemDao.delAuthoryElement(en);
				} else {
					BaseEntity en = new BaseEntity();
					en.setFdid(roleAllot.getRole_id());
					en.setKeyWords(roleAllot.getOperation_id());
					en.setCreator(roleAllot.getMenu_id());
					systemDao.createAuthoryElement(en);
				}
			}
		} else {
			for (String s : elements) {
				BaseEntity en = new BaseEntity();
				en.setFdid(roleAllot.getRole_id());
				en.setKeyWords(s);
				en.setCreator(roleAllot.getMenu_id());
				systemDao.createAuthoryElement(en);
			}
		}

		if (telements != null) {
			List<String> elementStrs = telements.stream()
					.map(TpageElement::getFdid).collect(Collectors.toList());
			for (String ss : elementStrs) {
				if (!Arrays.asList(elements).contains(ss)) {
					BaseEntity en = new BaseEntity();
					en.setFdid(roleAllot.getRole_id());
					en.setKeyWords(ss);
					en.setCreator(roleAllot.getMenu_id());
					systemDao.delAuthoryElement(en);
				}
			}
		}

	}

	@Override
	public JSONObject createNewAttachment(Attachment a) throws RbackException {
		JSONObject json = new JSONObject(); 
		PubFun.returnSuccessJson(json);
		try{
			TAttachment aa = new TAttachment();
			copyProperties(aa, a);
			systemDao.createNewAttachment(aa);
		}catch(RbackException ex){
			json = new JSONObject();
			json.put("msg", "导入异常,"+ex.getLocalizedMessage());
			json.put("res", "1"); 
			throw ex;
		}finally{
			return json ;
		}
		
	}

}

package com.bw.fit.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest; 
import javax.validation.Valid;

import static com.bw.fit.common.util.PubFun.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.controller.BaseController;
import com.bw.fit.common.dao.DaoTemplete;
import com.bw.fit.common.model.RbackException;
import com.bw.fit.common.util.Node;
import com.bw.fit.common.util.PropertiesUtil; 
import com.bw.fit.common.util.PubFun;
import com.bw.fit.system.dao.CompanyDao;
import com.bw.fit.system.dao.SystemDao;
import com.bw.fit.system.dao.UserDao;
import com.bw.fit.system.entity.*;
import com.bw.fit.system.model.*;
import com.bw.fit.system.service.SystemService;

/*****
 * 系统管理_核心
 * 
 * @author yangh
 *
 */
@RequestMapping("system")
@Controller
public class SystemCoreController extends BaseController {
	@Autowired
	private SystemService systemService;
	@Autowired
	private CompanyDao companyDao ;
	@Autowired
	private SystemDao systemDao;
	@Autowired
	private DaoTemplete daoTemplete ;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DefaultWebSecurityManager securityManager ;

	/****
	 * 登录请求
	 * @param user
	 * @param result
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String normalLogin(@Valid @ModelAttribute LogUser user,
			BindingResult result, HttpServletRequest request, Model model) {
		Session session = null;
		try {
			model.addAttribute("user", user);
			if (result.hasErrors()) {
				FieldError error = result.getFieldError();
				model.addAttribute("errorMsg", error.getDefaultMessage());
				return "common/loginPage";
			}
			// 获取存放在session中的验证码
			// String code = (String)
			// request.getSession().getAttribute("verificationCode");
			// 获取页面提交的验证码
			// String inputCode = user.getVerificationCode();
			// if(!code.toLowerCase().equals(inputCode.toLowerCase())) { //
			// 验证码不区分大小写
			// model.addAttribute("errorMsg", "验证码错误");
			// return "common/loginPage";
			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("errorMsg", "登录失败");
			return "common/loginPage";
		}

		/**** 开始shiro登录 *****/
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(
					user.getUser_cd(), user.getPasswd());
			Subject currentUser = SecurityUtils.getSubject();
			token.setRememberMe(true);
			currentUser.login(token);
			session = currentUser.getSession();
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("errorMsg", "登录失败,认证拦截:" + e.getMessage());
			return "common/loginPage";
		}

		/***
		 * 是否可以俩处登录
		 */
		// if ("false".equalsIgnoreCase(PropertiesUtil
		// .getValueByKey("user.multi.login"))) {
		// JSONObject j = systemService.getOnLineSituation(session, user,
		// request.getServletContext());
		// if ("1".equals(j.get("res"))) {
		// model.addAttribute("errorMsg", j.get("msg"));
		// return "common/loginPage";
		// }
		// }
		User uu = systemService.getCurrentUserInfo(user.getUser_cd());
		session.setAttribute("CurrentUser", uu);
		return "common/indexPage";
	}

	/*****
	 * 当前用户，的所有菜单权限 拼接为JSON-父子结构
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "getMenuAuthTreeJson", produces = "application/json; charset=utf-8")
	@ResponseBody
	public JSONArray getMenuAuthTreeJson() {
		Session session = PubFun.getCurrentSession();
		String user_id = ((User) session.getAttribute("CurrentUser")).getFdid();
		return systemService.getMenuTreeJsonByUserId(user_id);
	}

	/*****
	 * 菜单id，查询其对应的URL
	 * 
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value = "getFrameUrlByMenuId", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getFrameUrlByMenuId(
			@RequestParam(value = "menuId") String menuId) {
		JSONObject json = new JSONObject();
		Menu menu = (Menu) daoTemplete.getOneData(
				"systemSql.getFrameUrlByMenuId", menuId);
		if (StringUtils.isBlank(menu.getMenu_path())
				|| "".equals(menu.getMenu_path())
				|| "-9".equals(menu.getMenu_path())) {
			json.put("res", "1");
			return json.toJSONString();
		} else {
			return JSONObject.toJSONString(menu);
		}
	}

	@RequestMapping("openCompanyList/{params}")
	public String openCompanyList(@PathVariable("params") String params) {
		return "system/company/companyListPage";
	}
	 

	/*****
	 * 查询组织管理列表
	 * 
	 * @param params
	 * @param model
	 *            UI-Model
	 * @param c
	 *            组织
	 * @param request
	 *            请求
	 * @param session
	 *            会话
	 * @return
	 */
	@RequestMapping("companyList/{params}")
	@ResponseBody
	public JSONObject companyList(@PathVariable("params") String params,
			Model model, @ModelAttribute Company c, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		c.setPaginationEnable("1");
		List<Company> list = companyDao.getCompanyList(c);
		if (list != null && list.size() > 0) {
			for (Company cc : list) {
				TdataDict d = (systemDao
						.getDictByValue(cc.getCompany_type_cd()));
				if (d != null) {
					cc.setCompany_type_name(d.getDict_name());
				}
				Tcompany tc = (systemDao.getCompany(cc.getParent_id())) ;
				if(tc !=null){
					cc.setParent_company_name(tc.getCompany_name()) ;	
				}			
			}
		}
		c.setPaginationEnable("0");
		List<Company> listTotal = companyDao.getCompanyList(c);
		if (listTotal != null && listTotal.size() > 0) {
			json.put("total", listTotal.size());
		} else {
			json.put("total", 0);
		}
		json.put("rows", JSONObject.toJSON(list));
		return json;
	}

	/*****
	 * 获取此用户在此页面的功能权限
	 * 
	 * @param BtnPrefixCode
	 * @param requset
	 * @param session
	 * @return
	 */
	@RequestMapping("getOperationsByMenuId/{BtnPrefixCode}")
	@ResponseBody
	public JSONObject getOperationsByMenuId(
			@PathVariable(value = "BtnPrefixCode") String BtnPrefixCode,
			HttpServletRequest requset) {
		Session session = getCurrentSession();
		JSONObject json = new JSONObject();
		json = systemService.getOperationsByMenuId(
				((User) session.getAttribute("CurrentUser")).getFdid(),
				BtnPrefixCode);
		return json;
	}

	/*****
	 * 删除组织
	 * 
	 * @param fdid
	 * @return
	 */
	@RequestMapping("deleteCompany/{fdid}")
	@ResponseBody
	public JSONObject deleteCompany(@PathVariable(value = "fdid") String fdid) {
		JSONObject j = new JSONObject();
		returnSuccessJson(j);
		Company c = new Company();
		c.setFdid(fdid);
		try {
			companyDao.deleteCompany(c);
		} catch (RbackException e) {
			j = new JSONObject();
			returnFailJson(j, e.getMsg());
		} finally {
			return j;
		}
	}

	/****
	 * 打开数据字典页面
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("openDataDict/{params}")
	public String dataDictPage(@PathVariable("params") String params,
			Model model, @ModelAttribute DataDict c) {
		return "system/app/dataDictPage";
	}

	@RequestMapping(value = "getDataDictList/{parent_id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public JSONArray getDataDictList(
			@PathVariable(value = "parent_id") String parent_id)
			throws Exception {

		DataDict json = systemService.getAllDataDict(parent_id);
		return (JSONArray) JSONArray.parse("[" + json.toString() + "]");
	}

	@RequestMapping(value = "getCompanyTree/{parent_id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public JSONArray getCompanyTree(
			@PathVariable(value = "parent_id") String parent_id)
			throws Exception {
		if ("-9".equals(parent_id)) {
			Session session = getCurrentSession();
			parent_id = ((User) session.getAttribute("CurrentUser"))
					.getCompany_id();
		}
		Company json = systemService.getCompanyTree(parent_id);
		return (JSONArray) JSONArray.parse("[" + json.toString() + "]");
	}

	/****
	 * 用于页面获取下拉菜单(统一口径)
	 * 
	 * @param parent_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDataDict/{parent_id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public JSONArray getDataDict(
			@PathVariable(value = "parent_id") String parent_id)
			throws Exception {
		List<DataDict> list = systemService.getChildrenDictList(parent_id);
		return (JSONArray) JSONArray.toJSON(list);
	}

	/****
	 * 打开新建组织页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("openCreateCompany")
	public String openCreateCompany(Model model) throws Exception {
		return "system/company/createCompanyPage";
	}

	/***
	 * 这个节点为父节点，是否可以 增加子节点，修改本身，删除本身
	 * 
	 * @param fdid
	 * @return
	 */
	@RequestMapping("addNewDict")
	@ResponseBody
	public JSONObject addNewDict(@Valid @ModelAttribute DataDict d,
			BindingResult result, Model model) {
		JSONObject json = new JSONObject();
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			json.put("res", "1");
			returnFailJson(json, error.getDefaultMessage());
		}
		returnSuccessJson(json);
		TdataDict dd = new TdataDict();
		PubFun.copyProperties(dd, d);
		if (!"".equals(dd.getFdid())) {
			try {
				systemDao.updateDataDict(dd);
			} catch (RbackException e) {
				json = new JSONObject();
				json.put("res", "1");
				returnFailJson(json, e.getMsg());
			} finally {
				return json;
			}

		}
		dd.setFdid(getUUID());
		try {
			systemDao.createDataDict(dd);
		} catch (RbackException e) {
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, e.getMsg());
		}
		return json;
	}

	/*****
	 * 系统通用Action
	 * 
	 * @param path1
	 *            模块路径
	 * @param path2
	 *            领域路径
	 * @param pageName
	 *            页面名称(必须含Page)
	 * @param param
	 *            例如ID等参数
	 * @return
	 */
	@RequestMapping("gotoIframe/{path1}/{path2}/{pageName}/{param}")
	public String gotoIframe(@PathVariable(value = "path1") String path1,
			@PathVariable(value = "path2") String path2,
			@PathVariable(value = "pageName") String pageName,
			@PathVariable(value = "param") String param, Model model) {

		if ("addNewDictPage".equalsIgnoreCase(pageName)
				|| "editDictPage".equalsIgnoreCase(pageName)) {
			DataDict d = new DataDict();
			PubFun.copyProperties(d, systemDao.getThisDataDictInfo(param));
			d.setFdid(param);
			model.addAttribute("model", d);
		}

		return path1 + "/" + path2 + "/" + pageName;
	}

	@RequestMapping("getThisDataDictInfo/{fdid}")
	@ResponseBody
	public JSONObject getThisDataDictInfo(
			@PathVariable(value = "fdid") String fdid) {
		return (JSONObject) JSONObject.toJSON(systemDao
				.getThisDataDictInfo(fdid));
	}

	/****
	 * 删除数据字典数据
	 * 
	 * @param fdid
	 *            记录ID
	 * @return
	 */
	@RequestMapping("deleteDict/{fdid}")
	@ResponseBody
	public JSONObject deleteDict(@PathVariable String fdid) {
		JSONObject j = new JSONObject();
		returnSuccessJson(j);
		try {
			systemDao.deleteDict(fdid);
		} catch (RbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnFailJson(j, e.getMsg());
		} finally {
			return j;
		}
	}

	/****
	 * 页面相关权限列表
	 * 
	 * @param param
	 *            其他参数
	 * @param model
	 *            UIModel
	 * @param e
	 *            传入对象
	 * @param request
	 *            请求
	 * @return
	 */
	@RequestMapping("elementLevelList/{param}")
	@ResponseBody
	public JSONObject elementLevelList(@PathVariable("param") String param,
			Model model, @ModelAttribute ElementLevel e,
			HttpServletRequest request) {
		JSONObject json = new JSONObject();
		e.setPaginationEnable("1");
		List<ElementLevel> list = systemService.getElementLevelList(e);
		e.setPaginationEnable("0");
		List<ElementLevel> listTotal = systemService.getElementLevelList(e);
		if (listTotal != null && listTotal.size() > 0) {
			json.put("total", listTotal.size());
		} else {
			json.put("total", 0);
		}
		json.put("rows", JSONObject.toJSON(list));
		return json;
	}

	@RequestMapping("getPostionList")
	@ResponseBody
	public JSONArray getallpostion(){
		Postion e  = new Postion();
		e.setPaginationEnable("0");
		List<Postion> listTotal = systemService.getPostionList(e);
		return (JSONArray)JSONArray.toJSON(listTotal);
	}
	
	@RequestMapping("postionList/{params}")
	@ResponseBody
	public JSONObject postionList(@PathVariable String params,
			@ModelAttribute Postion e) {
		JSONObject json = new JSONObject();
		e.setPaginationEnable("1");
		List<Postion> list = systemService.getPostionList(e);
		e.setPaginationEnable("0");
		List<Postion> listTotal = systemService.getPostionList(e);
		if (listTotal != null && listTotal.size() > 0) {
			json.put("total", listTotal.size());
		} else {
			json.put("total", 0);
		}
		json.put("rows", JSONObject.toJSON(list));
		return json;
	}

	/***
	 * 角色列表请求
	 * 
	 * @param params
	 * @param r
	 * @return
	 */
	@RequestMapping("roleList/{params}")
	@ResponseBody
	public JSONObject roleList(@PathVariable String params,
			@ModelAttribute Role r) {
		JSONObject json = new JSONObject();
		Trole e = new Trole();
		copyProperties(e, r);
		e.setPaginationEnable("1");
		List<Trole> list = systemDao.getRoleList(e);
		for(Trole t:list){
			Trole trole = systemDao.getRole(t.getParent_id());
			t.setParent_role_name(trole!=null?trole.getRole_name():"无");
		}
		e.setPaginationEnable("0");
		List<Trole> listTotal = systemDao.getRoleList(e);
		if (listTotal != null && listTotal.size() > 0) {
			json.put("total", listTotal.size());
		} else {
			json.put("total", 0);
		}
		json.put("rows", JSONObject.toJSON(list));
		return json;
	}

	/***
	 * 获取所有的角色
	 * @return
	 */
	@RequestMapping("getAllRoles")
	@ResponseBody
	public JSONArray getAllRoles(){
		Trole e = new Trole();
		e.setPaginationEnable("0");
		List<Trole> listTotal = systemDao.getRoleList(e);
		return (JSONArray)JSONArray.toJSON(listTotal);
	}
	
	@RequestMapping("userList/{params}")
	@ResponseBody
	public JSONObject userList(@PathVariable String params,
			@ModelAttribute User u) {
		JSONObject json = new JSONObject();
		Tuser e = new Tuser();
		copyProperties(e, u);
		e.setPaginationEnable("1");
		List<Tuser> list = systemDao.getUserList(e);
		e.setPaginationEnable("0");
		List<Tuser> listTotal = systemDao.getUserList(e);
		if (listTotal != null && listTotal.size() > 0) {
			json.put("total", listTotal.size());
		} else {
			json.put("total", 0);
		}
		json.put("rows", JSONObject.toJSON(list));
		return json;
	}

	/***
	 * 新建岗位
	 * 
	 * @param p
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping("createPostion")
	@ResponseBody
	public JSONObject createPostion(@Valid @ModelAttribute Postion p,
			BindingResult result, Model model) {
		JSONObject json = new JSONObject();
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			json.put("res", "1");
			returnFailJson(json, error.getDefaultMessage());
		}
		returnSuccessJson(json);

		Tpostion dd = new Tpostion();
		PubFun.copyProperties(dd, p);
		try {
			dd.setFdid(getUUID());
			systemDao.createPostion(dd);
		} catch (RbackException e) {
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, e.getMsg());
		} finally {
			return json;
		}
	}

	/***
	 * 删除岗位
	 * 
	 * @param fdid
	 * @return
	 */
	@RequestMapping("deletePostion/{fdid}")
	@ResponseBody
	public JSONObject deletePostion(@PathVariable String fdid) {
		JSONObject json = new JSONObject();
		returnSuccessJson(json);
		if (StringUtils.isEmpty(fdid)) {
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, "请选择记录");
			return json;
		}
		try {
			if (systemDao.getPostion(fdid).getUser_count() > 0) { // 岗位上有人
				json = new JSONObject();
				json.put("res", "1");
				returnFailJson(json, "此岗位有用户正在使用");
				return json;
			}
			systemDao.deletePostion(fdid);
		} catch (RbackException e) {
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, e.getMsg());
		} finally {
			return json;
		}
	}

	@RequestMapping("deleteRole/{fdid}")
	@ResponseBody
	public JSONObject deleteRole(@PathVariable String fdid) {
		JSONObject json = new JSONObject();
		returnSuccessJson(json);
		if (StringUtils.isEmpty(fdid)) {
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, "请选择记录");
			return json;
		}
		try {
			if (systemDao.getRole(fdid).getUser_count() > 0) { // 岗位上有人
				json = new JSONObject();
				json.put("res", "1");
				returnFailJson(json, "此角色有用户正在使用");
				return json;
			}
			systemDao.deleteRole(fdid);
		} catch (RbackException e) {
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, e.getMsg());
		} finally {
			return json;
		}
		
	}
	
	@RequestMapping("createCompany")
	@ResponseBody
	public JSONObject createCompany(@Valid @ModelAttribute Company c,BindingResult result) {
		fillCommonProptities(c,true);
		JSONObject json = new JSONObject();
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			json.put("res", "1");
			returnFailJson(json, error.getDefaultMessage());
			return json;
		}
		returnSuccessJson(json);
		Tcompany cc = new Tcompany();
		PubFun.copyProperties(cc,c);
		try {
			systemDao.createCompany(cc);
		} catch (RbackException e) {
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, e.getMsg());
		}
		return json;
	}

	/****
	 * 打开编辑页
	 * @return
	 */
	@RequestMapping("openEditCompany/{fdid}")
	public String openEditCompany(@PathVariable String fdid,Model model){
		model.addAttribute("company",systemDao.getCompany(fdid));
		return "system/company/editCompanyPage";
	}
	
	/****
	 * 保存更新组织信息
	 * @param c
	 * @param result
	 * @return
	 */
	@RequestMapping("updateCompany")
	@ResponseBody
	public JSONObject updateCompany(@Valid @ModelAttribute Company c,BindingResult result){
		fillCommonProptities(c,false);
		JSONObject json = new JSONObject();
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			json.put("res", "1");
			returnFailJson(json, error.getDefaultMessage());
			return json;
		}
		returnSuccessJson(json);
		Tcompany cc = new Tcompany();
		PubFun.copyProperties(cc,c);
		try {
			systemDao.updateCompany(cc);
		} catch (RbackException e) {
			e.printStackTrace();
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, e.getMsg());
		}
		return json;
	}
	
	/****
	 * 请求，获取当前用户的所有角色
	 * @return
	 */
	@RequestMapping("getMyRoles")
	@ResponseBody
	public JSONArray getMyRoles(){
		Session session = getCurrentSession();
		String user_id = ((User) session.getAttribute("CurrentUser")).getFdid();
		List<Trole> rls = userDao.getUserRoleInfo(user_id);
		return (JSONArray)JSONArray.toJSON(rls);
	}
	
	/****
	 * (请求)根据角色id获取其拥有的所有菜单
	 * @param fdid
	 * @return
	 */
	@RequestMapping("getMenuListByRoleId/{fdid}")
	@ResponseBody
	public JSONArray getMenuListByRoleId(@PathVariable String fdid){
		String role_id =  fdid;
		List<Menu> rls = systemDao.getMenuListByRoleId(role_id);
		List<Menu> rls2 = new ArrayList<>();
		for(Menu mm:rls){
			for(Menu mm2:rls){
				if(mm.getFdid().equalsIgnoreCase(mm2.getParent_id()))
					rls2.add(mm);
			}
		}
		for(Menu m:rls2){
			rls.remove(m);
		}
		return (JSONArray)JSONArray.toJSON(rls);
	}
	/***
	 * 获取到系统中数据字典里配置的所有相关于
	 * 页面权限的类型
	 * @param type(传入到dict_remark) PageAuth 指页面权限类型,
	 * @return
	 */
	@RequestMapping("getALLAuths/{type}")
	@ResponseBody
	public JSONArray getALLAuths(@PathVariable String type){
		List<TdataDict> rls = systemDao.getALLPageAuths(type);
		return (JSONArray)JSONArray.toJSON(rls);	 
	}
	
	/****
	 * 根据值获取数据字典记录
	 * @param value
	 * @return
	 */
	@RequestMapping("getDictNameByValue/{value}")
	@ResponseBody
	public JSONArray getDictNameByValue(@PathVariable String value) {
		TdataDict dd = systemDao.getDictByValue(value);
		List<TdataDict> list = systemDao.getDataDictList(dd.getFdid()); 
		return (JSONArray)JSONArray.toJSON(list);
	}
	/****
	 * 新建页面权限
	 * @param e
	 * @param result
	 * @return
	 */
	@RequestMapping("createElementLevel")
	@ResponseBody
	public JSONObject createElementLevel(@Valid @ModelAttribute ElementLevel e,BindingResult result){
		fillCommonProptities(e,true);
		JSONObject json = new JSONObject();
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			json.put("res", "1");
			returnFailJson(json, error.getDefaultMessage());
			return json;
		}
		returnSuccessJson(json);
		TelementLevel cc = new TelementLevel();
		PubFun.copyProperties(cc,e);
		try {
			systemDao.createElementLevel(cc);
		} catch (RbackException ee) {
			ee.printStackTrace();
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, ee.getMsg());
		}
		return json;
	}
	
	/***
	 * 请求，删除页面权限
	 * @param fdid
	 * @return
	 */
	@RequestMapping("deleteELE/{fdid}")
	@ResponseBody
	public JSONObject deleteELE(@PathVariable String fdid){
		JSONObject json = new JSONObject();
		returnSuccessJson(json);
		if (StringUtils.isEmpty(fdid)) {
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, "请选择记录");
			return json;
		}
		try {
			systemDao.deleteELE(fdid);
		} catch (RbackException e) {
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, e.getMsg());
		} finally {
			return json;
		}
	}
	
	
	@RequestMapping("createRole")
	@ResponseBody
	public JSONObject createRole(@Valid @ModelAttribute Role role,BindingResult result){
		fillCommonProptities(role,true);
		JSONObject json = new JSONObject();
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			json.put("res", "1");
			returnFailJson(json, error.getDefaultMessage());
			return json;
		}
		returnSuccessJson(json);
		Trole cc = new Trole();
		PubFun.copyProperties(cc,role);
		try {
			systemDao.createRole(cc);
		} catch (RbackException e) {
			e.printStackTrace();
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, e.getMsg());
		}
		return json;
	}
	
	@RequestMapping("getRoleDetail/{fdid}")
	@ResponseBody
	public JSONObject getRole(@PathVariable String fdid,Model model){
		return (JSONObject)JSONObject.toJSON(systemDao.getRole(fdid)); 
	}
	/***
	 * 打开角色编辑页
	 * @param fdid
	 * @param model
	 * @return
	 */
	@RequestMapping("openEditRole/{fdid}")
	public String openEditRole(@PathVariable String fdid,Model model){
		model.addAttribute("role",systemDao.getRole(fdid));
		return "system/role/roleEditPage";
	}
	
	/***
	 * 根据角色id查询其菜单树
	 * @param role_id
	 * @return
	 */
	@RequestMapping("getMenuAuthTreeJsonByRoleId/{role_id}")
	@ResponseBody
	public JSONArray getMenuAuthTreeJsonByRoleId(@PathVariable String role_id){
		
		return systemService.getMenuAuthTreeJsonByRoleId(role_id);
	}
	@RequestMapping("getMenuArrayByRoleId/{role_id}")
	@ResponseBody
	public JSONArray getMenuArrayByRoleId(@PathVariable String role_id){
		List<Menu> list = systemDao.getMenuAuthTreeJsonByRoleId(role_id);
		return (JSONArray)JSONArray.toJSON(list);
	}
	
	@RequestMapping("getOperationsByMenuId/{menu_id}/{role_id}/{parent_id}")
	@ResponseBody
	public JSONObject getOperationsByMenuId(@PathVariable(value="menu_id") String menu_id,@PathVariable(value="parent_id") String parent_id,
			@PathVariable(value="role_id") String role_id){
		JSONObject json = new  JSONObject();
		List<Toperation> oplist_p = systemDao.getOperationsByMenuId(menu_id, parent_id);
		List<Toperation> oplist = systemDao.getOperationsByMenuId(menu_id, role_id);
		if(oplist_p==null||oplist_p.size()<1){
			json.put("res", "1");
			json.put("msg", "无数据");
			return json ;
		}
		json.put("res", "2");
		json.put("msg", "存在数据");
		for(Toperation t:oplist_p){
			if(oplist!=null){
				for(Toperation p:oplist){
					if(p.getFdid().equals(t.getFdid())){
						t.setChecked("1");
					}
				}
			}
		}
		json.put("list", JSONArray.toJSON(oplist_p));
		
		return json ;
	}
	
	@RequestMapping("getElementsByMenuId/{menu_id}/{role_id}/{parent_id}")
	@ResponseBody
	public JSONObject getElementsByMenuId(@PathVariable(value="menu_id") String menu_id,@PathVariable(value="parent_id") String parent_id,
			@PathVariable(value="role_id") String role_id){
		JSONObject json = new  JSONObject();
		List<TpageElement> elelist_p = systemDao.getElementsByMenuId(menu_id, parent_id);
		List<TpageElement> elelist = systemDao.getElementsByMenuId(menu_id, role_id);
		if(elelist==null||elelist.size()<1){
			json.put("res", "1");
			json.put("msg", "无数据");
			return json ;
		}
		json.put("res", "2");
		json.put("msg", "存在数据");
		for(TpageElement t:elelist_p){
			for(TpageElement p:elelist){
				if(p.getFdid().equals(t.getFdid())){
					t.setChecked("1");
				}
			}
		}
		json.put("list", JSONArray.toJSON(elelist));
		
		return json ;
	}
	
	/****
	 * 角色列表，修改角色
	 * 即为新角色分配权限
	 * @param role
	 * @param result
	 * @return
	 */
	@RequestMapping("updateRole")
	@ResponseBody
	public JSONObject updateRole(@Valid @ModelAttribute RoleAllot roleAllot,BindingResult result){
		fillCommonProptities(roleAllot,true);
		JSONObject json = new JSONObject();
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			json.put("res", "1");
			returnFailJson(json, error.getDefaultMessage());
			return json;
		}
		returnSuccessJson(json); 
		try {
			systemService.allotOrUpdateRole(roleAllot);
		} catch (RbackException e) {
			e.printStackTrace();
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, e.getMsg());
		}
		return json;
		
	}
	
	/****
	 * 删除用户
	 * @param fdid
	 * @return
	 */
	@RequestMapping("deleteUser/{fdid}")
	@ResponseBody
	public JSONObject deleteUser(@PathVariable String fdid){
		JSONObject json = new JSONObject();
		returnSuccessJson(json);
		if (StringUtils.isEmpty(fdid)) {
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, "请选择记录");
			return json;
		}
		try {
			systemDao.deleteUser(fdid);
		} catch (RbackException e) {
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, e.getMsg());
		} finally {
			return json;
		}
		
	}
	
	@RequestMapping("createUser")
	@ResponseBody
	public JSONObject createUser(@Valid @ModelAttribute User user,BindingResult result){
		fillCommonProptities(user,true);
		JSONObject json = new JSONObject();
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			json.put("res", "1");
			returnFailJson(json, error.getDefaultMessage());
			return json;
		}
		returnSuccessJson(json); 
		try {
			user.setPassword(getUserPasswordShiro(user.getUser_cd(),PropertiesUtil.getValueByKey("system.passwd"),"MD5",10));
			systemService.createUser(user);
		} catch (RbackException e) {
			e.printStackTrace();
			json = new JSONObject();
			json.put("res", "1");
			returnFailJson(json, e.getMsg());
		}
		return json;
	}
		
}

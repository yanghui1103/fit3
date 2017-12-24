package com.bw.fit.system.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.bw.fit.system.entity.TdataDict;
import com.bw.fit.system.model.Company;
import com.bw.fit.system.model.DataDict;
import com.bw.fit.system.model.LogUser;
import com.bw.fit.system.model.Menu;
import com.bw.fit.system.model.User;
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
			BindingResult result, HttpServletRequest request,
			 Model model) {
		Session session = null ;
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


		/****开始shiro登录*****/
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUser_cd(),user.getPasswd());
			Subject currentUser = SecurityUtils.getSubject();
			token.setRememberMe(true);
			currentUser.login(token);
			session = currentUser.getSession();
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
			model.addAttribute("errorMsg", "登录失败,认证拦截:"+e.getMessage());
			return "common/loginPage";
		}

		/***
		 * 是否可以俩处登录
		 */
//		if ("false".equalsIgnoreCase(PropertiesUtil
//				.getValueByKey("user.multi.login"))) {
//			JSONObject j = systemService.getOnLineSituation(session, user,
//					request.getServletContext());
//			if ("1".equals(j.get("res"))) {
//				model.addAttribute("errorMsg", j.get("msg"));
//				return "common/loginPage";
//			}
//		}
		User uu = systemService.getCurrentUserInfo(user.getUser_cd());
		session.setAttribute("CurrentUser",uu);
		return  "common/indexPage";
	}
	
	/*****
	 * 当前用户，的所有菜单权限
	 * 拼接为JSON-父子结构
	 * @param session
	 * @return
	 */
	@RequestMapping(value="getMenuAuthTreeJson",produces = "application/json; charset=utf-8")
	@ResponseBody
	public JSONArray getMenuAuthTreeJson(){ 
		Session session = PubFun.getCurrentSession();
		String user_id = ((User)session.getAttribute("CurrentUser")).getFdid() ;
		return systemService.getMenuTreeJsonByUserId(user_id);
	}
	
	/*****
	 * 菜单id，查询其对应的URL
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="getFrameUrlByMenuId",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getFrameUrlByMenuId(@RequestParam(value="menuId") String menuId){
		JSONObject json = new JSONObject();
		Menu menu = (Menu)daoTemplete.getOneData("systemSql.getFrameUrlByMenuId", menuId); 
		if(StringUtils.isBlank(menu.getMenu_path())||"".equals(menu.getMenu_path())||"-9".equals(menu.getMenu_path())){
			json.put("res", "1");
			return json.toJSONString() ;
		}else{
			return JSONObject.toJSONString(menu);
		} 
	}
	@RequestMapping("openCompanyList/{params}")
	public String openCompanyList(@PathVariable("params") String params){
		return "system/company/companyListPage";
	}
	/*****
	 * 查询组织管理列表
	 * @param params 
	 * @param model UI-Model
	 * @param c 组织
	 * @param request 请求
	 * @param session 会话
	 * @return
	 */
	@RequestMapping("companyList/{params}")
	@ResponseBody
	public JSONObject companyList(@PathVariable("params") String params,
			Model model, @ModelAttribute Company c,HttpServletRequest request
			) { 
		Session session = getCurrentSession();
		JSONObject json = new JSONObject();
		c.setPaginationEnable("1");
		List<Company> list = companyDao.getCompanyList(c);
		if(list!=null&&list.size()>0){
			for(Company cc:list){
				TdataDict d = (systemDao.getDictByValue(cc.getCompany_type_cd()));
				if(d!=null){
					cc.setCompany_type_name(d.getDict_name());
				}
			}
		}
		c.setPaginationEnable("0");
		List<Company> listTotal = companyDao.getCompanyList(c);
		if(listTotal!=null&&listTotal.size()>0){
			json.put("total", listTotal.size()); 
		}else{
			json.put("total", 0); 
		}
		json.put("rows", JSONObject.toJSON(list));
		return json ;
	}
	/*****
	 * 获取此用户在此页面的功能权限
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
		json = systemService.getOperationsByMenuId(((User) session.getAttribute("CurrentUser")).getFdid(),BtnPrefixCode);
		return json;
	} 
	/*****
	 * 删除组织
	 * @param fdid
	 * @return
	 */
	@RequestMapping("deleteCompany/{fdid}")
	@ResponseBody
	public JSONObject deleteCompany(@PathVariable(value="fdid") String fdid){
		JSONObject j = new JSONObject(); 
		returnSuccessJson(j);
		Company c =  new Company();
		c.setFdid(fdid);
		try {
			companyDao.deleteCompany(c);
		} catch (RbackException e) {
			j = new JSONObject(); 
			returnFailJson(j,e.getMsg());			
		}finally{
			return j;
		}
	}
	
	/****
	 * 打开数据字典页面
	 * @param params
	 * @return
	 */
	@RequestMapping("openDataDict/{params}")
	public String dataDictPage(@PathVariable("params") String params,
			Model model, @ModelAttribute DataDict c) {  
		return "system/app/dataDictPage";
	}
	
	@RequestMapping(value="getDataDictList/{parent_id}",produces = "application/json; charset=utf-8")
	@ResponseBody
	public JSONArray getDataDictList(@PathVariable(value="parent_id") String parent_id ) throws Exception{
				
		DataDict json  = systemService.getAllDataDict(parent_id);
		return (JSONArray)JSONArray.parse("["+json.toString()+"]"); 
	}

	/****
	 * 用于页面获取下拉菜单(统一口径)
	 * @param parent_id 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getDataDict/{parent_id}",produces = "application/json; charset=utf-8")
	@ResponseBody
	public JSONArray getDataDict(@PathVariable(value="parent_id") String parent_id ) throws Exception{				
		List<DataDict> list = systemService.getChildrenDictList(parent_id);
		return (JSONArray)JSONArray.toJSON(list); 
	}

	/****
	 * 打开新建组织页
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("openCreateCompany")
	public String openCreateCompany(Model model) throws Exception{  
		return "system/company/createCompanyPage";
	}
	
	/***
	 * 这个节点为父节点，是否可以
	 * 增加子节点，修改本身，删除本身
	 * @param fdid
	 * @return
	 */
	@RequestMapping("addNewDict/{fdid}")
	@ResponseBody
	public JSONObject addNewDict(@PathVariable(value="fdid") String fdid){
		TdataDict d = systemDao.getThisDataDictInfo(fdid);
		DataDict dd = new DataDict();
		copyProperties(dd, d);
		
		return (JSONObject)JSONObject.toJSON(dd) ;
	} 
	
	
	/*****
	 * 系统通用Action
	 * @param path1 模块路径
	 * @param path2 领域路径
	 * @param pageName 页面名称(必须含Page)  
	 * @param param 例如ID等参数
	 * @return
	 */
	@RequestMapping("gotoIframe/{path1}/{path2}/{pageName}/{param}")
	public String gotoIframe(@PathVariable(value="path1") String path1,
			@PathVariable(value="path2") String path2
			,@PathVariable(value="pageName") String pageName,
			@PathVariable(value="param") String param){
		
		return path1+"/"+path2+"/"+pageName;
	}
	
	
	@RequestMapping("deleteDict/{fdid}")
	@ResponseBody
	public JSONObject deleteDict(@PathVariable  String fdid){
		JSONObject j = new JSONObject();
		returnSuccessJson(j);
		try {
			systemDao.deleteDict(fdid);
		} catch (RbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnFailJson(j,e.getMsg());
		}finally{
			return j;
		}
	}
}

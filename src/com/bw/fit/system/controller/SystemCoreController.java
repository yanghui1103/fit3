package com.bw.fit.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.bw.fit.common.util.PubFun.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
import com.bw.fit.common.util.PropertiesUtil; 
import com.bw.fit.system.model.Company;
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
	private DaoTemplete daoTemplete ;

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
			HttpSession session, Model model) {
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

		/***
		 * 是否可以俩处登录
		 */
		if ("false".equalsIgnoreCase(PropertiesUtil
				.getValueByKey("user.multi.login"))) {
			JSONObject j = systemService.getOnLineSituation(session, user,
					request.getServletContext());
			if ("1".equals(j.get("res"))) {
				model.addAttribute("errorMsg", j.get("msg"));
				return "common/loginPage";
			}
		}

		/****开始shiro登录*****/
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUser_cd(),user.getPasswd());
			org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
			token.setRememberMe(true);
			currentUser.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
			model.addAttribute("errorMsg", "登录失败,认证拦截:"+e.getMessage());
			return "common/loginPage";
		}
		
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
	public JSONArray getMenuAuthTreeJson(HttpSession session){ 
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
			Model model, @ModelAttribute Company c,HttpServletRequest request,
			HttpSession session) { 
		JSONObject json = new JSONObject();
		
		return json ;
	}
	
}

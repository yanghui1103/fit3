package com.bw.fit.common.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import static com.bw.fit.common.util.PubFun.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.apache.shiro.authc.*;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.util.PropertiesUtil;
import com.bw.fit.common.util.PubFun;
import com.bw.fit.system.dao.UserDao;
import com.bw.fit.system.entity.Tuser;
import com.bw.fit.system.model.LogUser;
import com.bw.fit.system.service.SystemService;
/*****
 * 登录验证_shrio
 * @author yangh
 *
 */
public class VerificationRealm extends AuthorizingRealm  {
	@Autowired
	private UserDao userDao;
	private Log log = LogFactory.getLog(this.getClass());	
	/*****
	 * 认证权限是否符合
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		//1,把AuthenticationToken 转化为UsernamePasswordToken
		UsernamePasswordToken token =(UsernamePasswordToken)arg0;		
		//2.从UsernamePasswordToken 获取username
		String userName = token.getUsername();
		//3.数据库中查询用户记录
		LogUser user =new LogUser();
		user.setUser_cd(userName);
		Tuser u1 = userDao.getUserById(userDao.getUserIdByCd(user.getUser_cd()));
		if (u1 ==null) {
			throw new AuthenticationException("用户不存在");
		}
		copyProperties(user, u1);
		//5,最后返回的用户信息，
		Object principal = userName;
		Object credentials = user.getPassword();
		//6 盐值
		ByteSource salt = ByteSource.Util.bytes(PropertiesUtil.getValueByKey("user.pw.slogmm") + userName );
		SimpleAuthenticationInfo info = null ;
		info = new SimpleAuthenticationInfo(principal,credentials,salt,getName());
		return info;
	}
	/****
	 * 授权时候用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		//1，从PrincipalCollection中获取登录用户信息
		Object principal = arg0.getPrimaryPrincipal();
		//2,利用当前用户的权限/角色,此处数据出自数据库查询
		Set<String> roles = new HashSet<>();
		roles.add("user");
		//3,创建SimpleAuthorizationInfo,并设置其roles属性
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		//4，返回SimpleAuthorizationInfo
		return null;
	}
  

}

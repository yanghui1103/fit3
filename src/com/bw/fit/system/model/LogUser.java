package com.bw.fit.system.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.bw.fit.common.model.BaseModel;

/*****
 * 登录用户(领域对象)
 * @author yangh
 *
 */
public class LogUser extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	@NotEmpty(message="{user.login.cd}")
	private String user_cd ;
	private String user_name;
	@NotEmpty(message="{user.login.pwd}")
	private String passwd;
	private String pwd_mm;
	private String ip; 
	private String mac ;
	private String company_id;
	private String company_name; 
	private String menuAuthTreeJson; 
	@NotEmpty(message="{verificationCode.empty}")
	private String verificationCode;
	
	

	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public String getMenuAuthTreeJson() {
		return menuAuthTreeJson;
	}
	public void setMenuAuthTreeJson(String menuAuthTreeJson) {
		this.menuAuthTreeJson = menuAuthTreeJson;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	} 
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPwd_mm() {
		return pwd_mm;
	}
	public void setPwd_mm(String pwd_mm) {
		this.pwd_mm = pwd_mm;
	}
	public String getUser_cd() {
		return user_cd;
	}
	public void setUser_cd(String user_cd) {
		this.user_cd = user_cd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
}

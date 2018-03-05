package com.bw.fit.system.model;

import java.io.Serializable;
import java.util.*;

import com.bw.fit.common.model.BaseModel;

/****
 * 用户领域模型
 * @author yangh
 *
 */
public class User extends BaseModel implements Serializable{

	private static final long serialVersionUID = 13344598881L;
	private String user_cd ;
	private String user_name ;
	private List<Role> role_list;
	private List<Postion> postion_list;
	private String phone;
	private String user_order ;
	private String company_id ;
	private String[] role_ids;
	private String[] postion_ids;
	private String password ;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String[] getRole_ids() {
		return role_ids;
	}
	public void setRole_ids(String[] role_ids) {
		this.role_ids = role_ids;
	}
	public String[] getPostion_ids() {
		return postion_ids;
	}
	public void setPostion_ids(String[] postion_ids) {
		this.postion_ids = postion_ids;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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
	public List<Role> getRole_list() {
		return role_list;
	}
	public void setRole_list(List<Role> role_list) {
		this.role_list = role_list;
	}
	public List<Postion> getPostion_list() {
		return postion_list;
	}
	public void setPostion_list(List<Postion> postion_list) {
		this.postion_list = postion_list;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUser_order() {
		return user_order;
	}
	public void setUser_order(String user_order) {
		this.user_order = user_order;
	}
	
	
	
}

package com.bw.fit.system.entity;

import org.hibernate.validator.constraints.NotEmpty;

import com.bw.fit.common.entity.BaseEntity;
import com.bw.fit.common.model.BaseModel;

/*****
 * 用户实体(Dao实体)
 * @author yangh
 *
 */
public class Tuser extends BaseEntity{

	private String user_cd ;
	private String company_id ;
	private String user_name; 
	private String password;
	private String phone;
	private String state;
	private String user_order;
	
	
	private String role_id;
	private String postion_id;
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getPostion_id() {
		return postion_id;
	}
	public void setPostion_id(String postion_id) {
		this.postion_id = postion_id;
	}
	public String getUser_order() {
		return user_order;
	}
	public void setUser_order(String user_order) {
		this.user_order = user_order;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}

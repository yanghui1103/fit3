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
	private String user_name; 
	private String password;
	private String phone;
	private String state;
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

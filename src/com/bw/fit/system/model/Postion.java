package com.bw.fit.system.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.bw.fit.common.model.BaseModel;

public class Postion extends BaseModel  implements Serializable{

	private static final long serialVersionUID = 23344598881L;
	@NotEmpty(message="岗位名称不得为空")
	private String postion_name;
	private String desp ;
	private int user_count;
	
	public int getUser_count() {
		return user_count;
	}
	public void setUser_count(int user_count) {
		this.user_count = user_count;
	}
	public String getPostion_name() {
		return postion_name;
	}
	public void setPostion_name(String postion_name) {
		this.postion_name = postion_name;
	}
	public String getDesp() {
		return desp;
	}
	public void setDesp(String desp) {
		this.desp = desp;
	}
	
}

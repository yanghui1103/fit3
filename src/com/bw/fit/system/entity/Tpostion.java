package com.bw.fit.system.entity;

import com.bw.fit.common.entity.BaseEntity;

public class Tpostion extends BaseEntity{

	private String postion_name;
	private String desp ;
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

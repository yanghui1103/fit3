package com.bw.fit.system.entity;

import com.bw.fit.common.entity.BaseEntity;

public class Toperation extends BaseEntity {
	
	private String checked;
	private String operate_name ;
	private String operate_code ;
	private String operate_type ;
	private String operate_target ; // 使用easyUI的样式
	private String foreign_id ;
	private String address ;
	private String action_name ;
	
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getOperate_name() {
		return operate_name;
	}
	public void setOperate_name(String operate_name) {
		this.operate_name = operate_name;
	}
	public String getOperate_code() {
		return operate_code;
	}
	public void setOperate_code(String operate_code) {
		this.operate_code = operate_code;
	}
	public String getOperate_type() {
		return operate_type;
	}
	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}
	public String getOperate_target() {
		return operate_target;
	}
	public void setOperate_target(String operate_target) {
		this.operate_target = operate_target;
	}
	public String getForeign_id() {
		return foreign_id;
	}
	public void setForeign_id(String foreign_id) {
		this.foreign_id = foreign_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAction_name() {
		return action_name;
	}
	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}
	

}

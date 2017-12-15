package com.bw.fit.log.entity;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.entity.BaseEntity;
/****
 * 日志实体类
 * @author yangh
 *
 */
public class TLogInfo extends BaseEntity{

	private String level;
	private String user_id;
	private String menu_id;
	private String operation_id;
	private Date happen_time;
	private JSONObject message;
	private String res;
	private String msg;
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getOperation_id() {
		return operation_id;
	}
	public void setOperation_id(String operation_id) {
		this.operation_id = operation_id;
	}
	public Date getHappen_time() {
		return happen_time;
	}
	public void setHappen_time(Date happen_time) {
		this.happen_time = happen_time;
	}
	public JSONObject getMessage() {
		return message;
	}
	public void setMessage(JSONObject message) {
		this.message = message;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}

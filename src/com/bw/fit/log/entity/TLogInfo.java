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

	private String log_type_id ;// 
	private String params ;
	private String operator_id; 
	private String operate_function ;
	private String operate_target_fdids ;  // 被操作的目标资源fdid集合
	private String ip;
	private String url;
	private String res ;
	private String msg ;
	
	
	
	
	
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getOperate_target_fdids() {
		return operate_target_fdids;
	}
	public void setOperate_target_fdids(String operate_target_fdids) {
		this.operate_target_fdids = operate_target_fdids;
	}
	public String getLog_type_id() {
		return log_type_id;
	}
	public void setLog_type_id(String log_type_id) {
		this.log_type_id = log_type_id;
	}
	public String getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}
	public String getOperate_function() {
		return operate_function;
	}
	public void setOperate_function(String operate_function) {
		this.operate_function = operate_function;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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

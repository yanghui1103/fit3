package com.bw.fit.log.model;

import com.bw.fit.common.model.BaseModel;

/*****
 * 日志组件：日志的领域模型
 * @author yangh
 *
 */
public class LogInfo extends BaseModel{

	private String operator_id; 
	private String operator_name; 
	private String params ;
	private String log_type_id ; 
	private String log_type ; 
	private String operate_function ;
	private String operate_target_fdids ;  // 被操作的目标资源fdid集合
	private String ip;
	private String url;
	private String res ;
	private String res_desp ;
	private String msg ;
	
	
	
	
	public String getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}
	public String getLog_type_id() {
		return log_type_id;
	}
	public void setLog_type_id(String log_type_id) {
		this.log_type_id = log_type_id;
	}
	public String getRes_desp() {
		return res_desp;
	}
	public void setRes_desp(String res_desp) {
		this.res_desp = res_desp;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getOperator_name() {
		return operator_name;
	}
	public void setOperator_name(String operator_name) {
		this.operator_name = operator_name;
	}
	public String getLog_type() {
		return log_type;
	}
	public void setLog_type(String log_type) {
		this.log_type = log_type;
	}
	public String getOperate_function() {
		return operate_function;
	}
	public void setOperate_function(String operate_function) {
		this.operate_function = operate_function;
	}
	public String getOperate_target_fdids() {
		return operate_target_fdids;
	}
	public void setOperate_target_fdids(String operate_target_fdids) {
		this.operate_target_fdids = operate_target_fdids;
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

package com.bw.fit.log.model;

import com.bw.fit.common.model.BaseModel;

/*****
 * 日志组件：日志的领域模型
 * @author yangh
 *
 */
public class LogInfo extends BaseModel{

	private String operator_name; 
	private String log_type ;  
	private String operate_function ;
	private String operate_target_fdids ;  // 被操作的目标资源fdid集合
	private String ip;
	private String url;
	private String res ;
	private String msg ;
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

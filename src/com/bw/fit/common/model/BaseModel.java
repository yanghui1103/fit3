package com.bw.fit.common.model;


import org.apache.commons.lang3.StringUtils;

import com.bw.fit.common.util.Node;

/*****
 * 顶级领域对象
 * @author yangh
 *
 */
public class BaseModel{

	private String fdid ;
	private String keyWords="";	
	private String start_date="1900-01-01" ;
	private String end_date="2099-12-31" ;
	private String create_time ;
	private String version_time ;
	private String creator;
	private String creator_name;
	private String create_company_id;
	private String create_company_name;
	private String logId ;
	private String logContent;
	private String sql ;
	private String res ;
	private String msg ;
	private String returnInfo ;
	private String staff_id;
	private String action_name ;
	private String UUID ;
	private String status ;
	/***
	 * 翻页使用
	 */
	private Integer page =1;
	private Integer rows =20;
	private Integer start_num;
	private Integer end_num;
	private String paginationEnable;
	
	
	
	
	public String getPaginationEnable() {
		return paginationEnable;
	}
	public void setPaginationEnable(String paginationEnable) {
		this.paginationEnable = paginationEnable;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getStart_num() {
		return (page-1)*rows;
	} 
	public Integer getEnd_num() {
		return (page-1)*rows + rows;
	} 
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getAction_name() {
		return action_name;
	}
	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getReturnInfo() {
		return returnInfo;
	}
	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
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
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public String getFdid() {
		return fdid;
	}
	public void setFdid(String fdid) {
		this.fdid = fdid;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getCreate_time() {
		if(!"".equals(create_time)&&create_time!=null&&create_time.contains(".0")){
			create_time = create_time.replace(".0", "");
		}
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getVersion_time() {
		if(!"".equals(version_time)&&version_time!=null&&version_time.contains(".0")){
			version_time = version_time.replace(".0", "");
		}
		return version_time;
	}
	public void setVersion_time(String version_time) {
		this.version_time = version_time;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreator_name() {
		return creator_name;
	}
	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}
	public String getCreate_company_id() {
		return create_company_id;
	}
	public void setCreate_company_id(String create_company_id) {
		this.create_company_id = create_company_id;
	}
	public String getCreate_company_name() {
		return create_company_name;
	}
	public void setCreate_company_name(String create_company_name) {
		this.create_company_name = create_company_name;
	}	
	
	
}

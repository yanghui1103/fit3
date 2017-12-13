package com.bw.fit.common.entity;
import static com.bw.fit.common.util.PubFun.*;

import java.util.Date;
public class BaseEntity {


	private String fdid = getUUID(); 
	private Date create_time ;
	private Date version_time ;
	private String operator_id;
	private String creator_id;
	public String getFdid() {
		return fdid;
	}
	public void setFdid(String fdid) {
		this.fdid = fdid;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getVersion_time() {
		return version_time;
	}
	public void setVersion_time(Date version_time) {
		this.version_time = version_time;
	}
	public String getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}
	public String getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}
	
	
	
	
}

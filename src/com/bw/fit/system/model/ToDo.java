package com.bw.fit.system.model;

import com.bw.fit.common.model.BaseModel;

public class ToDo extends BaseModel {

	private String subject;
	private String app_name;
	private String model_name;
	private String model_id;
	private String url_link;
	private String link_data;
	private String staff_id;
	private String create_time;
	private String flow_id;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public String getModel_id() {
		return model_id;
	}
	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}
	public String getUrl_link() {
		return url_link;
	}
	public void setUrl_link(String url_link) {
		this.url_link = url_link;
	}
	public String getLink_data() {
		return link_data;
	}
	public void setLink_data(String link_data) {
		this.link_data = link_data;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getFlow_id() {
		return flow_id;
	}
	public void setFlow_id(String flow_id) {
		this.flow_id = flow_id;
	}
	
}

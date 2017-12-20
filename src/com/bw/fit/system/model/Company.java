package com.bw.fit.system.model;

import com.bw.fit.common.model.BaseModel;

public class Company  extends BaseModel{

	private String company_name;
	private String company_type_cd ;
	private String company_type_name ;
	private String parent_id;
	private String company_address;
	private String company_order;
	
	
	public String getCompany_type_name() {
		return company_type_name;
	}
	public void setCompany_type_name(String company_type_name) {
		this.company_type_name = company_type_name;
	}
	public String getCompany_order() {
		return company_order;
	}
	public void setCompany_order(String company_order) {
		this.company_order = company_order;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_type_cd() {
		return company_type_cd;
	}
	public void setCompany_type_cd(String company_type_cd) {
		this.company_type_cd = company_type_cd;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
}

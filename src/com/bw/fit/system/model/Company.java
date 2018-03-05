package com.bw.fit.system.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.bw.fit.common.model.BaseModel;
import com.bw.fit.common.util.treeHandler.CompanyChildren;
import com.bw.fit.common.util.treeHandler.DataDictChildren;

public class Company  extends BaseModel  implements Serializable{

	private static final long serialVersionUID = 544598881L;

	private String company_name;
	@NotEmpty(message="请选择组织类型")
	private String company_type_cd ;
	private String company_type_name ;
	@NotEmpty(message="请选择父组织")
	private String parent_id;
	private String parent_company_name;
	private String company_address;
	private String company_order;
	
	
	public String getParent_company_name() {
		return parent_company_name;
	}
	public void setParent_company_name(String parent_company_name) {
		this.parent_company_name = parent_company_name;
	}
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
	
	
	
	
	
	
	

	/**
	 * 孩子节点列表
	 */
	private CompanyChildren children = new CompanyChildren();

	// 先序遍历，拼接JSON字符串
	public String toString() {
		String result = "{" + "id : '" + getFdid() + "'" + ", text : '" + this.getCompany_name() + "'" 					
				+ ", parentId: '" + this.getParent_id() + "'"
				+ ", company_order: '" + this.getCompany_order() + "'"; 
		
		if (children != null && children.getSize() != 0) {
			result += ", children : " + children.toString();
		} else {
			result += ", leaf : true";
		}

		return result + "}";
	}

	// 兄弟节点横向排序
	public void sortChildren() {
		if (children != null && children.getSize() != 0) {
			children.sortChildren();
		}
	}

	// 添加孩子节点
	public void addChild(Company node) {
		this.children.addChild(node);
	}

}

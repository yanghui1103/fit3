package com.bw.fit.system.model;

import com.bw.fit.common.model.BaseModel;

/****
 * 页面元素权限级别领域类
 * @author yangh
 *
 */
public class ElementLevel extends BaseModel{

	private String role_id;
	private String role_name;
	private String menu_id;
	private String menu_name;
	/****
	 * 来自数据字典表
	 */
	private String level_code; 
	private String element_type ;// 页面数据权限分配类；页面字段可视权限；页面某个附件下载/预览；
	private String element_type_name ;
	private String element_type_remark ;
	private String level_desp;
	
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getElement_type_name() {
		return element_type_name;
	}
	public void setElement_type_name(String element_type_name) {
		this.element_type_name = element_type_name;
	}
	public String getLevel_desp() {
		return level_desp;
	}
	public void setLevel_desp(String level_desp) {
		this.level_desp = level_desp;
	}
	public String getElement_type_remark() {
		return element_type_remark;
	}
	public void setElement_type_remark(String element_type_remark) {
		this.element_type_remark = element_type_remark;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getLevel_code() {
		return level_code;
	}
	public void setLevel_code(String level_code) {
		this.level_code = level_code;
	}
	public String getElement_type() {
		return element_type;
	}
	public void setElement_type(String element_type) {
		this.element_type = element_type;
	}
	
}

package com.bw.fit.system.entity;

import com.bw.fit.common.entity.BaseEntity;

public class TpageElement extends BaseEntity {

	private String checked;
	private String actions  ; // 数据字典里取“页面元素权值”
	private String menu_id ;
	private String element_code ;
	private String element_name ;
	private String remark;
	private String element_css ;
	
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getActions() {
		return actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getElement_code() {
		return element_code;
	}
	public void setElement_code(String element_code) {
		this.element_code = element_code;
	}
	public String getElement_name() {
		return element_name;
	}
	public void setElement_name(String element_name) {
		this.element_name = element_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getElement_css() {
		return element_css;
	}
	public void setElement_css(String element_css) {
		this.element_css = element_css;
	}
	
}

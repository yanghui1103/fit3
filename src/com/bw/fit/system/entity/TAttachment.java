package com.bw.fit.system.entity;

import com.bw.fit.common.entity.BaseEntity;

public class TAttachment extends BaseEntity{
	private String file_name;
	private String before_name;
	private String path ;
	private String menu_id ;
	private String foreign_id ;
	private String file_size;
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getBefore_name() {
		return before_name;
	}
	public void setBefore_name(String before_name) {
		this.before_name = before_name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getForeign_id() {
		return foreign_id;
	}
	public void setForeign_id(String foreign_id) {
		this.foreign_id = foreign_id;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	
}

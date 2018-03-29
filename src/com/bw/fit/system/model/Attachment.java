package com.bw.fit.system.model;

import com.bw.fit.common.model.BaseModel;

public class Attachment extends BaseModel {

	private String file_name;
	private String before_name;
	private String path ; 
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

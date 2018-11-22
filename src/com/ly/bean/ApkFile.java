package com.ly.bean;

import java.util.Date;

public class ApkFile {
	private int id;
	private int type;
	private int version;
	private String note;
	private long bdate;
	private Date bddate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public long getBdate() {
		return bdate;
	}
	public void setBdate(long bdate) {
		this.bdate = bdate;
	}
	public Date getBddate() {
		return bddate;
	}
	public void setBddate(Date bddate) {
		this.bddate = bddate;
	}
}

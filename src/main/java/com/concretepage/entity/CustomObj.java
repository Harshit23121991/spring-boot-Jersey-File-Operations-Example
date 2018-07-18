package com.concretepage.entity;

import java.io.Serializable;

public class CustomObj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomObj(String title, String url) {
		super();
		this.title = title;
		this.url = url;
	}
	private String title;
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}

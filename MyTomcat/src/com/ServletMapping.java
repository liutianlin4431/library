package com;

public class ServletMapping {
	private String servletname;
	private String url;
	private String clazz;

	public ServletMapping(String servletname, String url, String clazz) {
		super();
		this.servletname = servletname;
		this.url = url;
		this.clazz = clazz;
	}

	public String getServletname() {
		return servletname;
	}

	public String getUrl() {
		return url;
	}

	public String getClazz() {
		return clazz;
	}

	public void setServletname(String servletname) {
		this.servletname = servletname;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

}

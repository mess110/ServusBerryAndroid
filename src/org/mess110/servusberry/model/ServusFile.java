package org.mess110.servusberry.model;

public class ServusFile {

	private String path;

	public ServusFile(String path) {
		this.path = path;
	}

	public boolean isRootPath() {
		return path.equals("/");
	}

	public String getPath() {
		return path;
	}

}

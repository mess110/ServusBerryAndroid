package org.mess110.servusberry.model;

import org.mess110.servusberry.util.Util;

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

	public String getPrevDir() {
		return Util.prevDir(path);
	}

}

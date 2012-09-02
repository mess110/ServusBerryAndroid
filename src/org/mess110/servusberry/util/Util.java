package org.mess110.servusberry.util;

import android.util.Log;

public class Util {

	public static void log(String text) {
		Log.d(ServusConst.LOG_TAG, text);
	}

	public static String pathJoin(String s1, String s2) {
		if (s1.endsWith("/")) {
			s1 = s1.substring(0, s1.length() - 1);
		}
		if (!s2.startsWith("/")) {
			s1 += "/";
		}
		return s1 + s2;
	}

	public static String prevDir(String path) {
		String s = path;
		if (s.indexOf("/") != -1) {
			s = s.substring(0, s.lastIndexOf("/"));
		}
		if (s.indexOf("/") != -1) {
			s = s.substring(0, s.lastIndexOf("/"));
		}
		return s;
	}
}

package org.mess110.servusberry.util;

import android.content.Context;

// This can be dried up even further
public class API {

	private Preferences pref;

	public API(Context context) {
		pref = new Preferences(context);
	}

	public String files(String path) {
		String result = "";
		String url = pref.getUrl() + "/files" + path;
		try {
			result = Util.executeHttpGet(url);
		} catch (Exception e) {

		}
		return result;
	}

	public String killall() {
		String result = "";
		String url = pref.getUrl() + "/killall";
		try {
			result = Util.executeHttpPost(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// url + '/ping' is the same as url
	public String ping(String url) {
		String result = "";
		try {
			result = Util.executeHttpGet(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String execute(String path) {
		String result = "";
		String url = pref.getUrl() + "/files" + path;
		try {
			result = Util.executeHttpPost(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

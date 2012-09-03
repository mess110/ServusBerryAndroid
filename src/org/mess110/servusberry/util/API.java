package org.mess110.servusberry.util;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONObject;

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

	public String findServusBerryServer() {
		String result = "";
		for (int i = 1; i <= 255; i++) {
			String url = "http://192.168.1." + String.valueOf(i) + ":5000/";
			try {
				String jsonString = Util.executeHttpGet(url, true);
				JSONObject response = new JSONObject(jsonString);
				if (response.has("servusberry")) {
					result = url;
					break;
				}
			} catch (ConnectTimeoutException e) {
				// e.printStackTrace();
			} catch (Exception e) {
				// e.printStackTrace();
			}
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

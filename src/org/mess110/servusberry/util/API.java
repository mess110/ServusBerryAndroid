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
		String url = pref.getUrl() + "files" + path;
		result = Util.executeHttpGet(url);
		return result;
	}

	public String radioStations() {
		String result = "";
		String url = pref.getUrl() + "radio";
		result = Util.executeHttpGet(url);
		return result;
	}

	public String killall() {
		String result = "";
		String url = pref.getUrl() + "killall";
		result = Util.executeHttpPost(url);
		return result;
	}

	// url + '/ping' is the same as url
	public String ping(String url) {
		String result = "";
		result = Util.executeHttpGet(url, true);
		return result;
	}

	public String execute(String path) {
		String result = "";
		String url = pref.getUrl() + "files" + path;
		result = Util.executeHttpPost(url);
		return result;
	}

	public String playRadioStation(String name) {
		String result = "";
		String url = pref.getUrl() + "radio/?name=" + name;
		result = Util.executeHttpPost(url);
		return result;
	}

	public void update() {
		String url = pref.getUrl() + "update";
		Util.executeHttpPost(url);
	}
}

package org.mess110.servusberry.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {
	private SharedPreferences preferences;

	public Preferences(Context context) {
		preferences = context.getSharedPreferences(ServusConst.PREF_KEY, 0);
	}

	public void setPath(String path) {
		Editor editor = preferences.edit();
		editor.putString(ServusConst.PATH_KEY, path);
		editor.commit();
	}

	public String getPath() {
		return preferences.getString(ServusConst.PATH_KEY,
				ServusConst.ROOT_PATH);
	}

	public void setUrl(String url) {
		Editor editor = preferences.edit();
		editor.putString(ServusConst.URL_KEY, url);
		editor.commit();
	}

	public String getUrl() {
		return preferences.getString(ServusConst.URL_KEY,
				ServusConst.DEFAULT_URL);
	}

	// TODO should it be string or int?
	public String getScanPort() {
		return preferences.getString(ServusConst.SCAN_PORT_KEY,
				ServusConst.DEFAULT_SCAN_PORT);
	}

	public void setScanPort(String port) {
		Editor editor = preferences.edit();
		editor.putString(ServusConst.SCAN_PORT_KEY, port);
		editor.commit();
	}
}
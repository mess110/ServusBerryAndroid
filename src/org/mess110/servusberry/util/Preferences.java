package org.mess110.servusberry.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {
	private SharedPreferences preferences;

	public Preferences(Context context) {
		preferences = context.getSharedPreferences(ServusConst.PREF_KEY, 0);
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
}
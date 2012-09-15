package org.mess110.servusberry.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.mess110.servusberry.util.API;
import org.mess110.servusberry.util.Util;

import android.content.Context;

public class ServusBerry {

	private Context context;
	private API api;
	private boolean connected;

	public ServusBerry(Context context) {
		this.context = context;
		this.api = new API(context);
		this.connected = false;
	}

	private boolean isServer(String jsonString) {
		boolean result = false;
		try {
			JSONObject response = new JSONObject(jsonString);
			if (response.has("servusberry")) {
				result = true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void killall() {
		api.killall();
	}

	public boolean ping(String url) {
		String jsonString = api.ping(url);
		return isServer(jsonString);
	}

	public String findServerIpAddr(String ipMask) {
		String result = "";
		for (int i = 1; i <= 255; i++) {
			String url = "http://" + ipMask + String.valueOf(i) + ":5000/";
			String jsonString = Util.executeHttpGet(url, true);
			if (isServer(jsonString)) {
				result = url;
				break;
			}
		}
		return result;
	}

	public void update() {
		api.update();
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean value) {
		this.connected = value;
	}
}

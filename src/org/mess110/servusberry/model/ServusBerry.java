package org.mess110.servusberry.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.mess110.servusberry.util.API;
import org.mess110.servusberry.util.Util;

import android.content.Context;

public class ServusBerry {

	private static ServusBerry instance;

	private API api;
	private boolean connected;
	private Context context;

	private ServusBerry() {
		this.connected = false;
	}

	public void init(Context context) {
		this.context = context;
		this.api = new API(context);
	}

	public static ServusBerry getInstance() {
		if (instance == null) {
			instance = new ServusBerry();
		}
		return instance;
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

	public String findServerIpAddr(String ipMask, String scanPort) {
		String result = "";
		for (int i = 1; i <= 255; i++) {
			String url = "http://" + ipMask + String.valueOf(i) + ":"
					+ scanPort + "/";
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

	public void mute() {
		api.mute();
	}

	public void volDown() {
		api.volumeDown("1000");
	}

	public void volUp() {
		api.volumeUp("1000");
	}

	public Context getContext() {
		return context;
	}
}

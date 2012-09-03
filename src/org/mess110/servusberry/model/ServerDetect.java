package org.mess110.servusberry.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerDetect {

	public static boolean isServer(String jsonString) {
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

}

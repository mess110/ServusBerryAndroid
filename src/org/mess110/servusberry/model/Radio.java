package org.mess110.servusberry.model;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.mess110.servusberry.util.API;
import org.mess110.servusberry.util.Util;

import android.content.Context;

public class Radio {

	private ArrayList<String> stations;
	private API api;
	private Context context;

	public Radio(Context context) {
		this.stations = new ArrayList<String>();
		this.api = new API(context);
		this.context = context;
	}

	public ArrayList<String> getStations() {
		stations = new ArrayList<String>();

		String jsonString = api.radioStations();
		try {
			JSONObject response = new JSONObject(jsonString);

			Iterator<String> iter = response.keys();
			while (iter.hasNext()) {
				stations.add(iter.next());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return stations;
	}

	public void play(String name) {
		String jsonString = api.playRadioStation(name);
		try {
			JSONObject response = new JSONObject(jsonString);
			if (response.has("code")) {
				Util.toast(context, response.getString("message"));
				return;
			}

			// done
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}

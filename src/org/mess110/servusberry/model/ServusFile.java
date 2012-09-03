package org.mess110.servusberry.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mess110.servusberry.util.API;
import org.mess110.servusberry.util.Util;

import android.content.Context;

public class ServusFile {

	private String path;
	private Context context;
	private API http;
	private ArrayList<String> files;

	public ServusFile(String path, Context context) {
		this.path = path;
		this.context = context;
		this.http = new API(context);
		this.files = new ArrayList<String>();
	}

	public boolean isRootPath() {
		return path.equals("/");
	}

	public String getPath() {
		return path;
	}

	public String getPrevDir() {
		return Util.prevDir(path);
	}

	public ArrayList<String> getFiles() {
		return files;
	}

	public void look() {
		files = new ArrayList<String>();

		String jsonString = http.files(path);
		try {
			JSONObject response = new JSONObject(jsonString);
			JSONArray fileNames = response.getJSONArray("files");
			for (int i = 0; i < fileNames.length(); i++) {
				files.add((String) fileNames.get(i));
			}
			
			// get some more info about the file
			// like extension
			// or is-folder
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void execute() {
		String jsonString = http.execute(path);
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

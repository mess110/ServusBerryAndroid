package org.mess110.servusberry;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mess110.servusberry.base.BaseListActivity;
import org.mess110.servusberry.util.HTTPClient;
import org.mess110.servusberry.util.Util;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FileSystemActivity extends BaseListActivity {
	private HTTPClient http;
	private String path;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		http = new HTTPClient(getApplicationContext());

		loadList("/");
	}

	private void loadList(String urlPath) {
		ArrayList<String> files = new ArrayList<String>();

		String jsonString = http.files(urlPath);
		try {
			JSONObject response = new JSONObject(jsonString);
			JSONArray fileNames = response.getJSONArray("files");
			for (int i = 0; i < fileNames.length(); i++) {
				files.add((String) fileNames.get(i));
			}
			path = response.getString("path");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		setListAdapter(new ArrayAdapter<String>(this, R.layout.file_system,
				files));

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String s = (String) (((TextView) view).getText());
				String newPath = Util.pathJoin(path, s);
				Util.log(newPath);
				loadList(newPath);
			}
		});
	}

	public void refresh() {
		loadList(path);
	}

	public void killall() {
		http.killall();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {

			if (!path.equals("/")) {
				String prevDir = Util.prevDir(path);
				loadList(prevDir);
				return false;
			}
			return super.onKeyDown(keyCode, event);
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
}

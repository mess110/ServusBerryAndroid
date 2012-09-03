package org.mess110.servusberry;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mess110.servusberry.base.BaseListActivity;
import org.mess110.servusberry.model.ServusFile;
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

// TODO fix this class
// specifically the JSON part
public class FileSystemActivity extends BaseListActivity {
	private HTTPClient http;
	private ServusFile servusFile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		http = new HTTPClient(getApplicationContext());

		loadList("/");
	}

	public void refresh() {
		// loadList(path);
	}

	public void killall() {
		http.killall();
	}

	private void loadList(String urlPath) {
		servusFile = new ServusFile(urlPath);

		ArrayList<String> files = new ArrayList<String>();

		String jsonString = http.files(servusFile.getPath());
		try {
			JSONObject response = new JSONObject(jsonString);
			JSONArray fileNames = response.getJSONArray("files");
			for (int i = 0; i < fileNames.length(); i++) {
				files.add((String) fileNames.get(i));
			}
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
				String fileName = (String) (((TextView) view).getText());
				String newPath = Util.pathJoin(servusFile.getPath(), fileName);
				loadList(newPath);
			}
		});
	}

	public void execute() {
		String jsonString = http.execute(servusFile.getPath());
		try {
			JSONObject response = new JSONObject(jsonString);
			if (response.has("code")) {
				Util.toast(this, response.getString("message"));
				return;
			}

			// done
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && !servusFile.isRootPath()) {
			String prevDir = servusFile.getPrevDir();
			loadList(prevDir);
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
}

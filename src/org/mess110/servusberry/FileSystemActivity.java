package org.mess110.servusberry;

import org.mess110.servusberry.base.BaseListActivity;
import org.mess110.servusberry.model.ServusFile;
import org.mess110.servusberry.util.API;
import org.mess110.servusberry.util.ServusConst;
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
	private API api;
	private ServusFile servusFile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		api = new API(getApplicationContext());
		
		loadList(ServusConst.ROOT_PATH);
	}

	public void refresh() {
		loadList(servusFile.getPath());
	}

	public void killall() {
		api.killall();
	}

	public void execute() {
		servusFile.execute();
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
	
	private void loadList(String urlPath) {
		servusFile = new ServusFile(urlPath, getApplicationContext());
		servusFile.look();
		updateFileList();
	}

	private void updateFileList() {
		setListAdapter(new ArrayAdapter<String>(this, R.layout.file_system,
				servusFile.getFiles()));

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
}

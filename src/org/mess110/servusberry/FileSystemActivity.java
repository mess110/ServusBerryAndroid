package org.mess110.servusberry;

import org.mess110.servusberry.base.BaseListActivity;
import org.mess110.servusberry.model.ServusFile;
import org.mess110.servusberry.util.ServusConst;
import org.mess110.servusberry.util.Util;

import android.os.Bundle;
import android.view.KeyEvent;

public class FileSystemActivity extends BaseListActivity {
	private ServusFile servusFile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadList(ServusConst.ROOT_PATH);
	}

	public void refresh() {
		loadList(servusFile.getPath());
	}

	public void execute() {
		servusFile.execute();
	}

	private void loadList(String urlPath) {
		servusFile = new ServusFile(urlPath, getApplicationContext());
		updateList(servusFile.look());
	}

	@Override
	public void itemClick(String name) {
		String newPath = Util.pathJoin(servusFile.getPath(), name);
		loadList(newPath);
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

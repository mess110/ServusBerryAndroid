package org.mess110.servusberry.base;

import org.mess110.servusberry.FileSystemActivity;
import org.mess110.servusberry.SettingsActivity;
import org.mess110.servusberry.util.ServusConst;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public abstract class BaseActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ServusConst.ID_MENU_REFRESH, Menu.NONE, "refresh");
		menu.add(Menu.NONE, ServusConst.ID_MENU_SETTINGS, Menu.NONE, "settings");
		menu.add(Menu.NONE, ServusConst.ID_MENU_FILE_SYSTEM, Menu.NONE, "file system");
		menu.add(Menu.NONE, ServusConst.ID_MENU_EXIT, Menu.NONE, "exit");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ServusConst.ID_MENU_EXIT:
			this.finish();
			break;
		case ServusConst.ID_MENU_REFRESH:
			refresh();
			break;
		case ServusConst.ID_MENU_SETTINGS:
			startActivity(SettingsActivity.class);
			break;
		case ServusConst.ID_MENU_FILE_SYSTEM:
			startActivity(FileSystemActivity.class);
			break;
		default:
			break;
		}
		return false;
	}

	public abstract void refresh();

	public void startActivity(Class<?> klass) {
		Intent myIntent = new Intent(getBaseContext(), klass);
		startActivity(myIntent);
	}
}
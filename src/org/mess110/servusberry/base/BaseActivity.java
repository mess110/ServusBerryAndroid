package org.mess110.servusberry.base;

import org.mess110.servusberry.FileSystemActivity;
import org.mess110.servusberry.RadioActivity;
import org.mess110.servusberry.SettingsActivity;
import org.mess110.servusberry.util.Preferences;
import org.mess110.servusberry.util.ServusConst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public abstract class BaseActivity extends Activity {

	protected Preferences pref;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pref = new Preferences(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ServusConst.ID_MENU_SETTINGS, Menu.NONE, "settings");
		menu.add(Menu.NONE, ServusConst.ID_MENU_UPDATE, Menu.NONE, "update");
		menu.add(Menu.NONE, ServusConst.ID_MENU_EXIT, Menu.NONE, "exit");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ServusConst.ID_MENU_EXIT:
			this.finish();
			break;
		case ServusConst.ID_MENU_SETTINGS:
			startActivity(SettingsActivity.class);
			break;
		case ServusConst.ID_MENU_RADIO:
			startActivity(RadioActivity.class);
			break;
		case ServusConst.ID_MENU_FILE_SYSTEM:
			startActivity(FileSystemActivity.class);
			break;
		case ServusConst.ID_MENU_UPDATE:
			update();
			break;
		default:
			break;
		}
		return false;
	}

	public abstract void connect();

	public abstract void update();

	public abstract boolean isConnected();

	public void startActivity(Class<?> klass) {
		if (isConnected()) {
			Intent myIntent = new Intent(getBaseContext(), klass);
			startActivity(myIntent);
		} else {
			connect();
		}
	}
}
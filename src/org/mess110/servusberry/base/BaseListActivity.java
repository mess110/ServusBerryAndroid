package org.mess110.servusberry.base;

import org.mess110.servusberry.util.ServusConst;

import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuItem;

public abstract class BaseListActivity extends ListActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ServusConst.ID_MENU_REFRESH, Menu.NONE, "refresh");
		menu.add(Menu.NONE, ServusConst.ID_MENU_KILL_ALL, Menu.NONE, "killall");
		menu.add(Menu.NONE, ServusConst.ID_MENU_EXIT, Menu.NONE, "exit");

		return true;
	}

	public abstract void refresh();
	public abstract void killall();

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ServusConst.ID_MENU_EXIT:
			this.finish();
			break;
		case ServusConst.ID_MENU_REFRESH:
			refresh();
			break;
		case ServusConst.ID_MENU_KILL_ALL:
			killall();
			break;
		default:
			break;
		}
		return false;
	}
}

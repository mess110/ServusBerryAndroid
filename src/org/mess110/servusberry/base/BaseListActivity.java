package org.mess110.servusberry.base;

import java.util.ArrayList;

import org.mess110.servusberry.R;
import org.mess110.servusberry.util.Preferences;
import org.mess110.servusberry.util.ServusConst;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public abstract class BaseListActivity extends ListActivity {

	protected Preferences pref;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pref = new Preferences(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ServusConst.ID_MENU_PLAY, Menu.NONE, "play");
		menu.add(Menu.NONE, ServusConst.ID_MENU_REFRESH, Menu.NONE, "refresh");
		menu.add(Menu.NONE, ServusConst.ID_MENU_EXIT, Menu.NONE, "exit");

		return true;
	}

	public abstract void refresh();

	public abstract void execute();

	public abstract void itemClick(String name);

	public void updateList(ArrayList<String> listItems) {
		setListAdapter(new ArrayAdapter<String>(this, R.layout.file_system,
				listItems));

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String fileName = (String) (((TextView) view).getText());
				itemClick(fileName);
			}
		});
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
		case ServusConst.ID_MENU_PLAY:
			execute();
			break;
		default:
			break;
		}
		return false;
	}
}

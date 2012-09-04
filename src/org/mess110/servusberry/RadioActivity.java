package org.mess110.servusberry;

import org.mess110.servusberry.base.BaseListActivity;
import org.mess110.servusberry.model.Radio;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RadioActivity extends BaseListActivity {

	private Radio radio;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		radio = new Radio(getApplicationContext());

		refresh();
	}

	@Override
	public void refresh() {
		updateFileList();
	}

	private void updateFileList() {
		setListAdapter(new ArrayAdapter<String>(this, R.layout.file_system,
				radio.getStations()));

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String name = (String) (((TextView) view).getText());
				radio.play(name);
			}
		});
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}

package org.mess110.servusberry;

import org.mess110.servusberry.base.BaseListActivity;
import org.mess110.servusberry.model.Radio;

import android.os.Bundle;

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
		updateList(radio.getStations());
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	public void itemClick(String name) {
		radio.play(name);
	}

}

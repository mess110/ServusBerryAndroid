package org.mess110.servusberry;

import org.mess110.servusberry.base.BaseActivity;
import org.mess110.servusberry.util.API;
import org.mess110.servusberry.util.Preferences;

import android.os.Bundle;
import android.widget.TextView;

public class ServusBerry extends BaseActivity {
	private API api;
	private Preferences pref;
	private TextView servusBerryServer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		pref = new Preferences(this);
		servusBerryServer = (TextView) findViewById(R.id.textView1);
		servusBerryServer.setText(pref.getUrl());

		api = new API(getApplicationContext());
	}

	@Override
	public void refresh() {
		String ip = api.ping();
		if (!ip.equals("")) {
			pref.setUrl(ip);
			servusBerryServer.setText(ip);
		}
	}

	@Override
	public void killall() {
		api.killall();
	}
}

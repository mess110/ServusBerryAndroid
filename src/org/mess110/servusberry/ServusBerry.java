package org.mess110.servusberry;

import org.mess110.servusberry.base.BaseActivity;
import org.mess110.servusberry.util.HTTPClient;
import org.mess110.servusberry.util.Preferences;

import android.os.Bundle;
import android.widget.TextView;

public class ServusBerry extends BaseActivity {
	private HTTPClient http;
	private Preferences pref;
	private TextView servusBerryServer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		pref = new Preferences(this);
		servusBerryServer = (TextView) findViewById(R.id.textView1);
		servusBerryServer.setText(pref.getUrl());

		http = new HTTPClient(getApplicationContext());
	}

	@Override
	public void refresh() {
		String ip = http.ping();
		if (!ip.equals("")) {
			pref.setUrl(ip);
			servusBerryServer.setText(ip);
		}
	}
}

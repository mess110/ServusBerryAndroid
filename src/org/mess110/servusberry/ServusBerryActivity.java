package org.mess110.servusberry;

import org.mess110.servusberry.base.BaseActivity;
import org.mess110.servusberry.model.ServusBerry;
import org.mess110.servusberry.model.WifiIP;
import org.mess110.servusberry.util.Preferences;
import org.mess110.servusberry.util.Util;

import android.os.Bundle;

public class ServusBerryActivity extends BaseActivity {
	private Preferences pref;
	private ServusBerry servusBerry;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		pref = new Preferences(this);

		servusBerry = new ServusBerry(getApplicationContext());
	}

	@Override
	public void detectServer() {
		WifiIP wifiIp = new WifiIP(getApplicationContext());
		if (!wifiIp.isAvailable()) {
			Util.toast(getApplicationContext(), "Not connected to wifi");
			return;
		}

		String ipMask = wifiIp.getMask();
		String url = servusBerry.findServerIpAddr(ipMask);
		pref.setUrl(url);
	}

	@Override
	public void ping() {
		servusBerry.ping(pref.getUrl());
	}

	@Override
	public void killall() {
		servusBerry.killall();
	}

	@Override
	public void update() {
		servusBerry.update();
	}
}

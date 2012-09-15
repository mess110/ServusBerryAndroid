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

		connect(this);
	}

	private void connect(ServusBerryActivity servusBerryActivity) {
		if (servusBerry.ping(pref.getUrl())) {
			servusBerry.setConnected(true);
		} else {
			detectServer();
		}
		Util.toast(getApplicationContext(),
				String.valueOf(servusBerry.isConnected()));
	}

	public void detectServer() {
		WifiIP wifiIp = new WifiIP(getApplicationContext());
		if (!wifiIp.isAvailable()) {
			servusBerry.setConnected(false);
			return;
		}

		String ipMask = wifiIp.getMask();
		String url = servusBerry.findServerIpAddr(ipMask);
		pref.setUrl(url);
		servusBerry.setConnected(true);
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

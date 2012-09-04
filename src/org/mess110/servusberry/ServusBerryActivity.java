package org.mess110.servusberry;

import org.mess110.servusberry.base.BaseActivity;
import org.mess110.servusberry.model.ServerDetect;
import org.mess110.servusberry.util.API;
import org.mess110.servusberry.util.Preferences;
import org.mess110.servusberry.util.Util;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

// TODO: fix this liek FileSystemActivity
// evaluate if there should be an API object here, since API object
// only does a HTTP request
public class ServusBerryActivity extends BaseActivity {
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

	private String getIpAddr() {
		WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ip = wifiInfo.getIpAddress();

		String ipString = String.format("%d.%d.%d.%d", (ip & 0xff),
				(ip >> 8 & 0xff), (ip >> 16 & 0xff), (ip >> 24 & 0xff));

		return ipString.toString();
	}

	@Override
	public void detectServer() {
		String ipAddr = getIpAddr();
		for (int i = 1; i <= 255; i++) {
			String url = "http://192.168.1." + String.valueOf(i) + ":5000/";
			String jsonString = Util.executeHttpGet(url, true);
			if (ServerDetect.isServer(jsonString)) {
				pref.setUrl(url);
				break;
			}
		}
		servusBerryServer.setText(pref.getUrl());
		Util.toast(getApplicationContext(), getIpAddr());
	}

	@Override
	public void ping() {
		String result = api.ping(pref.getUrl());
		if (ServerDetect.isServer(result)) {
			Util.toast(getApplicationContext(), "connected");
		} else {
			Util.toast(getApplicationContext(), "not connected");
		}
	}

	@Override
	public void killall() {
		api.killall();
	}
}

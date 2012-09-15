package org.mess110.servusberry;

import org.mess110.servusberry.base.BaseActivity;
import org.mess110.servusberry.model.ServusBerry;
import org.mess110.servusberry.model.WifiIP;
import org.mess110.servusberry.util.CustomHandler;
import org.mess110.servusberry.util.Preferences;

import android.app.ProgressDialog;
import android.os.Bundle;

public class ServusBerryActivity extends BaseActivity {
	private Preferences pref;
	private ServusBerry servusBerry;
	private ProgressDialog pd;
	private CustomHandler handler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		pref = new Preferences(this);

		servusBerry = new ServusBerry(getApplicationContext());

		pd = new ProgressDialog(this);
		pd.setMessage("Detecting server..");

		handler = new CustomHandler(pd);

		connect();
	}

	@Override
	public void connect() {
		pd.show();

		if (servusBerry.ping(pref.getUrl())) {
			servusBerry.setConnected(true);
			handler.sendEmptyMessage(0);
		} else {
			new Thread(new Runnable() {
				public void run() {
					detectServer();
					handler.sendEmptyMessage(0);
				}
			}).start();
		}
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

	@Override
	public boolean isConnected() {
		return servusBerry.isConnected();
	}
}

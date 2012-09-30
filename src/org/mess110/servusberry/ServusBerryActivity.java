package org.mess110.servusberry;

import org.mess110.servusberry.base.BaseActivity;
import org.mess110.servusberry.model.ServusBerry;
import org.mess110.servusberry.model.WifiIP;
import org.mess110.servusberry.util.CustomHandler;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServusBerryActivity extends BaseActivity {
	private ServusBerry servusBerry;
	private ProgressDialog pd;
	private CustomHandler handler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		servusBerry = ServusBerry.getInstance();
		servusBerry.init(getApplicationContext());

		pd = new ProgressDialog(this);
		pd.setMessage("Detecting server..");

		Button retry = (Button) findViewById(R.id.button1);
		retry.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				connect();
			}
		});

		handler = new CustomHandler(this, pd);

		connect();
	}

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
		String url = servusBerry.findServerIpAddr(ipMask, pref.getScanPort());

		if (url.equals("")) {
			servusBerry.setConnected(false);
			return;
		}

		pref.setUrl(url);
		servusBerry.setConnected(true);
	}

	public boolean isConnected() {
		return servusBerry.isConnected();
	}

	public ServusBerry getServusBerry() {
		return servusBerry;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_servusberry, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			startActivity(SettingsActivity.class);
			break;
		case R.id.menu_retry:
			connect();
			break;
		case R.id.menu_exit:
			this.finish();
			break;
		}
		return true;
	}
}

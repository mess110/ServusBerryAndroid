package org.mess110.servusberry;

import org.mess110.servusberry.base.BaseActivity;
import org.mess110.servusberry.model.ServusBerry;
import org.mess110.servusberry.model.WifiIP;
import org.mess110.servusberry.util.CustomHandler;

import android.app.ProgressDialog;
import android.os.Bundle;
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

		servusBerry = new ServusBerry(getApplicationContext());

		pd = new ProgressDialog(this);
		pd.setMessage("Detecting server..");

		Button killall = (Button) findViewById(R.id.button1);
		killall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				servusBerry.mute();
			}
		});

		Button volDown = (Button) findViewById(R.id.button4);
		volDown.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				servusBerry.volDown();
			}
		});

		Button volUp = (Button) findViewById(R.id.button5);
		volUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				servusBerry.volUp();
			}
		});

		Button fileSystem = (Button) findViewById(R.id.button2);
		fileSystem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(FileSystemActivity.class);
			}
		});

		Button radio = (Button) findViewById(R.id.button3);
		radio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(RadioActivity.class);
			}
		});

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
	public void update() {
		servusBerry.update();
	}

	@Override
	public boolean isConnected() {
		return servusBerry.isConnected();
	}
}

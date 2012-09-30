package org.mess110.servusberry.util;

import org.mess110.servusberry.R;
import org.mess110.servusberry.RCActivity;
import org.mess110.servusberry.ServusBerryActivity;
import org.mess110.servusberry.model.ServusBerry;
import org.mess110.servusberry.model.WifiIP;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomHandler extends Handler {
	private ProgressDialog progressDialog;
	private ServusBerryActivity servusBerryActivity;
	private ServusBerry servusBerry;
	private TextView status;
	private ImageView imageView;

	public CustomHandler(ServusBerryActivity servusBerryActivity,
			ProgressDialog progressDialog) {
		this.progressDialog = progressDialog;
		this.servusBerryActivity = servusBerryActivity;
		this.servusBerry = this.servusBerryActivity.getServusBerry();

		status = (TextView) servusBerryActivity.findViewById(R.id.statusText);
		imageView = (ImageView) servusBerryActivity
				.findViewById(R.id.imageView1);
	}

	@Override
	public void handleMessage(Message msg) {
		progressDialog.dismiss();

		WifiIP wifiIp = new WifiIP(servusBerry.getContext());
		if (!wifiIp.isAvailable()) {
			status.setText("not connected to wifi");
			return;
		}

		if (!servusBerry.isConnected()) {
			status.setText("could not find server");
			return;
		}

		status.setText("");
		imageView.setImageResource(R.drawable.servusberry);
		imageView.refreshDrawableState();
		servusBerryActivity.startActivity(RCActivity.class);
	}
}

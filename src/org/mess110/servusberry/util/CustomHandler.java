package org.mess110.servusberry.util;

import org.mess110.servusberry.model.ServusBerry;
import org.mess110.servusberry.model.WifiIP;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;

public class CustomHandler extends Handler {
	private ProgressDialog progressDialog;
	private ServusBerry servusBerry;

	public CustomHandler(ServusBerry servusBerry, ProgressDialog progressDialog) {
		this.progressDialog = progressDialog;
		this.servusBerry = servusBerry;
	}

	@Override
	public void handleMessage(Message msg) {
		progressDialog.dismiss();
		
		WifiIP wifiIp = new WifiIP(servusBerry.getContext());
		if (!wifiIp.isAvailable()) {
			Util.toast(servusBerry.getContext(), "not connected to wifi");
		}
		
		if (!servusBerry.isConnected()) {
			Util.toast(servusBerry.getContext(), "could not find server");
		}
	}
}

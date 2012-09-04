package org.mess110.servusberry.model;

import org.mess110.servusberry.util.Util;

import android.content.Context;

public class WifiIP {

	private String ipAddr;

	public WifiIP(Context context) {
		this.ipAddr = Util.getWifiIpAddr(context);
	}

	public boolean isAvailable() {
		return !ipAddr.equals("0.0.0.0");
	}

	public String getMask() {
		return ipAddr.substring(0, ipAddr.lastIndexOf('.') + 1);
	}

}

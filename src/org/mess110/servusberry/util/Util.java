package org.mess110.servusberry.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

public class Util {

	public static void log(String text) {
		Log.d(ServusConst.LOG_TAG, text);
	}

	public static void log(int i) {
		log(String.valueOf(i));
	}

	public static String pathJoin(String s1, String s2) {
		if (s1.endsWith("/")) {
			s1 = s1.substring(0, s1.length() - 1);
		}
		if (!s2.startsWith("/")) {
			s1 += "/";
		}
		return s1 + s2;
	}

	public static String prevDir(String path) {
		String s = path;
		if (s.indexOf("/") != -1) {
			s = s.substring(0, s.lastIndexOf("/"));
		}
		if (s.equals("")) {
			return "/";
		} else {
			return s;
		}
	}

	public static void toast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}

	public static String executeHttpPost(String url) {
		BufferedReader in = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost();
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			return sb.toString();
		} catch (Exception e) {
			return "{ 'code': 5, 'message': '" + e.getMessage() + "'}";
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String executeHttpGet(String url) {
		return executeHttpGet(url, false);
	}

	public static String executeHttpGet(String url, boolean useTimeout) {
		BufferedReader in = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();

			if (useTimeout == true) {
				HttpParams httpParameters = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParameters, 10);
				client.setParams(httpParameters);
			}

			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			return sb.toString();
		} catch (Exception e) {
			return "{ 'code': 5, 'message': '" + e.getMessage() + "'}";
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getWifiIpAddr(Context ctx) {
		WifiManager wifiManager = (WifiManager) ctx
				.getSystemService(Activity.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ip = wifiInfo.getIpAddress();

		String ipString = String.format("%d.%d.%d.%d", (ip & 0xff),
				(ip >> 8 & 0xff), (ip >> 16 & 0xff), (ip >> 24 & 0xff));

		return ipString.toString();
	}
}

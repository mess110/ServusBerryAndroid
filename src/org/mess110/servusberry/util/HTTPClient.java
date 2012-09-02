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

import android.content.Context;

// This is bad design. Should make a genereic HTTP client, and a wrapper for the API
public class HTTPClient {

	private Preferences pref;

	public HTTPClient(Context context) {
		pref = new Preferences(context);
	}

	public String files(String path) {
		String result = "";
		String url = pref.getUrl() + "/files" + path;
		try {
			result = executeHttpGet(url);
		} catch (Exception e) {

		}
		return result;
	}

	public String killall() {
		String result = "";
		String url = pref.getUrl() + "/killall";
		try {
			result = executeHttpPost(url);
		} catch (Exception e) {

		}
		return result;
	}

	public String execute(String path) {
		String result = "";
		String url = pref.getUrl() + "/files" + path;
		try {
			result = executeHttpPost(url);
		} catch (Exception e) {

		}
		return result;
	}

	// TODO dry this up
	private static String executeHttpPost(String url) throws Exception {
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

	private static String executeHttpGet(String url) throws Exception {
		BufferedReader in = null;
		try {
			HttpClient client = new DefaultHttpClient();
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
}

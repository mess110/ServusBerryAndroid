package org.mess110.servusberry.util;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;

public class CustomHandler extends Handler {
	private ProgressDialog progressDialog;

	public CustomHandler(ProgressDialog progressDialog) {
		this.progressDialog = progressDialog;
	}

	@Override
	public void handleMessage(Message msg) {
		progressDialog.dismiss();
	}
}

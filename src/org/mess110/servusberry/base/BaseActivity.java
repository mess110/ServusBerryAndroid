package org.mess110.servusberry.base;

import org.mess110.servusberry.util.Preferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

	protected Preferences pref;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pref = new Preferences(this);
	}

	public void startActivity(Class<?> klass) {
		Intent myIntent = new Intent(getBaseContext(), klass);
		startActivity(myIntent);
	}
}
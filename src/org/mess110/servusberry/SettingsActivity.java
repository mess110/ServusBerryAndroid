package org.mess110.servusberry;

import org.mess110.servusberry.util.Preferences;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends Activity {
	private Button save;
	private EditText url;
	private Preferences pref;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		pref = new Preferences(this);
		save = (Button) findViewById(R.id.save_button);

		url = (EditText) findViewById(R.id.url);
		url.setText(pref.getUrl());

		save.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				pref.setUrl(url.getText().toString());
				Toast.makeText(getApplicationContext(), "saved",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}

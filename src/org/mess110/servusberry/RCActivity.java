package org.mess110.servusberry;

import org.mess110.servusberry.base.BaseActivity;
import org.mess110.servusberry.model.ServusBerry;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class RCActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rc);

		ImageView volDown = (ImageView) findViewById(R.id.imageView1);
		volDown.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ServusBerry.getInstance().volDown();
			}
		});

		ImageView mute = (ImageView) findViewById(R.id.imageView3);
		mute.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ServusBerry.getInstance().mute();
			}
		});

		ImageView volUp = (ImageView) findViewById(R.id.imageView4);
		volUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ServusBerry.getInstance().volUp();
			}
		});

		ImageView file = (ImageView) findViewById(R.id.imageView5);
		file.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(FileSystemActivity.class);
			}
		});

		ImageView radio = (ImageView) findViewById(R.id.imageView6);
		radio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(RadioActivity.class);
			}
		});

		ImageView killall = (ImageView) findViewById(R.id.imageView7);
		killall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ServusBerry.getInstance().killall();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_rc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default:
			break;
		}
		return false;
	}
}

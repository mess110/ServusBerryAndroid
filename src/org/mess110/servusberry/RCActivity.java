package org.mess110.servusberry;

import org.mess110.servusberry.base.BaseActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class RCActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_rc, menu);
        return true;
    }
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_radio:
			startActivity(RadioActivity.class);
			break;
		case R.id.menu_file_system:
			startActivity(FileSystemActivity.class);
			break;
		case R.id.menu_killall:
			startActivity(RadioActivity.class);
			break;
		default:
			break;
		}
		return false;
	}
}

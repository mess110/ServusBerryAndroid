package org.mess110.servusberry;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RCActivity extends Activity {

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
}

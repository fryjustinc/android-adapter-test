package com.example.fryjc.learn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by fryjc on 5/25/2015.
 */
public class launcherActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_launcher);
        new Handler().postDelayed(new Runnable() {
                                     @Override
                                     public void run() {
                                        startActivity(new Intent(launcherActivity.this, DrawerMainActivity.class));
                                         finish();
                                     }
                                 }
            ,2000);
    }
}


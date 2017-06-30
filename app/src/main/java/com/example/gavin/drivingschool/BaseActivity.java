package com.example.gavin.drivingschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.gavin.drivingschool.Config.Util.SessionManagerHelper;

/**
 * Created by gavin.ackerman on 2017-04-20.
 */

public class BaseActivity extends AppCompatActivity {
    SessionManagerHelper sess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        sess = new SessionManagerHelper(getApplicationContext());

        if(sess.isLoggedIn())
        {
            Intent intent = new Intent(this,AppointmentListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);
        }else
        {
            Intent intent = new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);
        }

    }
}

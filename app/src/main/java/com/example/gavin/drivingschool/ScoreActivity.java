package com.example.gavin.drivingschool;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gavin.drivingschool.Config.Util.SessionManagerHelper;
import com.example.gavin.drivingschool.Domain.Address;
import com.example.gavin.drivingschool.Domain.Score;
import com.example.gavin.drivingschool.Services.AddressService;
import com.example.gavin.drivingschool.Services.Impl.AddressServiceImpl;
import com.example.gavin.drivingschool.Services.Impl.ScoreServiceImpl;
import com.facebook.FacebookSdk;

/**
 * Created by gavin on 2017/06/27.
 */
public class ScoreActivity extends AppCompatActivity {
    private ScoreServiceImpl activateService;
    private boolean isBound = false;
    private AddressService activateAccountService;
    EditText allRight,allLeft,parRight,parLeft,threePoint,driving,lessonNum;
    Intent loginIntent;
    SessionManagerHelper sess;

    private boolean isValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.address_activity);
        activateService = ScoreServiceImpl.getInstance();
        allRight = (EditText)findViewById(R.id.allRight);
        allLeft = (EditText)findViewById(R.id.allLeft);
        parRight = (EditText)findViewById(R.id.parRight);
        parLeft = (EditText)findViewById(R.id.parLeft);
        threePoint = (EditText)findViewById(R.id.threePoint);
        driving = (EditText)findViewById(R.id.driving);
        lessonNum = (EditText)findViewById(R.id.lessonNum);
        sess = new SessionManagerHelper(getApplicationContext());
    }

    public void saveClick(View v) {
        String aLeft = allRight.getText().toString();
        String aRight = allLeft.getText().toString();
        String pRight = parRight.getText().toString();
        String pLeft = parLeft.getText().toString();
        String threeP = threePoint.getText().toString();
        String driv = driving.getText().toString();
        String less = lessonNum.getText().toString();
        if(less == "" ) {
            Toast.makeText(getApplicationContext(),
                    "Please Fill in First 2 fields", Toast.LENGTH_LONG)
                    .show();

        }
        else
        {


            ScoreServiceImpl userService = new ScoreServiceImpl();



            Score user = new Score.Builder()

                    .AlleyDockingLeft(Integer.parseInt(aLeft))
                    .AlleyDockingRight(Integer.parseInt(aRight))
                    .ParallelParkingLeft(Integer.parseInt(pRight))
                    .ParallelParkingRight(Integer.parseInt(pLeft))
                    .ThreePointTurn(Integer.parseInt(threeP))
                    .Driving(Integer.parseInt(driv))
                    .LessonNumber(Integer.parseInt(less))
                     .build();
            Score userId = userService.addScore(user);
            Intent i = new Intent(this,ScoreListActivity.class);

            startActivity(i);
        }


    }
}

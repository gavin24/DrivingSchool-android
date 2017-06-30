package com.example.gavin.drivingschool;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gavin.drivingschool.Domain.Score;
import com.example.gavin.drivingschool.Services.Impl.ImageServiceImpl;
import com.example.gavin.drivingschool.Services.Impl.ScoreServiceImpl;
import com.example.gavin.drivingschool.Services.ScoreService;

/**
 * Created by gavin on 2017/06/27.
 */
public class ViewScoreActivity extends AppCompatActivity {


    private ImageServiceImpl imageService;

    private boolean isBound = false;
    private ScoreService activateAccountService;
    TextView allRight,allLeft,parRight,parLeft,threePoint,driving,lessonNum;

    private long id;
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    int likes,dislikes,shares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreview_activity);

        ////  Bundle extras = getIntent().getExtras();
        id = getIntent().getLongExtra("id", 0);
        ScoreServiceImpl scoreService = new ScoreServiceImpl();
        Score score = scoreService.getScore(id);
        allRight = (TextView)findViewById(R.id.line1);
        allLeft = (TextView)findViewById(R.id.line2);
        parRight = (TextView)findViewById(R.id.line3);
        parLeft = (TextView)findViewById(R.id.line4);
        threePoint = (TextView)findViewById(R.id.line5);
        driving = (TextView)findViewById(R.id.line4);
        lessonNum = (TextView)findViewById(R.id.line5);

        allRight.setText(score.getAlleyDockingRight());
        allLeft.setText(score.getAlleyDockingLeft());
        parRight.setText(score.getParallelParkingRight());
        parLeft.setText(score.getParallelParkingLeft());
        threePoint.setText(score.getThreePointTurn());
        driving.setText(score.getDriving());
        lessonNum.setText(score.getLessonNumber());
    }
}

package com.example.gavin.drivingschool;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.gavin.drivingschool.Config.Util.SessionManagerHelper;
import com.example.gavin.drivingschool.Services.AddressService;
import com.example.gavin.drivingschool.Services.Impl.AppointmentServiceImpl;

import java.sql.Date;

/**
 * Created by gavin on 2017/06/27.
 */
public class CalendarViewActivity extends AppCompatActivity {
    private AppointmentServiceImpl activateService;
    private boolean isBound = false;
    private AddressService activateAccountService;
    EditText Notes;
    TimePicker startTime,endTime;
    DatePicker datePicker;
    Intent loginIntent;
    SessionManagerHelper sess;
    long dateMili;
CalendarView cal;
    Date b;
    private boolean isValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        // FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.appointment_activity);
        activateService = AppointmentServiceImpl.getInstance();
        cal = (CalendarView)findViewById(R.id.calendarView);
        dateMili = b.getTime();
       cal.setDate(dateMili);

        sess = new SessionManagerHelper(getApplicationContext());


        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month, int date) {
                Intent i = new Intent(CalendarViewActivity.this, AppointmentListActivity.class);
                i.putExtra("id", Long.valueOf(date));
                startActivity(i);
            }
        });
    }



}

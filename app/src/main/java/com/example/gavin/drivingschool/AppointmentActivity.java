package com.example.gavin.drivingschool;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gavin.drivingschool.Config.Util.SessionManagerHelper;
import com.example.gavin.drivingschool.Domain.Address;
import com.example.gavin.drivingschool.Domain.Appointment;
import com.example.gavin.drivingschool.Services.AddressService;
import com.example.gavin.drivingschool.Services.Impl.AddressServiceImpl;
import com.example.gavin.drivingschool.Services.Impl.AppointmentServiceImpl;
import com.facebook.FacebookSdk;

import java.util.Calendar;

/**
 * Created by gavin on 2017/06/27.
 */
public class AppointmentActivity extends AppCompatActivity {
    private AppointmentServiceImpl activateService;
    private boolean isBound = false;
    private AddressService activateAccountService;
    EditText Notes;
    EditText startTime,endTime;
    DatePicker datePicker;
    Intent loginIntent;
    SessionManagerHelper sess;
String d;
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
        Notes = (EditText)findViewById(R.id.textView5);
        startTime = (EditText)findViewById(R.id.starttimePicker);
        endTime = (EditText)findViewById(R.id.endtimePicker);
        datePicker = (DatePicker)findViewById(R.id.datePicker);

        sess = new SessionManagerHelper(getApplicationContext());
        setCurrentDateOnView();

    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {
        d = String.valueOf(year) +"-" + String.valueOf(monthOfYear) + "-" + String.valueOf(dayOfMonth);
    }; // BirthDateDP.init()
    public void setCurrentDateOnView() {



        datePicker = (DatePicker) findViewById(R.id.datePicker);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        datePicker.init(year, month, day, null);

    }

    public void saveClick(View v) {
        String notes = Notes.getText().toString();
        String start = startTime.toString();
        String end = endTime.toString();
        String dat = d;

        if(start == "" || end == "" || dat == "" ) {
            Toast.makeText(getApplicationContext(),
                    "Please Fill in First three fields", Toast.LENGTH_LONG)
                    .show();

        }
        else
        {


            AppointmentServiceImpl userService = new AppointmentServiceImpl();



            Appointment user = new Appointment.Builder()

                    .Notes(notes)
                    .StartTime(start)
                    .EndTime(end)
                    .AppointmentDate(dat)
                    .build();
            Appointment userId = userService.addAppointment(user);
            Intent i = new Intent(this,AppointmentListActivity.class);

            startActivity(i);
        }


    }
}

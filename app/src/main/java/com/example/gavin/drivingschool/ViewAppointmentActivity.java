package com.example.gavin.drivingschool;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gavin.drivingschool.Domain.Appointment;
import com.example.gavin.drivingschool.Domain.Client;
import com.example.gavin.drivingschool.Services.Impl.AppointmentServiceImpl;
import com.example.gavin.drivingschool.Services.Impl.ClientServiceImpl;
import com.example.gavin.drivingschool.Services.Impl.ImageServiceImpl;
import com.example.gavin.drivingschool.Services.UserService;

/**
 * Created by gavin on 2017/06/27.
 */
public class ViewAppointmentActivity extends AppCompatActivity {

    private ImageServiceImpl imageService;

    private boolean isBound = false;
    private UserService activateAccountService;
    TextView DateText,startText,endText,NotesText,NameText;

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
        setContentView(R.layout.addressview_activity);

        ////  Bundle extras = getIntent().getExtras();
        id = getIntent().getLongExtra("id", 0);
        ClientServiceImpl clientService = new ClientServiceImpl();
        Client client = clientService.getClient((long)34);
        AppointmentServiceImpl addressService = new AppointmentServiceImpl();
        Appointment appointment = addressService.getAppointment(id);
        NameText = (TextView)findViewById(R.id.textName);
        DateText = (TextView)findViewById(R.id.textDate);
        startText = (TextView)findViewById(R.id.textStart);
        endText = (TextView)findViewById(R.id.textEnd);
        NotesText = (TextView)findViewById(R.id.textNotes);

        NameText.setText(client.getName() + "" + client.getSurname());
        DateText.setText(appointment.getAppointmentDate());
        startText.setText(appointment.getStartTime());
        endText.setText(appointment.getEndTime());
        NotesText.setText(appointment.getNotes());
    }
}

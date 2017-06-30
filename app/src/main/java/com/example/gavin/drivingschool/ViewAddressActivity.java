package com.example.gavin.drivingschool;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gavin.drivingschool.Domain.Address;
import com.example.gavin.drivingschool.Services.Impl.AddressServiceImpl;
import com.example.gavin.drivingschool.Services.Impl.ImageServiceImpl;
import com.example.gavin.drivingschool.Services.UserService;

/**
 * Created by gavin on 2017/06/27.
 */
public class ViewAddressActivity extends AppCompatActivity {

    private ImageServiceImpl imageService;

    private boolean isBound = false;
    private UserService activateAccountService;
    TextView Line1TextView,Line2TextView,Line3TextView,Line4TextView,Line5TextView;

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
        AddressServiceImpl addressService = new AddressServiceImpl();
        Address address = addressService.getAddress(id);
        Line1TextView = (TextView)findViewById(R.id.line1);
        Line2TextView = (TextView)findViewById(R.id.line2);
        Line3TextView = (TextView)findViewById(R.id.line3);
        Line4TextView = (TextView)findViewById(R.id.line4);
        Line5TextView = (TextView)findViewById(R.id.line5);

        Line1TextView.setText(address.getLine1());
        Line2TextView.setText(address.getLine2());
        Line3TextView.setText(address.getLine3());
        Line4TextView.setText(address.getLine4());
        Line5TextView.setText(address.getLine5());
    }
}

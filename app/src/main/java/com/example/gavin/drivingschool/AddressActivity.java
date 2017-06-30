package com.example.gavin.drivingschool;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gavin.drivingschool.Config.Util.SessionManagerHelper;
import com.example.gavin.drivingschool.Domain.Address;
import com.example.gavin.drivingschool.Domain.User;
import com.example.gavin.drivingschool.Domain.UserImage;
import com.example.gavin.drivingschool.Services.AddressService;
import com.example.gavin.drivingschool.Services.Impl.AddressServiceImpl;
import com.example.gavin.drivingschool.Services.Impl.UserImageServiceImpl;
import com.example.gavin.drivingschool.Services.Impl.UserServiceImpl;
import com.example.gavin.drivingschool.Services.UserService;
import com.facebook.FacebookSdk;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

/**
 * Created by gavin on 2017/06/27.
 */
public class AddressActivity extends AppCompatActivity {
    private AddressServiceImpl activateService;
    private boolean isBound = false;
    private AddressService activateAccountService;
    EditText Line1,Line2,Line3,Line4,Line5;
    Intent loginIntent;
    SessionManagerHelper sess;

    private boolean isValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.address_activity);
        activateService = AddressServiceImpl.getInstance();
        Line1 = (EditText)findViewById(R.id.line1);
        Line2 = (EditText)findViewById(R.id.line2);
        Line3 = (EditText)findViewById(R.id.line4);
        Line4 = (EditText)findViewById(R.id.line4);
        Line5 = (EditText)findViewById(R.id.line5);
        sess = new SessionManagerHelper(getApplicationContext());
    }

    public void saveClick(View v) {
        String L1 = Line1.getText().toString();
        String L2 = Line2.getText().toString();
        String L3 = Line3.getText().toString();
        String L4 = Line4.getText().toString();
        String L5 = Line5.getText().toString();
        if(L1 == "" || L2 == "" || L3 == "" ) {
            Toast.makeText(getApplicationContext(),
                    "Please Fill in First three fields", Toast.LENGTH_LONG)
                    .show();

        }
        else
        {


            AddressServiceImpl userService = new AddressServiceImpl();



                Address user = new Address.Builder()

                        .Line1(L1)
                        .Line2(L2)
                        .Line3(L3)
                        .Line4(L4)
                        .Line5(L5)
                        .build();
            Address userId = userService.addAddress(user);
                Intent i = new Intent(this,ClientListActivity.class);

                startActivity(i);
            }


        }



}

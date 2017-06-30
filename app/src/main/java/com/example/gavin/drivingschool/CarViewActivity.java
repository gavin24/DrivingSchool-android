package com.example.gavin.drivingschool;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gavin.drivingschool.Config.Util.SessionManagerHelper;
import com.example.gavin.drivingschool.Domain.Car;
import com.example.gavin.drivingschool.Domain.Image;
import com.example.gavin.drivingschool.Services.Impl.CarServiceImpl;
import com.example.gavin.drivingschool.Services.Impl.ImageServiceImpl;
import com.example.gavin.drivingschool.Services.Impl.UserServiceImpl;
import com.example.gavin.drivingschool.Services.UserService;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public class CarViewActivity extends AppCompatActivity {
    private CarServiceImpl carService;
    private ImageServiceImpl imageService;
    private boolean isBound = false;
    private UserService activateAccountService;
    TextView TypeText,LicenseText,YearText,MilaegeText;
    private ImageView ivImage;
    private long id;
    long fameid;
    private long fameId;
    long userProfileId;
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    int likes,dislikes,shares;
    SessionManagerHelper sess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carview_activity);
        mNavItems.add(new NavItem("Home", "newsfeed", R.drawable.ic_newsfeed_24dp));
        mNavItems.add(new NavItem("Story", "Create new story", R.drawable.ic_brush_dp));
        mNavItems.add(new NavItem("Profile", "Check your profile", R.drawable.ic_arrow_24dp));
        mNavItems.add(new NavItem("Logout", "Sign out of the app", R.drawable.ic_arrow_24dp));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        sess = new SessionManagerHelper(getApplicationContext());
        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);


                invalidateOptionsMenu();
            }
        };

        //  mDrawerLayout.setDrawerListener(mDrawerToggle);
        Bundle extras = getIntent().getExtras();
        id = getIntent().getLongExtra("id", 0);
        CarServiceImpl carService = new CarServiceImpl();
        ImageServiceImpl imageService = new ImageServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();
        TypeText = (TextView)findViewById(R.id.typeText);
        LicenseText = (TextView)findViewById(R.id.licenseText);
        YearText = (TextView)findViewById(R.id.yearText);
        MilaegeText = (TextView)findViewById(R.id.milaegeText);


        Car car2 = carService.getCar(id);
        Image image = imageService.getImage(car2.getUserId());
        ivImage = (ImageView) findViewById(R.id.ivImage);
        Bitmap bmp = BitmapFactory.decodeByteArray(image.getImage(), 0, image.getImage().length);
        ivImage.setImageBitmap(bmp);
        TypeText.setText(car2.getType());
        LicenseText.setText(car2.getLicense());
        YearText.setText(car2.getYear());
        MilaegeText.setText(car2.getMileage());


    }
    private void selectItemFromDrawer(int position) {
        android.app.Fragment fragment = new android.app.Fragment();

        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mNavItems.get(position).mTitle);
        switch (position){
            case 0:
                startActivity(new Intent(CarViewActivity.this, AppointmentListActivity.class));
                mDrawerLayout.closeDrawers();
                break;
            case 1:
                startActivity(new Intent(CarViewActivity.this, UserListActivity.class));
                break;
            case 2:
                startActivity(new Intent(CarViewActivity.this, ClientListActivity.class));
                //  mDrawerLayout.closeDrawers();
                break;
            case 3:
                startActivity(new Intent(CarViewActivity.this, CarListActivity.class));
                break;
            case 4:
                startActivity(new Intent(CarViewActivity.this, ScoreListActivity.class));
                //  mDrawerLayout.closeDrawers();
                break;
            case 5:
                sess.logoutUser();

                finish();

        }
        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
}

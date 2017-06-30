package com.example.gavin.drivingschool;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.gavin.drivingschool.Config.Util.SessionManagerHelper;
import com.example.gavin.drivingschool.Domain.Client;
import com.example.gavin.drivingschool.Services.Impl.ClientServiceImpl;
import com.example.gavin.drivingschool.Services.Impl.ImageServiceImpl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavin on 2017/06/27.
 */
public class ClientListActivity extends AppCompatActivity {
    private ClientServiceImpl activateService;
    private boolean isBound = false;

    private ImageServiceImpl imageService;
    ListView listView;

    List<Client> newsItems;
    FloatingActionButton fab;
    ArrayList<String> values = new ArrayList<String>();
    ArrayList<String> images = new ArrayList<String>();
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    SessionManagerHelper sess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientlist_activity);
        mNavItems.add(new NavItem("Home", "Appointments", R.drawable.ic_newsfeed_24dp));
        mNavItems.add(new NavItem("Drivers", "Check List of Drivers", R.drawable.ic_brush_dp));
        mNavItems.add(new NavItem("Students", "Check list of Students", R.drawable.ic_arrow_24dp));
        mNavItems.add(new NavItem("Cars", "Check list of Cars", R.drawable.ic_arrow_24dp));
        mNavItems.add(new NavItem("Scores", "Add new Scores", R.drawable.ic_arrow_24dp));
        mNavItems.add(new NavItem("Logout", "Sign out of the app", R.drawable.ic_arrow_24dp));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        sess = new SessionManagerHelper(getApplicationContext());
        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> "+mDrawerList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> "+adapter);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });
        fab = (FloatingActionButton) findViewById(R.id.createNewClient);

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

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        // CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
        //  list=(ListView)findViewById(R.id.list);
        //  list.setAdapter(adapter);
     /*   Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StoryServiceImpl service = new StoryServiceImpl();
                    newsItems = service.getAllStorys();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
*/
    /*    thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

*/


        ClientServiceImpl service = new ClientServiceImpl();
        newsItems = service.getAllClient();

        int number =43646346;
        Client user = new Client.Builder()

                .Name("Chad")
                .Surname("Johnson")
                .Email("dsgs@gmail.com")
                .PhoneNumber(number)
                .build();
        service.addClient(user);
        if(newsItems.size() == 0)
        {
            newsItems.add(user);
        }

        if (newsItems == null) {
            values.add("Chad Johnson");
        }




        AppointmentListAdapter adapter2 = new AppointmentListAdapter(this, values);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = values.get(+position);
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                Client clickStory = newsItems.get(+position);
                System.out.println("story id "+ clickStory.getId());
                Intent i = new Intent(ClientListActivity.this, ViewClientActivity.class);
                i.putExtra("id", Long.valueOf(clickStory.getId()));
                startActivity(i);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(ClientListActivity.this, ClientActivity.class));

            }

        });
    }


    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        return stream.toByteArray();
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
                startActivity(new Intent(ClientListActivity.this, AppointmentListActivity.class));
                mDrawerLayout.closeDrawers();
                break;
            case 1:
                startActivity(new Intent(ClientListActivity.this, UserListActivity.class));
                break;
            case 2:
                startActivity(new Intent(ClientListActivity.this, ClientListActivity.class));
                //  mDrawerLayout.closeDrawers();
                break;
            case 3:
                startActivity(new Intent(ClientListActivity.this, CarListActivity.class));
                break;
            case 4:
                startActivity(new Intent(ClientListActivity.this, ScoreListActivity.class));
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

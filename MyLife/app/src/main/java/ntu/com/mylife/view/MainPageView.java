package ntu.com.mylife.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.applicationentity.SharedPreferencesKey;
import ntu.com.mylife.common.entity.databaseentity.UserType;
import ntu.com.mylife.common.service.SharedPreferencesService;
import ntu.com.mylife.controller.NavigationDrawerRecyclerViewAdapter;
import ntu.com.mylife.controller.NotificationController;
import ntu.com.mylife.controller.TabsPagerAdapter;

public class MainPageView extends AppCompatActivity implements HomeView.OnFragmentInteractionListener,ProfileView.OnFragmentInteractionListener,
        MedicalRecordView.OnFragmentInteractionListener, ChatView.OnFragmentInteractionListener,
        ContactOptionsView.OnFragmentInteractionListener, SubmitMedicalRecordView.OnFragmentInteractionListener,
        CreateReminderView.OnFragmentInteractionListener, CalendarView.OnFragmentInteractionListener {

    private Toolbar toolbar;                              // Declaring the Toolbar Object


    private RecyclerView mRecyclerView;                           // Declaring RecyclerView
    private LinearLayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    private DrawerLayout Drawer;                                  // Declaring DrawerLayout
    private Fragment mainFragment;
    private NotificationController notificationController;
    private SharedPreferencesService sharedPreferencesService;
    private String TYPE ;
    private ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_view);
        //instanstiate general attribute here
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        /*
        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.view_pager_container);
        viewPager.setAdapter(tabsPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        */


        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        Drawer = (DrawerLayout) findViewById(R.id.drawer_navigation_drawer);
        sharedPreferencesService = new SharedPreferencesService(this);
        TYPE = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,"userType");

        instanstiateNavigationDrawer();




//        //Fragment
//        // Create new fragment and transaction
//        Fragment newFragment = new ProfileView();
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        // Replace whatever is in the fragment_container view with this fragment,
//        // and add the transaction to the back stack if needed
//        transaction.replace(R.id.fragment_transition_main_page, newFragment);
//        transaction.addToBackStack(null);
//        // Commit the transaction
//        transaction.commit();


        //set up notification Controller
        String userName = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,SharedPreferencesKey.KEY_USER);
        notificationController = new NotificationController(this,userName);
    }



    private void instanstiateNavigationDrawer(){
        //set the navigation drawer

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
//        getSupportActionBar().setHomeButtonEnabled(true);

        mRecyclerView.setHasFixedSize(true);
        //adapter for the navigation drawer
        //change the left Drawer based on the type of the user
        ArrayList<String> listTextNavigationDrawer = new ArrayList<String>();
        listTextNavigationDrawer.add("Home");
        listTextNavigationDrawer.add("Profile");
        if(TYPE.equals(UserType.Type.PATIENT))listTextNavigationDrawer.add("Medical Record");
        if(TYPE.equals(UserType.Type.PATIENT))listTextNavigationDrawer.add("Contact Doctor");
        else listTextNavigationDrawer.add("Contact Patient");
        listTextNavigationDrawer.add("Calendar");
        ArrayList<Bitmap> listBitmapNavigationDrawer = new ArrayList<Bitmap>();
        listBitmapNavigationDrawer.add(BitmapFactory.decodeResource(getResources(), R.drawable.icon_doctor));
        listBitmapNavigationDrawer.add(BitmapFactory.decodeResource(getResources(), R.drawable.icon_profile));
        if(TYPE.equals(UserType.Type.PATIENT))listBitmapNavigationDrawer.add(BitmapFactory.decodeResource(getResources(), R.drawable.icon_report));
        listBitmapNavigationDrawer.add(BitmapFactory.decodeResource(getResources(), R.drawable.icon_doctor));
        listBitmapNavigationDrawer.add(BitmapFactory.decodeResource(getResources(), R.drawable.icon_calendar));

        NavigationDrawerRecyclerViewAdapter myAdapter = new NavigationDrawerRecyclerViewAdapter(this,listTextNavigationDrawer,listBitmapNavigationDrawer);
        mRecyclerView.setAdapter(myAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //need to sync the drawer
        mDrawerToggle = new ActionBarDrawerToggle(this,
                Drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        mRecyclerView.bringToFront();
        Drawer.requestLayout();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_page_view, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_navigation_drawer);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Drawer.openDrawer(Gravity.LEFT);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

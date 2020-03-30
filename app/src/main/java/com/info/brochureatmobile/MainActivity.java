package com.info.brochureatmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.info.brochureatmobile.Activity.CallbackRequest;
import com.info.brochureatmobile.fragment.Certification;
import com.info.brochureatmobile.fragment.FragmentFirst;
import com.info.brochureatmobile.fragment.NewsEvent;
import com.info.brochureatmobile.fragment.OurClients;
import com.info.brochureatmobile.fragment.ProductFragment;
import com.info.brochureatmobile.fragment.Representative;
import com.info.brochureatmobile.fragment.Service;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FrameLayout frame_main;
    ImageView img_home, img_contact, img_search, img_product, img_service;
    private AppBarConfiguration mAppBarConfiguration;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DrawerLayout drawer;
    NavigationView navigationView;
    LinearLayout ll_home, ll_product, ll_service, ll_contact, ll_search;
    BottomNavigationView bottom_navigation;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    Button btn_callBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
           /* Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
           */
            init();
            // setActionBar();
            initFragment();

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                    R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                    .setDrawerLayout(drawer)
                    .build();

            setUpNavigationView();

            ll_search.setOnClickListener(this);
            ll_contact.setOnClickListener(this);
            ll_service.setOnClickListener(this);
            ll_product.setOnClickListener(this);
            ll_home.setOnClickListener(this);
            btn_callBack.setOnClickListener(this);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setActionBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(getTitle());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        //WebUtils.applyFontForToolbarTitle(ItemListActivity.this, toolbar);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
    }


    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_main, new FragmentFirst(), "first").addToBackStack(null).commit();
        img_home.setImageResource(R.drawable.home_blue);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /* @Override
         public boolean onSupportNavigateUp() {
             NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
             return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                     || super.onSupportNavigateUp();
         }
     */
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        try {
            int i = getSupportFragmentManager().getBackStackEntryCount();
            if (i == 1)
                finishAffinity();
            else
                getSupportFragmentManager().popBackStack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void init() {
        img_home = findViewById(R.id.img_home);
        img_service = findViewById(R.id.img_service);
        img_product = findViewById(R.id.img_product);
        img_contact = findViewById(R.id.img_contact);
        img_search = findViewById(R.id.img_search);
        frame_main = findViewById(R.id.frame_main);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ll_search = findViewById(R.id.ll_search);
        ll_contact = findViewById(R.id.ll_contact);
        ll_service = findViewById(R.id.ll_service);
        ll_product = findViewById(R.id.ll_product);
        ll_home = findViewById(R.id.ll_home);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar);
        btn_callBack = findViewById(R.id.btn_callBack);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_slideshow:
                        //doDrawerAction();
                        invalidateOptionsMenu();

                        drawer.closeDrawer(Gravity.LEFT);
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_main, new Service(), "third").addToBackStack(null).commit();
                        img_home.setImageResource(R.drawable.homegrey);
                        img_contact.setImageResource(R.drawable.contactgrey);
                        img_service.setImageResource(R.drawable.services);
                        img_product.setImageResource(R.drawable.productgrey);
                        img_search.setImageResource(R.drawable.searchgrey);
                        break;

                    case R.id.nav_tools:
                        invalidateOptionsMenu();
                        drawer.closeDrawer(Gravity.LEFT);
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_main, new Representative(), "third").addToBackStack(null).commit();
                        img_home.setImageResource(R.drawable.homegrey);
                        img_contact.setImageResource(R.drawable.contactgrey);
                        img_service.setImageResource(R.drawable.services);
                        img_product.setImageResource(R.drawable.productgrey);
                        img_search.setImageResource(R.drawable.searchgrey);
                        break;

                    case R.id.nav_events:
                        invalidateOptionsMenu();
                        drawer.closeDrawer(Gravity.LEFT);
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_main, new NewsEvent(), "third").addToBackStack(null).commit();
                        img_home.setImageResource(R.drawable.homegrey);
                        img_contact.setImageResource(R.drawable.contactgrey);
                        img_service.setImageResource(R.drawable.services);
                        img_product.setImageResource(R.drawable.productgrey);
                        img_search.setImageResource(R.drawable.searchgrey);
                        break;

                    case R.id.nav_home:
                        invalidateOptionsMenu();
                        drawer.closeDrawer(Gravity.LEFT);
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_main, new FragmentFirst(), "third").addToBackStack(null).commit();
                        img_home.setImageResource(R.drawable.homegrey);
                        img_contact.setImageResource(R.drawable.contactgrey);
                        img_service.setImageResource(R.drawable.services);
                        img_product.setImageResource(R.drawable.productgrey);
                        img_search.setImageResource(R.drawable.searchgrey);
                        break;

                    case R.id.nav_certification:
                        invalidateOptionsMenu();
                        drawer.closeDrawer(Gravity.LEFT);
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_main, new Certification(), "third").addToBackStack(null).commit();
                        img_home.setImageResource(R.drawable.homegrey);
                        img_contact.setImageResource(R.drawable.contactgrey);
                        img_service.setImageResource(R.drawable.services);
                        img_product.setImageResource(R.drawable.productgrey);
                        img_search.setImageResource(R.drawable.searchgrey);
                        break;

                    case R.id.nav_clients:
                        invalidateOptionsMenu();
                        drawer.closeDrawer(Gravity.LEFT);
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_main, new OurClients(), "third").addToBackStack(null).commit();
                        img_home.setImageResource(R.drawable.homegrey);
                        img_contact.setImageResource(R.drawable.contactgrey);
                        img_service.setImageResource(R.drawable.services);
                        img_product.setImageResource(R.drawable.productgrey);
                        img_search.setImageResource(R.drawable.searchgrey);
                        break;

                    case R.id.nav_gallery:
                        invalidateOptionsMenu();
                        drawer.closeDrawer(Gravity.LEFT);
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_main, new ProductFragment(), "third").addToBackStack(null).commit();
                        img_home.setImageResource(R.drawable.homegrey);
                        img_contact.setImageResource(R.drawable.contactgrey);
                        img_service.setImageResource(R.drawable.services);
                        img_product.setImageResource(R.drawable.productgrey);
                        img_search.setImageResource(R.drawable.searchgrey);
                        break;
                    case R.id.nav_call:
                        Intent it = new Intent(MainActivity.this, CallbackRequest.class);
                        startActivity(it);
                        break;

                    default:
                        invalidateOptionsMenu();
                        break;
                }
                return true;
            }
        });


    }

    void doDrawerAction() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_main, new FragmentFirst(), "fifth").commit();
                img_home.setImageResource(R.drawable.home_blue);
                img_contact.setImageResource(R.drawable.contactgrey);
                img_service.setImageResource(R.drawable.servicesgrey);
                img_product.setImageResource(R.drawable.productgrey);
                img_search.setImageResource(R.drawable.searchgrey);
                break;
            case R.id.ll_service:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_main, new Service(), "third").addToBackStack(null).commit();
                img_home.setImageResource(R.drawable.homegrey);
                img_contact.setImageResource(R.drawable.contactgrey);
                img_service.setImageResource(R.drawable.services);
                img_product.setImageResource(R.drawable.productgrey);
                img_search.setImageResource(R.drawable.searchgrey);
                break;
            case R.id.ll_product:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_main, new ProductFragment(), "second").addToBackStack(null).commit();
                img_home.setImageResource(R.drawable.homegrey);
                img_contact.setImageResource(R.drawable.contactgrey);
                img_service.setImageResource(R.drawable.servicesgrey);
                img_product.setImageResource(R.drawable.product_blue);
                img_search.setImageResource(R.drawable.searchgrey);
                break;
            case R.id.ll_contact:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_main, new Representative(), "fourth").addToBackStack(null).commit();
                img_home.setImageResource(R.drawable.homegrey);
                img_contact.setImageResource(R.drawable.contact_blue);
                img_service.setImageResource(R.drawable.servicesgrey);
                img_product.setImageResource(R.drawable.productgrey);
                img_search.setImageResource(R.drawable.searchgrey);
                break;
            case R.id.ll_search:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_main, new NewsEvent(), "fifth").addToBackStack(null).commit();
                img_home.setImageResource(R.drawable.homegrey);
                img_contact.setImageResource(R.drawable.contactgrey);
                img_service.setImageResource(R.drawable.servicesgrey);
                img_product.setImageResource(R.drawable.productgrey);
                img_search.setImageResource(R.drawable.search_blue);
                break;
            case R.id.btn_callBack:
                Intent it = new Intent(MainActivity.this, CallbackRequest.class);
                startActivity(it);
                break;
        }
    }


}


package com.chetverg.dongtu_mobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chetverg.dongtu_mobile.adapter.TabsPagerFragmentAdapter;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //объявление формы
    private static final int LAYOUT = R.layout.activity_main;
    //объявление тулбара
    private Toolbar toolbar;
    //объявление вкладок
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;


    //
    private SQLiteHandler db;
    private SessionManager session;

   /* private TextView Text1;
    private TextView Text2;
    private TextView Text3;*/
    private FloatingActionButton FAB;

    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());



       if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String id = user.get("uid");
        String name = user.get("name");
        String surname = user.get("second_name");

        //вызов тулбара
        initToolbar();
        //вызов меню
        initNavigationView();
        //вызов вкладок
        initTabs();
        //вызов панели вкладок
        initTabLayout();

 /*       Text1 = (TextView) findViewById(R.id.user_id);
        Text2 = (TextView) findViewById(R.id.user_name);
        Text3 = (TextView) findViewById(R.id.user_surname);
       // Text1.setText(id);
       // Text2.setText("jo");
       Text3.setText("pa");*/

        toolbar.setTitle(name);

        FAB = (FloatingActionButton) findViewById(R.id.fab_schedule);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    //инициализация тулбара
    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Выставление заголовка тулбара
        toolbar.setTitle("DonGTU_mobile BETA.Главная");
/*        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });*/

        toolbar.inflateMenu(R.menu.menu);

    }

    //подключение выпадающего меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        //открыть меню по нажатию на кнопку
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //переход на вкладу при нажатии на пункт бокового меню
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.schedule:
                        showSecondTab();
                }
                return true;
            }
        });
    }

    //объявлении и инициализация панели вкладок
    private void initTabLayout(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    //инициализация вкладок
    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

    }

    private void showSecondTab(){
        viewPager.setCurrentItem(Constants.TAB_TWO);
    }

   private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}

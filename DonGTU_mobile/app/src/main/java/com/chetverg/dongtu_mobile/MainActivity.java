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


    private SQLiteHandler db;
    private SessionManager session;

    //имя фамилия пользователя в боковой панели
    private TextView user_name;
    private TextView user_surname;

    private FloatingActionButton FAB;

    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        //Вернуть пользователя на форму входа, если не залогини. Временно отключено
/*       if (!session.isLoggedIn()) {
            logoutUser();
        }*/

        //вызов тулбара
        initToolbar();
        //вызов меню
        initNavigationView();
       // SlideMenu sl = new SlideMenu(getApplicationContext());
       // sl.runMenu();

        //вызов вкладок
        initTabs();
        //вызов панели вкладок
        initTabLayout();

        //экшн-баттон
        FAB = (FloatingActionButton) findViewById(R.id.fab_schedule);
/*        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });*/
    }


    //инициализация тулбара
    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Выставление заголовка тулбара
       toolbar.setTitle("Расписание");

       toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.first_item:
                       // logoutUser();
                       // break;
                }
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.menu);
    }

    //подключение выпадающего меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //инициализация бокового меню
    private void initNavigationView() {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        //открыть меню по нажатию на кнопку
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //переход на вкладу при нажатии на пункт бокового меню
        NavigationView NView = (NavigationView) findViewById(R.id.navigation);


        //выставление имени и фамилии пользователя в шапке боковой панели
        db = new SQLiteHandler(getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();

        View header = NView.getHeaderView(0);
        user_name = (TextView)header.findViewById(R.id.nav_header_username);
        user_surname = (TextView)header.findViewById(R.id.nav_header_usersurname);
        user_name.setText(user.get("name"));
        user_surname.setText(" " + user.get("second_name"));


        assert NView != null;
        NView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.schedule:
                        Intent intent_schedule = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent_schedule);
                        finish();
                        break;
                    //выход по кнопке в боковом меню
                    case R.id.navigation_menu_logout_btn:
                        logoutUser();
                        break;
                    case R.id.navigation_menu_profile_btn:
                        Intent intent_profile = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent_profile);
                       // finish();
                        break;
                    case R.id.navigation_menu_courses_btn:
                        Intent intent_courses = new Intent(getApplicationContext(), CoursesActivity.class);
                        startActivity(intent_courses);
                        break;
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

    //это просто тествовая фун-я - необходимо удалить!!!
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

package com.chetverg.dongtu_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by chetverg on 05.06.16.
 */
public class ProfileActivity extends AppCompatActivity {

/*    private String test;

    public ProfileActivity(String test){
        this.test = test;
    }*/


    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    //объявление формы
    private static final int LAYOUT = R.layout.profile_activity;
    //объявление тулбара
    private Toolbar toolbar;

    //имя фамилия пользователя в заголовке
    private TextView user_title_name;
    private TextView user_title_surname;

    //имя фамилия пользователя
    private TextView user_fio;

    private TextView user_city;
    private TextView user_country;

    private SQLiteHandler db;
    private SessionManager session;

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        // session manager
        session = new SessionManager(getApplicationContext());

        db = new SQLiteHandler(getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();

//        user_name = (TextView)toolbar.findViewById(R.id.profile_name);
//        user_surname = (TextView)toolbar.findViewById(R.id.profile_surname);

        user_fio = (TextView)findViewById(R.id.profile_fio);
        user_title_name = (TextView)findViewById(R.id.profile_title_name);
        user_title_surname = (TextView)findViewById(R.id.profile_title_surname);
        user_city = (TextView)findViewById(R.id.profile_city);
        user_country = (TextView)findViewById(R.id.profile_country);


        user_title_name.setText(user.get("name"));
        user_title_surname.setText(" " + user.get("second_name"));


        user_fio.setText(user.get("name") + " " + user.get("third_name") + " " + user.get("second_name"));

        user_city.setText(user.get("city"));
        user_country.setText(user.get("country"));

//        lv = (ListView) findViewById(R.id.profile_list);


        //вызов тулбара
        initToolbar();
        //вызов бового меню
        initNavigationView();

    }

    //инициализация тулбара
    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Выставление заголовка тулбара
        toolbar.setTitle("Профиль");

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.first_item:
                        // logoutUser();
                        // break;
                }
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    //инициализация бокового меню
    private void initNavigationView() {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        //кнопка бокового меню
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //переход на вкладу при нажатии на пункт бокового меню
        NavigationView NView = (NavigationView) findViewById(R.id.navigation);


        //выставление имени и фамилии пользователя в шапке боковой панели
        db = new SQLiteHandler(getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();

        View header = NView.getHeaderView(0);
        user_title_name = (TextView)header.findViewById(R.id.nav_header_username);
        user_title_surname = (TextView)header.findViewById(R.id.nav_header_usersurname);
        user_title_name.setText(user.get("name"));
        user_title_surname.setText(" " + user.get("second_name"));


        assert NView != null;
        NView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    //выход по кнопке в боковом меню
                    case R.id.schedule:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                        break;
                    case R.id.navigation_menu_logout_btn:
                        logoutUser();
                        break;
                    case R.id.navigation_menu_profile_btn:
                        /*Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent);*/
                        break;
                }
                return true;
            }
        });
    }


    private void logoutUser() {
        session.setLogin(false);
        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}

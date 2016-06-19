package com.chetverg.dongtu_mobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chetverg.dongtu_mobile.R;
import com.chetverg.dongtu_mobile.api.SQLiteHandler;
import com.chetverg.dongtu_mobile.api.SessionManager;

import java.util.HashMap;

/**
 * Created by chetverg on 14.06.16.
 */
public class CoursesActivity extends AppCompatActivity {

    private int LAYOUT = R.layout.courses_activity;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private SQLiteHandler db;
    private SessionManager session;

//карточки
    CardView card;
    LinearLayout cardInner;
    LinearLayout mainsection;
    TextView tv_title;
    TextView tv_teacher;
    TextView tv_dicrip;
    LinearLayout.LayoutParams layout_params;


//имя фамилия пользователя в заголовке
    private TextView user_title_name;
    private TextView user_title_surname;


    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        // session manager
        session = new SessionManager(getApplicationContext());

        db = new SQLiteHandler(getApplicationContext());

        initToolbar();
        initNavigationView();
        addCard("Заголовок1", "Препод1");
        addCard("Заголовок2", "Препод2");
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Выставление заголовка тулбара
        toolbar.setTitle("Курсы");

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
                        Intent intent_profile = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent_profile);
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

    private void logoutUser() {
        session.setLogin(false);
        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(CoursesActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void addCard(String title, String teacher ){
        mainsection = (LinearLayout) findViewById(R.id.courses_main_section);

        card = new CardView(new ContextThemeWrapper(getApplicationContext(), R.style.CardViewStyle), null, 0);
        cardInner = new LinearLayout(new ContextThemeWrapper(getApplicationContext(), R.style.Widget_CardContent));

        tv_title = new TextView(this);
        tv_teacher  = new TextView(this);

        layout_params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
//применять стиль необходимо именно тут, т.к. далее стиль изменяется
        tv_title.setLayoutParams(layout_params);
        tv_teacher.setLayoutParams(layout_params);
        tv_title.setText(title);
        tv_teacher.setText(teacher);


        int margins = 5;
        layout_params.setMargins(margins, margins, margins, margins);
        card.setLayoutParams(layout_params);

        mainsection.addView(card);
        card.addView(cardInner);
        cardInner.addView(tv_title);
        cardInner.addView(tv_teacher);
    }

}

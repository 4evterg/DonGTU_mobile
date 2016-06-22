package com.chetverg.dongtu_mobile.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chetverg.dongtu_mobile.R;
import com.chetverg.dongtu_mobile.api.SQLiteHandler;
import com.chetverg.dongtu_mobile.api.SessionManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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

    private ImageView user_photo;

//карточки
    CardView card;
    LinearLayout card_content;
    LinearLayout mainsection;
    TextView tv_course_name;
    TextView tv_lector;
    TextView tv_more;
    LinearLayout.LayoutParams layout_params;


//имя фамилия пользователя в заголовке
    private TextView user_title_name;


    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        // session manager
        session = new SessionManager(getApplicationContext());

        db = new SQLiteHandler(getApplicationContext());

        initToolbar();
        initNavigationView();
        addCard("Заголовок1", "Препод1", "go");
        addCard("Заголовок2", "Препод2","go2");
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
        user_title_name.setText(user.get("name") + " " + user.get("second_name") + " " + user.get("user_group"));
        user_title_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(i);
            }
        });

        user_photo = (ImageView)header.findViewById(R.id.nav_menu_photo);


        setPhoto(user.get("photo"), user_photo);

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

    public void addCard(String course_name, String lector, String action){
        mainsection = (LinearLayout) findViewById(R.id.courses_main_section);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CardView cw = (CardView) inflater.inflate(R.layout.course_card_layout, null);
        cw.setLayoutParams(new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mainsection.addView(cw);
        tv_lector = (TextView)cw.findViewById(R.id.course_lector);
        tv_course_name = (TextView)cw.findViewById(R.id.course_name);
        tv_more = (TextView)cw.findViewById(R.id.course_go);

        tv_lector.setText(lector);
        tv_course_name.setText(course_name);
        tv_more.setText(action);
        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SingleCourseActivity.class);
                startActivity(i);
            }
        });
    }

    private void setPhoto(String photo_url, ImageView photo_view){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        imageLoader.displayImage(photo_url, photo_view);
    }


}

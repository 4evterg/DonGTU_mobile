package com.chetverg.dongtu_mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;

import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.chetverg.dongtu_mobile.activities.CoursesActivity;
import com.chetverg.dongtu_mobile.activities.LoginActivity;
import com.chetverg.dongtu_mobile.activities.MainActivity;
import com.chetverg.dongtu_mobile.activities.ProfileActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import com.chetverg.dongtu_mobile.api.SQLiteHandler;
import com.chetverg.dongtu_mobile.api.SessionManager;

import java.util.HashMap;

/**
 * Created by chetverg on 05.06.16.
 */
public class SlideMenu  extends NavigationView{

    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private SQLiteHandler db;
    private SessionManager session;

    //имя фамилия пользователя в боковой панели
    private TextView user_name;
    private TextView user_surname;
    private Context ctx;
    private ImageView user_photo;
    private Toolbar toolbar;



   public SlideMenu (final Context ctx, final Toolbar toolbar){
       super(ctx);
       this.ctx = ctx;
       this.toolbar = toolbar;
   }


   public void runMenu(){
       drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

       //открыть меню по нажатию на кнопку
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(((Activity)ctx), drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

       //переход на вкладу при нажатии на пункт бокового меню
       NavigationView NView = (NavigationView) findViewById(R.id.navigation);


       //выставление имени и фамилии пользователя в шапке боковой панели
       db = new SQLiteHandler(ctx);
       HashMap<String, String> user = db.getUserDetails();

       View header = NView.getHeaderView(0);

       user_photo = (ImageView)header.findViewById(R.id.nav_menu_photo);
       setPhoto(ctx, user.get("photo"), user_photo);
       user_name.setText(user.get("name") + " " + user.get("second_name") + " " + user.get("user_group"));
       user_name.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(ctx, ProfileActivity.class);
               ctx.startActivity(i);
           }
       });


       assert NView != null;
       NView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(MenuItem item) {
               drawerLayout.closeDrawers();
               switch (item.getItemId()) {
                   case R.id.schedule:
                       Intent intent_schedule = new Intent(ctx, MainActivity.class);
                       ctx.startActivity(intent_schedule);
                       ((Activity)ctx).finish();
                       break;
                   //выход по кнопке в боковом меню
                   case R.id.navigation_menu_logout_btn:
                       logoutUser();
                       break;
                   //переход на страницу профиля
                   case R.id.navigation_menu_profile_btn:
                       Intent intent_profile = new Intent(ctx, ProfileActivity.class);
                       ctx.startActivity(intent_profile);
                       break;
                   //переход на страницу списка курсов
                   case R.id.navigation_menu_courses_btn:
                       Intent intent_courses = new Intent(ctx, CoursesActivity.class);
                       ctx.startActivity(intent_courses);
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
        Intent i = new Intent(ctx, LoginActivity.class);
        ctx.startActivity(i);
        ((Activity)ctx).finish();
    }
    private void setPhoto(Context ctx, String photo_url, ImageView photo_view){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(ctx));
        imageLoader.displayImage(photo_url, photo_view);
    }



}

package com.chetverg.dongtu_mobile;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

/**
 * Created by chetverg on 05.06.16.
 */
public class SlideMenu{

/*
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private SQLiteHandler db;
    private SessionManager session;

    //имя фамилия пользователя в боковой панели
    private TextView user_name;
    private TextView user_surname;
    private Context ctx;


   public SlideMenu (final Context ctx){
       super(ctx);
       this.ctx = ctx;
   }


   public void runMenu(){
       drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

       //открыть меню по нажатию на кнопку
       // ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
       // drawerLayout.setDrawerListener(toggle);
       // toggle.syncState();

       //переход на вкладу при нажатии на пункт бокового меню
       NavigationView NView = (NavigationView) findViewById(R.id.navigation);


       //выставление имени и фамилии пользователя в шапке боковой панели
       db = new SQLiteHandler(ctx);
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
                   //выход по кнопке в боковом меню
                   case R.id.navigation_menu_logout_btn:
                       //logoutUser();
                       Intent intent_log = new Intent(ctx.getApplicationContext(), LoginActivity.class);
                       ctx.startActivity(intent_log);
                       break;
                   case R.id.navigation_menu_profile_btn:
                       Intent intent_prof = new Intent(ctx.getApplicationContext(), ProfileActivity.class);
                       ctx.startActivity(intent_prof);
                       break;
               }
               return true;
           }
       });
   }


    private void logoutUser(final Intent intent) {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        //Intent intent = new Intent(MainActivity.this, LoginActivity.class);
      //  ctx.startActivity(MainActivity.this, LoginActivity.class);
        //finish();
    }
*/



}

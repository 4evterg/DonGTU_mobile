package com.chetverg.dongtu_mobile.activities;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chetverg.dongtu_mobile.R;
import com.chetverg.dongtu_mobile.api.SQLiteHandler;
import com.chetverg.dongtu_mobile.api.SessionManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.HashMap;

/**
 * Created by chetverg on 05.06.16.
 */
public class ProfileActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    //объявление формы
    private static final int LAYOUT = R.layout.profile_activity;
    //объявление тулбара
    private Toolbar toolbar;

    //имя фамилия пользователя в заголовке
    private TextView user_title_name;
    private TextView user_title_surname;

    //данные пользователя выводимые в основном блоке
    private TextView user_fio;
    private TextView user_city;
    private TextView user_cathedra;
    private TextView user_group;
    private TextView user_post;
    private LinearLayout user_main_photo;


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
        user_cathedra = (TextView)findViewById(R.id.profile_cathedra);
        user_group = (TextView)findViewById(R.id.profile_group);
        user_post = (TextView)findViewById(R.id.profile_post);
        user_main_photo = (LinearLayout)findViewById(R.id.profile_photo);

        setPhoto(user.get("photo"), user_main_photo);

        user_title_name.setText(user.get("name"));
        user_title_surname.setText(" " + user.get("second_name"));


        user_fio.setText(user.get("name") + " " + user.get("third_name") + " " + user.get("second_name"));
        user_city.setText(user.get("country") + ", " + user.get("city"));
        user_cathedra.setText(user.get("cathedra"));
        user_group.setText(user.get("user_group"));
        user_post.setText(user.get("post"));


        //вызов тулбара
        initToolbar();
    }

//обработка нажатия кнопки назад в тулбаре
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    //инициализация тулбара
    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Выставление заголовка тулбара
        toolbar.setTitle("Профиль");

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

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

    private void logoutUser() {
        session.setLogin(false);
        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void setPhoto(String photo_url, LinearLayout main_photo){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        Drawable d = new BitmapDrawable(getResources(), imageLoader.loadImageSync(photo_url));
        main_photo.setBackground(d);
       // imageLoader.displayImage(photo_url, photo_view);
    }

}

package com.chetverg.dongtu_mobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by chetverg on 05.06.16.
 */
public class ProfileActivity extends AppCompatActivity {

    //объявление формы
    private static final int LAYOUT = R.layout.profile_activity;
    //объявление тулбара
    private Toolbar toolbar;

    //имя фамилия пользователя
    private TextView user_name;
    private TextView user_surname;

    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        //вызов тулбара
        initToolbar();

        db = new SQLiteHandler(getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();

//        user_name = (TextView)toolbar.findViewById(R.id.profile_name);
//        user_surname = (TextView)toolbar.findViewById(R.id.profile_surname);

        user_name = (TextView)findViewById(R.id.profile_name);
        user_surname = (TextView)findViewById(R.id.profile_surname);

        user_name.setText(user.get("name"));
        user_surname.setText(" " + user.get("second_name"));
    }

    //инициализация тулбара
    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Выставление заголовка тулбара
        toolbar.setTitle("DonGTU_mobile BETA.Профиль");

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
}

package com.chetverg.dongtu_mobile.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.chetverg.dongtu_mobile.R;

/**
 * Created by chetverg on 25.06.16.
 */
public class PreCourseActivity extends AppCompatActivity {

    private int LAYOUT = R.layout.precourse_activity;
    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        setContentView(LAYOUT);
        super.onCreate(savedInstanceState);

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

}

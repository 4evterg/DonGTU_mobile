package com.chetverg.dongtu_mobile.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.chetverg.dongtu_mobile.R;

/**
 * Created by chetverg on 23.06.16.
 */
public class SingleCourseActivity extends AppCompatActivity {
    public static final String course_id =
            "course_id";
    private static final int LAYOUT = R.layout.single_course_activity;
    private Toolbar toolbar;
    private String course;
    private TextView tw;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        tw = (TextView)findViewById(R.id.single_course_tw);


        Bundle extras = getIntent().getExtras();
        course = extras.getString(course_id);
if (course.equals("Физика")) {tw.setText("Фи́зика (от др.-греч. φύσις — природа) — область естествознания: наука о простейших и, " +
        "вместе с тем, наиболее общих законах природы, о материи, её структуре и движении. Законы физики лежат в основе всего естествознания.");


}
if (course.equals("Программирование")) tw.setText("Программи́рование — процесс создания компьютерных программ.\n" +
        "\n" +
        "По известному выражению Никлауса Вирта «Программы = алгоритмы + структуры данных»; " +
        "иными словами, ключевыми непосредственными задачами программирования являются создание " +
        "и использование алгоритмов и структур данных.");
if (course.equals("Культурология")) tw.setText("Предмет культурологии — исследование феномена культуры как исторически-социального опыта людей, " +
        "который воплощается в специфических нормах, законах и чертах их деятельности, передаётся из поколения в поколение в" +
        " виде ценностных ориентиров и идеалов, интерпретируется в «культурных текстах» философии, религии, искусства и права.");
if (course.equals("Охрана труда")) tw.setText("Охрана труда — система сохранения жизни и здоровья работников в процессе трудовой деятельности, " +
        "включающая в себя правовые, социально-экономические, организационно-технические, санитарно-гигиенические, лечебно-профилактические, реабилитационные " +
        "и иные мероприятия");

        initToolbar();
    }

    //инициализация тулбара
    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Выставление заголовка тулбара
        toolbar.setTitle(course);

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

    //обработка нажатия кнопки назад в тулбаре
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }


}

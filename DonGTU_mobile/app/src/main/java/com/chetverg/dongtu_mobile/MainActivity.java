package com.chetverg.dongtu_mobile;


import android.os.Bundle;
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

import com.chetverg.dongtu_mobile.adapter.TabsPagerFragmentAdapter;

public class MainActivity extends AppCompatActivity {

    //объявление формы
    private static final int LAYOUT = R.layout.activity_main;
    //объявление тулбара
    private Toolbar toolbar;
    //объявление вкладок
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;





    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        //вызов тулбара
        initToolbar();
        //вызов меню
        initNavigationView();
        //вызов вкладок
        initTabs();
        //вызов панели вкладок
        initTabLayout();
    }

    //инициализация тулбара
    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Выставление заголовка тулбара
        toolbar.setTitle("DonGTU_mobile BETA.Главная");
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                return false;
//            }
//        });

        toolbar.inflateMenu(R.menu.menu);
    }

    //подключение выпадающего меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        //открыть меню по нажатию на кнопку
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //переход на вкладу при нажатии на пункт бокового меню
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.schedule:
                        showSecondTab();
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

    private void showSecondTab(){
        viewPager.setCurrentItem(Constants.TAB_TWO);
    }
}

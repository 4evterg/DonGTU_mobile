package com.chetverg.dongtu_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by chetverg on 29.05.16.
 */
public class LoginActivity extends AppCompatActivity {

    // Объявление формы входа
    private static final int LAYOUT = R.layout.login_activity;
    // Объявление кнопки входа
    private Button mLoginButton;


    //Инициализация формы входа
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Выставление стиля формы по-умолчанию
        setTheme(R.style.AppDefault);
        //Подключение формы
        setContentView(LAYOUT);

        //
        mLoginButton = (Button)findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(LoginActivity.this, MainActivity.class);
                startActivityForResult(i, 0);
            }
        });

    }
}

package com.chetverg.dongtu_mobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;



/*import com.chetverg.dongtu_mobile.Constants;
import com.chetverg.dongtu_mobile.AppController;
import com.chetverg.dongtu_mobile.SQLiteHandler;
import com.chetverg.dongtu_mobile.SessionManager;*/

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chetverg on 29.05.16.
 */
public class LoginActivity extends AppCompatActivity {

    //константы необходимые для логина
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    private static final String TAG = "Login";

    private static final int LAYOUT = R.layout.login_activity;

    private Button mLoginButton; //объявление кнопки входа
    private EditText mInputLogin; //объявление поля логина
    private EditText mIputPassword; //объявление поля пароля

    //инициализация формы входа
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //выставление стиля формы по-умолчанию
        setTheme(R.style.AppLogin);
        //Пподключение формы
        setContentView(LAYOUT);

        // Инициализация полей логина и пароля, а так же прогресс диалога
        mInputLogin = (EditText) findViewById(R.id.login_name);
        mIputPassword = (EditText) findViewById(R.id.login_password);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());
        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        //
        mLoginButton = (Button)findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String login = mInputLogin.getText().toString().trim();
                String password = mIputPassword.getText().toString().trim();

                if (!login.isEmpty() && !password.isEmpty()) {
                    // login user
                    checkLogin(login, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }

                //Intent i  = new Intent(LoginActivity.this, MainActivity.class);
                //startActivityForResult(i, 0); //Не забыть изменить на просто стартактивити
            }
        });

    }

    private void checkLogin(final String login, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Выполняется вход ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST,
                Constants.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        session.setLogin(true);

                        // Now store the user in SQLite
                        String uid = jObj.getString("uid");
                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String second_name = user.getString("second_name");


                        // Inserting row in users table
                        db.addUser(name, second_name, uid);

                        // Launch main activity
                        Intent intent = new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("login", login);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}

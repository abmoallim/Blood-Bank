package com.example.bloodbank;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodbank.urlController.urlModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity  {

    EditText et_username, et_Password;
    Button btn_SignIn;
    DatabaseHelper MyDb;

    private CheckBox checkBox;
    public Boolean saveLogin;
    public SharedPreferences loginPreferences;
    public SharedPreferences.Editor loginPrefeEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.userName);
        et_Password = findViewById(R.id.etpassword);
        checkBox = findViewById(R.id.check);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefeEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);

        if (saveLogin == true) {
            et_username.setText(loginPreferences.getString("UserName", ""));
            et_Password.setText(loginPreferences.getString("Password", ""));
            checkBox.setChecked(true);
        }

        btn_SignIn = findViewById(R.id.signin);
        btn_SignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_Password.getText().toString();

                // call login method
                Login(username, password);
            }
        });


    }

    // Log in logic
    private void Login(String username, String password) {

        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, urlModel.login_url, new com.android.volley.Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(LoginActivity.this, "Client: sent data to the API", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);

                    String response_message = respObj.getString("response_message");
                    int response_code = Integer.parseInt(respObj.getString("response_code"));

                    if(response_code == 200){
                        Intent welcome_intent = new Intent();
                        welcome_intent.setClass(LoginActivity.this, DashboardActivity.class);
                        if (checkBox.isChecked()) {
                            loginPrefeEditor.putBoolean("saveLogin", true);
                            loginPrefeEditor.putString("UserName", et_username.getText().toString().trim());
                            loginPrefeEditor.putString("Password", et_Password.getText().toString().trim());
                            loginPrefeEditor.commit();

                        } else {
                            loginPrefeEditor.clear();
                            loginPrefeEditor.commit();
                        }
                        startActivity(welcome_intent);

                    }else{
                        Toast.makeText(LoginActivity.this, response_message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {
            // method to handle errors.
            Toast.makeText(LoginActivity.this, "Failed to get response: " + error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };
        // a json object request.
        queue.add(request);
//        System.out.println(response_code);
    }

}

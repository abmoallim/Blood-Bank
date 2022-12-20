package com.example.bloodbank;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity  {

    EditText et_username, et_Password;
    Button btn_SignIn;
    DatabaseHelper MyDb;

    private CheckBox checkBox;
    public Boolean saveLogin;
    public SharedPreferences loginPreferences;
    public SharedPreferences.Editor loginPrefeEditor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyDb = new DatabaseHelper(this);

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
                if (et_username.getText().toString().equals("Hersi") && et_Password.getText().toString().equals("123")) {
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

                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials.", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}

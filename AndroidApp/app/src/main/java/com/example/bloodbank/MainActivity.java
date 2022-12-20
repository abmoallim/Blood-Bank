package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView btn_Go_SignUp, btn_Go_SignIn, btn_go_shutdown;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Go_SignIn = findViewById(R.id.go_signin);
        btn_Go_SignIn.setOnClickListener(this);

        btn_Go_SignUp = findViewById(R.id.go_signup);
        btn_Go_SignUp.setOnClickListener(this);

        btn_go_shutdown=findViewById(R.id.go_shoutdown);
        btn_go_shutdown.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go_signup:
                Intent register_intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(register_intent);
                break;
            case R.id.go_signin:
                Intent login_intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login_intent);
                break;
            case R.id.go_shoutdown:
                MainActivity.this.finish();
//                Toast.makeText(MainActivity.this, "Here we go", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
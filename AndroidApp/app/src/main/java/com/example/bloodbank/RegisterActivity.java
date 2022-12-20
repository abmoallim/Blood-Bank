package com.example.bloodbank;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodbank.DatabaseHelper;
import com.example.bloodbank.LoginActivity;
import com.example.bloodbank.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    EditText et_name, et_phone, et_date, et_address, et_weight;
//    DatePicker dp_dop;
    Button btn_signUp;
    DatabaseHelper MyDb;
    Toolbar toolbar;
    final Calendar myCalendar= Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MyDb = new DatabaseHelper(this);

        et_name = findViewById(R.id.etname);
        et_phone = findViewById(R.id.etphone);
        et_date = findViewById(R.id.dpdop);
        et_address = findViewById(R.id.etaddress);
        et_weight = findViewById(R.id.etweight);

//        toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getSupportActionBar().setTitle("Register Now");
//        setTitle(Color.WHITE);
//        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        btn_signUp = findViewById(R.id.signup);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Name validation
                EditText etName = findViewById(R.id.etname);
                if (etName.getText().toString().isEmpty()) {
                    etName.setError("this field is required!");
                    return;
                }

                if (etName.getText().toString().length() < 3) {
                    etName.setError("Name field  must be 3 or above characters");
                    return;
                }

                // Phone validation
                EditText etPhone = findViewById(R.id.etphone);

                if (etPhone.getText().toString().isEmpty()) {
                    etPhone.setError("this field is required!");
                    return;
                }

                if (etPhone.getText().toString().length() < 9 || etPhone.getText().toString().length() > 9) {
                    etPhone.setError("Phone field  must be 9 characters long");
                    return;
                }

                // Address validation
                EditText etAddress = findViewById(R.id.etaddress);

                if (etAddress.getText().toString().isEmpty()) {
                    etAddress.setError("this field is required!");
                    return;
                }

                // Weight validation
                EditText etWeight = findViewById(R.id.etaddress);

                if (etWeight.getText().toString().isEmpty()) {
                    etWeight.setError("this field is required!");
                    return;
                }

                // Instantiate the RequestQueue.
//                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
//                String url = "https://www.metaweather.com/api/location/search/?query=44418";
//
//                // Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
////                        Toast.makeText(RegisterActivity.this, response.substring(0,500), Toast.LENGTH_LONG).show();
//                        Log.d("Response Message", response);
//                    }
//                    }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(RegisterActivity.this, "That didn't work!", Toast.LENGTH_LONG).show();
//                    }
//                });
//
//                // Add the request to the RequestQueue.
//                queue.add(stringRequest);

                // Check User's UserName whether its in our database.
                Cursor cursor = MyDb.Login("Hersi");
                if(cursor.getCount()  == 0){

                    boolean isInserted = MyDb.RegisterDonor(
                            et_name.getText().toString(),
                            et_phone.getText().toString(),
                            et_date.getText().toString(),
                            et_address.getText().toString(),
                            et_weight.getText().toString());
                    if (isInserted == true)
                        Toast.makeText(RegisterActivity.this,"Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(RegisterActivity.this,"Data is not Inserted", Toast.LENGTH_LONG).show();


                    Intent register_intent = new Intent();
                    register_intent.setClass(RegisterActivity.this, LoginActivity.class);
                    Toast.makeText(RegisterActivity.this,"Your are Successfully Registered ! Now you can Login", Toast.LENGTH_LONG).show();
                    startActivity(register_intent);

                }else {
                    Toast.makeText(RegisterActivity.this, "This StudentNumber is already registered!", Toast.LENGTH_LONG).show();
                }
            }
        });


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                String myFormat="MM/dd/yy";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                et_date.setText(dateFormat.format(myCalendar.getTime()));
            }
        };
        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterActivity.this, date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.login_menu,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id=item.getItemId();
//        switch (id){
//
//            case R.id.ho_exit:
//                RegisterActivity.this.finish();
//
//            case android.R.id.home:
//                RegisterActivity.this.finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }

}

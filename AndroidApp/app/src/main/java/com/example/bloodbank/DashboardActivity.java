package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView btn_donate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btn_donate = findViewById(R.id.donate);
        btn_donate.setOnClickListener(this);



        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(DashboardActivity.this);
        String url = "http://192.168.100.100:5000/TotalDonors";

        // Request a string response from the provided URL.
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(DashboardActivity.this, "Hi there", Toast.LENGTH_LONG);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Message", error.toString());
            }
        });
        StringRequest request2 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                Toast.makeText(DashboardActivity.this, response, Toast.LENGTH_LONG).show();
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DashboardActivity.this, "That didn't work!", Toast.LENGTH_LONG).show();
                Log.d("Error Message", error.toString());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(request);
        queue.add(request2);


//
//        toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        getSupportActionBar().setTitle("Course Management Application");
////        setTitle(Color.WHITE);
//        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.donate:
                Intent Donate_Intent = new Intent(DashboardActivity.this, DonationActivity.class);
                startActivity(Donate_Intent);
//                Toast.makeText(DashboardActivity.this, "Here we go", Toast.LENGTH_LONG).show();
                break;
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.optional_menu_item,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id=item.getItemId();
//        switch (id){
//            case R.id.ho_exit:
//                Dashboard.this.finish();
//            case android.R.id.home:
//                Dashboard.this.finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
}

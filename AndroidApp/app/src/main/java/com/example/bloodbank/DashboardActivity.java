package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodbank.adapters.dashboard_adapter;
import com.example.bloodbank.urlController.urlModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DashboardActivity extends AppCompatActivity {

    ImageView btn_donate;
    RecyclerView rv_cards_holder;
    RecyclerView.Adapter dashboardAdapter;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        btn_donate = findViewById(R.id.donate);
//        btn_donate.setOnClickListener(this);

        rv_cards_holder = findViewById(R.id.cards_holder);

        GridLayoutManager gridLayout = new GridLayoutManager(getApplicationContext(), 2);
        rv_cards_holder.setLayoutManager(gridLayout);

        getDashboardData();


    }

    private void getDashboardData(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, urlModel.dashboard_data, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                    dashboardAdapter = new dashboard_adapter(DashboardActivity.this, response);
                    rv_cards_holder.setAdapter(dashboardAdapter);

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error Message", error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        requestQueue.add(request);
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.donate:
//                Intent Donate_Intent = new Intent(DashboardActivity.this, DonationActivity.class);
//                startActivity(Donate_Intent);
////                Toast.makeText(DashboardActivity.this, "Here we go", Toast.LENGTH_LONG).show();
//                break;
//        }
//    }


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

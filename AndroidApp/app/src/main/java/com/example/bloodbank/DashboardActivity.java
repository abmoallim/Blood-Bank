package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodbank.R;
import com.example.bloodbank.R.id;
import com.example.bloodbank.adapters.dashboard_adapter;
import com.example.bloodbank.urlController.urlModel;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView iv_donors, iv_recipients;
    RecyclerView rv_cards_holder;
    RecyclerView.Adapter dashboardAdapter;

    TextView tvR, tvPython, tvCPP, tvJava, tv_name, tv_total_hospitals, tv_total_donors, tv_total_recipients, tv_total_blood;
    PieChart pieChart;

//    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        tv_name = findViewById(id.name);
//        tv_name.setOnClickListener(this);
//
//        iv_recipients = findViewById(R.id.recipients);
//        iv_recipients.setOnClickListener(this);

//        rv_cards_holder = findViewById(R.id.cards_holder);

//        tvR = findViewById(R.id.tvR);
//        tvPython = findViewById(R.id.tvPython);
//        tvCPP = findViewById(R.id.tvCPP);
//        tvJava = findViewById(R.id.tvJava);
//        pieChart = findViewById(R.id.piechart);

        tv_total_hospitals = findViewById(R.id.total_hospitals);
        tv_total_blood = findViewById(R.id.total_blood);
        tv_total_donors = findViewById(R.id.total_donors);
        tv_total_recipients = findViewById(id.total_recipients);

        iv_recipients = findViewById(id.recipients);
        iv_recipients.setOnClickListener(this);

        iv_donors = findViewById(id.donors);
        iv_donors.setOnClickListener(this);

//        GridLayoutManager gridLayout = new GridLayoutManager(getApplicationContext(), 2);
//        rv_cards_holder.setLayoutManager(gridLayout);
//        Toast.makeText(DashboardActivity.this, tv_total_donors.getText(), Toast.LENGTH_LONG).show();
        getDashboardData();

//        setData();


    }

//    private void setData(){
//        // Set the percentage of language used
//        tvR.setText(Integer.toString(40));
//        tvPython.setText(Integer.toString(30));
//        tvCPP.setText(Integer.toString(5));
//        tvJava.setText(Integer.toString(25));
//
//        // Set the data and color to the pie chart
//        pieChart.addPieSlice(new PieModel("R", Integer.parseInt(tvR.getText().toString()), Color.parseColor("#FFA726")));
//        pieChart.addPieSlice(new PieModel("Python", Integer.parseInt(tvPython.getText().toString()), Color.parseColor("#66BB6A")));
//        pieChart.addPieSlice(new PieModel("C++", Integer.parseInt(tvCPP.getText().toString()), Color.parseColor("#EF5350")));
//        pieChart.addPieSlice(new PieModel("Java", Integer.parseInt(tvJava.getText().toString()), Color.parseColor("#29B6F6")));
//
//        pieChart.startAnimation();
//    }

    private void getDashboardData(){
        @SuppressLint("LongLogTag")
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, urlModel.dashboard_data_url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
//                System.out.println(response);
                try {
                    JSONObject hospital_data = response.getJSONObject(0);
                    JSONObject donor_data = response.getJSONObject(1);
                    JSONObject recipient_data = response.getJSONObject(2);
                    JSONObject blood_data = response.getJSONObject(3);
//                    System.out.println(donor_data);
                    tv_total_hospitals.setText(hospital_data.getString("total"));
                    tv_total_donors.setText(donor_data.getString("total"));
                    tv_total_recipients.setText(recipient_data.getString("total"));
                    tv_total_blood.setText(blood_data.getString("total"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                    dashboardAdapter = new dashboard_adapter(DashboardActivity.this, response);
//                    rv_cards_holder.setAdapter(dashboardAdapter);

            }
        }, error -> Log.i("Error Message ------- ------- ------- ", error.getMessage()));

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        requestQueue.add(request);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case id.donors:
                Intent donors_intent = new Intent(DashboardActivity.this, DonorsActivity.class);
                startActivity(donors_intent);
                break;
            case id.recipients:
                Intent recipients_intent = new Intent(DashboardActivity.this, RecipientsActivity.class);
                startActivity(recipients_intent);
                break;
        }
    }

}

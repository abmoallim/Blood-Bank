package com.example.bloodbank;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodbank.urlController.urlModel;
import com.google.android.material.datepicker.MaterialDatePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class DonationActivity extends AppCompatActivity {

    EditText et_quantity, et_date;
    Spinner sp_donor, sp_hospital;
    Button btn_donate;
    Context context;

    final Calendar myCalendar= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        et_quantity = findViewById(R.id.quantity);
        sp_donor = findViewById(R.id.donor);
        sp_hospital = findViewById(R.id.hospital);
        btn_donate = findViewById(R.id.donate);
        et_date =  findViewById(R.id.date);
        context = this;

        // results list request
        @SuppressLint("LongLogTag")
        JsonArrayRequest results_request = new JsonArrayRequest(Request.Method.GET, urlModel.result_url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<State> results = new ArrayList<>();
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject result = response.getJSONObject(i);
                        JSONObject donor = new JSONObject(result.getString("donor"));
                        results.add(new State(result.getString("id"), donor.getString("name")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                //fill data in spinner
                ArrayAdapter<State> adapter = new ArrayAdapter<State>(context, android.R.layout.simple_spinner_dropdown_item, results);
                sp_donor.setAdapter(adapter);
            }
        }, error -> Log.i("Error Message ------- ------- ------- ", error.getMessage()));

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(results_request);

        // hospitals list request
        @SuppressLint("LongLogTag")
        JsonArrayRequest blood_request = new JsonArrayRequest(Request.Method.GET, urlModel.hospitals_url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<State> hospitals = new ArrayList<>();
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject hospital = response.getJSONObject(i);
                        hospitals.add(new State(hospital.getString("id"), hospital.getString("name")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                //fill data in spinner
                ArrayAdapter<State> adapter = new ArrayAdapter<State>(context, android.R.layout.simple_spinner_dropdown_item, hospitals);
                sp_hospital.setAdapter(adapter);
            }
        }, error -> Log.i("Error Message ------- ------- ------- ", error.getMessage()));

        requestQueue.add(blood_request);

        btn_donate = findViewById(R.id.donate);
        btn_donate.setOnClickListener(v -> {

            // Quantity validation
            EditText etQuantity = findViewById(R.id.quantity);
            if (etQuantity.getText().toString().isEmpty()) {
                etQuantity.setError("this field is required!");
                return;
            }

            // call RecordDonation method and pass its arguments
            try {
                RecordDonation(
                    ((State) sp_donor.getSelectedItem()).getId(),
                    ((State) sp_hospital.getSelectedItem()).getId(),
                    et_quantity.getText().toString(),
                    et_date.getText().toString()
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });


        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            String myFormat="yyyy-MM-dd";
            SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
            et_date.setText(dateFormat.format(myCalendar.getTime()));
        };
        et_date.setOnClickListener(view -> new DatePickerDialog(DonationActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show());

    }

    // Donation Record logic
    private void RecordDonation(String result_id, String hospital_id, String quantity, String date) throws JSONException {

        RequestQueue queue = Volley.newRequestQueue(context);
        JSONObject result_data = new JSONObject();
        result_data.put("id", Integer.parseInt(result_id));

        JSONObject hospital_data = new JSONObject();
        hospital_data.put("id", Integer.parseInt(hospital_id));

        // request parameters
        JSONObject params = new JSONObject();
        params.put("quantity", quantity);
        params.put("date", date);
        params.put("results", result_data);
        params.put("hospital", hospital_data);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, urlModel.donation_url, params, response -> {
            Toast.makeText(context, "Client: sent data to the API", Toast.LENGTH_SHORT).show();
            if(response.length() == 5){
                Intent dashboard_intent = new Intent();
                dashboard_intent.setClass(context, DashboardActivity.class);
                Toast.makeText(context, "Successfully recorded", Toast.LENGTH_SHORT).show();
                startActivity(dashboard_intent);

            }else{
                Toast.makeText(context, "Failed to record", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            // method to handle errors.
            System.out.println(error);
            Toast.makeText(context, "Failed to get response: ", Toast.LENGTH_SHORT).show();
        });
        // a json object request.
        queue.add(request);
    }
}

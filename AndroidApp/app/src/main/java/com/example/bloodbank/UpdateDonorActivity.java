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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodbank.urlController.urlModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class UpdateDonorActivity extends AppCompatActivity {

    EditText et_name, et_phone, et_date, et_address, et_weight;
    Spinner sp_state;
    Button btn_update;
    final Calendar myCalendar= Calendar.getInstance();
    private Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_donor);
        context = this;

        et_name = findViewById(R.id.etname);
        et_phone = findViewById(R.id.etphone);
        et_date = findViewById(R.id.dpdop);
        et_address = findViewById(R.id.etaddress);
        et_weight = findViewById(R.id.etweight);
        sp_state = findViewById(R.id.state);

        // Call getDonorData method
        String donor_id = getIntent().getStringExtra("donor_id");
        getDonorData(donor_id);


        btn_update = findViewById(R.id.update);
        btn_update.setOnClickListener(new View.OnClickListener() {

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

                // call UpdateDonor method and pass its arguments
                UpdateDonor(
                        donor_id,
                        et_name.getText().toString(),
                        et_phone.getText().toString(),
                        et_date.getText().toString(),
                        et_address.getText().toString(),
                        et_weight.getText().toString(),
                        ((State) sp_state.getSelectedItem()).getId()
                );
            }
        });

        // Date picker
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
                new DatePickerDialog(context, date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }


    // Donor registration logic
    private void UpdateDonor(String donor_id, String name, String phone, String dop, String address, String weight, String state) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = urlModel.update_donor + donor_id + "/Update";
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Client: sent data to the API", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);

                    String response_message = respObj.getString("response_message");
                    int response_code = Integer.parseInt(respObj.getString("response_code"));

                    if(response_code == 200){
                        Intent donors_intent = new Intent();
                        donors_intent.setClass(context, DonorsActivity.class);
                        Toast.makeText(context, response_message, Toast.LENGTH_SHORT).show();
                        startActivity(donors_intent);

                    }else{
                        Toast.makeText(context, response_message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {
            // method to handle errors.
            Toast.makeText(context, "Failed to get response: " + error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("name", name);
                params.put("phone", phone);
                params.put("dop", dop);
                params.put("address", address);
                params.put("weight", weight);
                params.put("state", state);

                return params;
            }
        };
        // a json object request.
        queue.add(request);
    }

    private void getDonorData(String donor_id){
        // modify url
        String url = urlModel.update_donor + donor_id + "/Update";

        @SuppressLint("LongLogTag")
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
               // System.out.println(response);
                try {
                    JSONObject data = response.getJSONObject(0);
                    JSONArray states_array = new JSONArray(data.getString("states"));

                    // convert states array to list in order to populate
                    ArrayList<State> states_list = new ArrayList<>();
                    for(int i = 0; i < states_array.length(); i++){
                            JSONObject state = states_array.getJSONObject(i);
                            states_list.add(new State(state.getString("id"), state.getString("name")));
                    }

                    //fill data in spinner
                    ArrayAdapter<State> adapter = new ArrayAdapter<State>(context, android.R.layout.simple_spinner_dropdown_item, states_list);
                    sp_state.setAdapter(adapter);

                    // select donor's state
                    sp_state.setSelection(Integer.parseInt(data.getString("state_index")));

                    // pass donor data to the EditText
                    et_name.setText(data.getString("name"));
                    et_phone.setText(data.getString("phone"));
                    et_date.setText(data.getString("dop"));
                    et_address.setText(data.getString("address"));
                    et_weight.setText(data.getString("weight"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, error -> Log.i("Error Message ------- ------- ------- ", error.getMessage()));

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        requestQueue.add(request);
    }
}

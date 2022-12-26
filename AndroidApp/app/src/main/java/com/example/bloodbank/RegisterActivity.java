package com.example.bloodbank;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodbank.adapters.dashboard_adapter;
import com.example.bloodbank.urlController.urlModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText et_name, et_phone, et_date, et_address, et_weight;
    Spinner sp_state;
    Button btn_signUp;
    final Calendar myCalendar= Calendar.getInstance();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = this;

        et_name = findViewById(R.id.etname);
        et_phone = findViewById(R.id.etphone);
        et_date = findViewById(R.id.dpdop);
        et_address = findViewById(R.id.etaddress);
        et_weight = findViewById(R.id.etweight);
        sp_state = findViewById(R.id.state);


        @SuppressLint("LongLogTag")
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, urlModel.states_list_url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
//                System.out.println(response);

                ArrayList<State> states = new ArrayList<>();
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject state = response.getJSONObject(i);
                        states.add(new State(state.getString("id"), state.getString("name")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                //fill data in spinner
                ArrayAdapter<State> adapter = new ArrayAdapter<State>(context, android.R.layout.simple_spinner_dropdown_item, states);
                sp_state.setAdapter(adapter);
            }
        }, error -> Log.i("Error Message ------- ------- ------- ", error.getMessage()));

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        requestQueue.add(request);

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

                // call RegisterDonor method and pass its arguments
                RegisterDonor(
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
    private void RegisterDonor(String name, String phone, String dop, String address, String weight, String state) {

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, urlModel.register_url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Client: sent data to the API", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);

                    String response_message = respObj.getString("response_message");
                    int response_code = Integer.parseInt(respObj.getString("response_code"));

                    if(response_code == 201){
                        Intent register_intent = new Intent();
                        register_intent.setClass(context, LoginActivity.class);
                        Toast.makeText(context, response_message, Toast.LENGTH_SHORT).show();
                        startActivity(register_intent);

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
}



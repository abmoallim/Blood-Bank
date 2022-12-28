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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class UpdateRecipientActivity extends AppCompatActivity {

    EditText et_name, et_phone, et_date;
    Spinner sp_state, sp_blood;
    Button btn_update;
    final Calendar myCalendar= Calendar.getInstance();
    private Context context;
    public String blood_name, state_name;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipient);
        context = this;

        et_name = findViewById(R.id.etname);
        et_phone = findViewById(R.id.etphone);
        et_date = findViewById(R.id.dpdop);
        sp_blood = findViewById(R.id.blood);
        sp_state = findViewById(R.id.state);

        // Call getRecipientData method
        String recipient_id = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        getRecipientData(recipient_id);


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

                // call UpdateRecipient method and pass its arguments
                try {
                    UpdateRecipient(
                            recipient_id,
                            ((State) sp_state.getSelectedItem()).getId(),
                            ((State) sp_blood.getSelectedItem()).getId(),
                            et_name.getText().toString(),
                            et_phone.getText().toString(),
                            et_date.getText().toString()
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        // Date picker
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                String myFormat="yyyy-MM-dd";
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


    // Recipient update logic
    private void UpdateRecipient(String recipient_id, String state_id, String blood_id, String name, String phone, String dop) throws JSONException {

        RequestQueue queue = Volley.newRequestQueue(context);

        JSONObject state_json_data = new JSONObject();
        state_json_data.put("id", Integer.parseInt(state_id));

        JSONObject blood_json_data = new JSONObject();
        blood_json_data.put("id", Integer.parseInt(blood_id));

        // request parameters
        JSONObject params = new JSONObject();
        params.put("id", Integer.parseInt(recipient_id));
        params.put("name", name);
        params.put("phone", phone);
        params.put("brithDate", dop);
        params.put("username", "Hersi");
        params.put("status", "Active");
        params.put("state", state_json_data);
        params.put("bloodType", blood_json_data);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, urlModel.update_recipient, params, response -> {
            Toast.makeText(context, "Client: sent data to the API", Toast.LENGTH_SHORT).show();
            if(response.length() == 9){
                Intent recipients_intent = new Intent();
                recipients_intent.setClass(context, RecipientsActivity.class);
                Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show();
                startActivity(recipients_intent);

            }else{
                Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            // method to handle errors.
            Toast.makeText(context, "Failed to get response: " + error, Toast.LENGTH_SHORT).show();
        });
        // a json object request.
        queue.add(request);
    }

    private void getRecipientData(String recipient_id){

        // EditText data request
        String url = urlModel.update_recipient + recipient_id;
        @SuppressLint("LongLogTag")
        JsonObjectRequest et_request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                JSONObject data = response;
                try {
                    JSONObject recipient_blood = new JSONObject(response.getString("bloodType"));
                    JSONObject recipient_state = new JSONObject(response.getString("state"));
                    blood_name = recipient_blood.getString("bloodName");
                    state_name = recipient_state.getString("stateName");
                    et_name.setText(response.getString("name"));
                    et_phone.setText(response.getString("phone"));
                    et_date.setText(response.getString("brithDate"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, error -> Log.i("Error Message ------- ------- ------- ", error.getMessage()));

        // Blood Spinner data request
        @SuppressLint("LongLogTag")
        JsonArrayRequest blood_request = new JsonArrayRequest(Request.Method.GET, urlModel.blood_url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<State> bloods = new ArrayList<State>();
                ArrayList<String> blood_arr = new ArrayList<String>();
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject blood = response.getJSONObject(i);
                        bloods.add(new State(blood.getString("id"), blood.getString("bloodName")));
                        blood_arr.add(blood.getString("bloodName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                //fill data to the spinner
                ArrayAdapter<State> adapter = new ArrayAdapter<State>(context, android.R.layout.simple_spinner_dropdown_item, bloods);
                sp_blood.setAdapter(adapter);
                int index = blood_arr.indexOf(blood_name);
                blood_arr.clear();
                System.out.println(index);
                sp_blood.setSelection(index);
            }
        }, error -> Log.i("Error Message ------- ------- ------- ", error.getMessage()));


        // State Spinner data request
        @SuppressLint("LongLogTag")
        JsonArrayRequest states_request = new JsonArrayRequest(Request.Method.GET, urlModel.states_url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<String> state_arr = new ArrayList<String>();
                ArrayList<State> states = new ArrayList<>();
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject state = response.getJSONObject(i);
                        states.add(new State(state.getString("id"), state.getString("stateName")));
                        state_arr.add(state.getString("stateName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                //fill data in spinner
                ArrayAdapter<State> adapter = new ArrayAdapter<State>(context, android.R.layout.simple_spinner_dropdown_item, states);
                sp_state.setAdapter(adapter);
                int index = state_arr.indexOf(state_name);
                state_arr.clear();
                System.out.println(index);
                sp_state.setSelection(index);
            }
        }, error -> Log.i("Error Message ------- ------- ------- ", error.getMessage()));


        // que requests
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(et_request);
        requestQueue.add(blood_request);
        requestQueue.add(states_request);
    }

}

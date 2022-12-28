package com.example.bloodbank;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodbank.adapters.dashboard_adapter;
import com.example.bloodbank.urlController.urlModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DonorsActivity extends AppCompatActivity implements View.OnClickListener{

    TableLayout table_layout;
    Button register_btn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors);
        context = this;

        table_layout  = findViewById(R.id.table_layout);
        register_btn = findViewById(R.id.register);
        register_btn.setOnClickListener((View.OnClickListener) context);



        TableRow table_head = new TableRow(context);

        String[] header_text = {"No", "Name", "Phone", "Age", "Weight", "Blood", "Update"};
        for(String text:header_text) {
            TextView tv_th = new TextView(this);
            tv_th.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            tv_th.setTextSize(19);
            tv_th.setGravity(Gravity.CENTER);
            tv_th.setTypeface(null, Typeface.BOLD);
            tv_th.setPadding(15, 15, 15, 15);
            tv_th.setText(text);
            table_head.addView(tv_th);
        }
        table_layout.addView(table_head);

        @SuppressLint("LongLogTag")
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, urlModel.donors_url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject donor = response.getJSONObject(i);
                        TableRow  row = new TableRow(context);
                        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));

                        // get donor age by using his/her birthdate
                        Date current_date = Calendar.getInstance().getTime();
                        Date date  = new Date();
                        try {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            date = format.parse(donor.getString("brithDate"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        long age = ((Math.abs(current_date.getTime() - date.getTime())/(24 * 60 * 60 * 1000))/365);
                        JSONObject donor_blood = new JSONObject(donor.getString("bloodType"));
                        String[] blodyText = {String.valueOf(i+1), donor.getString("name"), donor.getString("phone"), String.valueOf(age), donor.getString("weight"), donor_blood.getString("bloodName")};
                        int counter = 0;

                        for(String text:blodyText) {
                            counter ++;
                            final TextView tv_td = new TextView(context);
                            tv_td.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                            tv_td.setTextSize(17);
                            tv_td.setGravity(Gravity.CENTER);
                            tv_td.setText(text);

                            row.addView(tv_td);

                            // Add edit and delete buttons at the end of each row
                            if (counter == 6){
                                final Button edit_btn = new Button(context);
                                edit_btn.setText("Edit");
                                edit_btn.setTextColor(Color.parseColor("#ffb300"));
                                edit_btn.setGravity(Gravity.CENTER);

                                // add button view to the row
                                row.addView(edit_btn);

                                // add click_listener to the edit button
                                edit_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        try {
                                            Intent update_donor_intent = new Intent(context, UpdateDonorActivity.class);
                                            update_donor_intent.putExtra(Intent.EXTRA_TEXT, donor.getString("id"));
                                            startActivity(update_donor_intent);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });


//                                final Button delete_btn = new Button(context);
//                                delete_btn.setText("Drop");
//                                delete_btn.setTextColor(Color.parseColor("#ff4081"));
//                                delete_btn.setGravity(Gravity.CENTER);
//
//                                // add delete button to the row
//                                row.addView(delete_btn);
//
//                                // add click_listener to the edit button
//                                delete_btn.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        try {
////                                            Toast.makeText(context, donor.getString("index") + ": " + donor.getString("name"), Toast.LENGTH_LONG).show();
//                                            // call delete alert method
//                                            DeleteAlert(Integer.parseInt(donor.getString("id")));
//                                        }catch (JSONException e){
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                });
                            }
                        }
                        table_layout.addView(row);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, error -> Log.i("Error Message ------- ------- ------- ", error.getMessage()));

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);


    }

    public void DeleteAlert(int donor_id){
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure to delete ?");
        builder.setTitle("Alert!!");
        builder.setCancelable(false);

        // call donor DELETE api if user clicks yes
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            String url = urlModel.delete_donor + donor_id;
            RequestQueue queue = Volley.newRequestQueue(context);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>(){
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println(response);
                    Toast.makeText(context, "Client: sent data to the API", Toast.LENGTH_SHORT).show();

                    try {
                        String response_message = response.getString("response_message");
                        int response_code = Integer.parseInt(response.getString("response_code"));

                        finish();
                        startActivity(getIntent());
                        if(response_code == 200){
                            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, error -> {
                // method to handle errors.
                System.out.println(error);
                Toast.makeText(context, "Failed to get response: " + error, Toast.LENGTH_SHORT).show();
            });
            // a json object request.
            queue.add(request);
        });

        // close dialog if user clicks no
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                Intent register_intent = new Intent(context, RegisterActivity.class);
                startActivity(register_intent);
                break;
        }
    }
}



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

public class DonorsActivity extends AppCompatActivity {

    TableLayout table_layout;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors);
        context = this;

        table_layout  = findViewById(R.id.table_layout);



        TableRow rowHeader = new TableRow(context);
//        rowHeader.setBackgroundColor(Color.parseColor("#51B435"));

        String[] header_text = {"No", "Name", "Phone", "Age", "Weight", "Blood", "Update", "Delete"};
        for(String text:header_text) {
            TextView tv_th = new TextView(this);
            tv_th.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            tv_th.setTextSize(19);
            tv_th.setGravity(Gravity.CENTER);
            tv_th.setTypeface(null, Typeface.BOLD);
            tv_th.setPadding(15, 15, 15, 15);
            tv_th.setText(text);
            rowHeader.addView(tv_th);
        }
        table_layout.addView(rowHeader);

        @SuppressLint("LongLogTag")
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, urlModel.donors_url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
//                System.out.println(response);

                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject donor = response.getJSONObject(i);
                        TableRow  row = new TableRow(context);
                        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));

                        String[] blodyText = {donor.getString("index"), donor.getString("name"), donor.getString("phone"), donor.getString("age"), donor.getString("weight"), donor.getString("blood")};
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
                                            int donor_id = Integer.parseInt(donor.getString("index"));
//                                            Toast.makeText(context, donor.getString("index") + ": " + donor.getString("name"), Toast.LENGTH_LONG).show();
                                            Intent update_donor_intent = new Intent(context, UpdateDonorActivity.class);
                                            update_donor_intent.putExtra("donor_id", donor_id);
                                            startActivity(update_donor_intent);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });


                                final Button delete_btn = new Button(context);
                                delete_btn.setText("Drop");
                                delete_btn.setTextColor(Color.parseColor("#ff4081"));
                                delete_btn.setGravity(Gravity.CENTER);

                                // add delete button to the row
                                row.addView(delete_btn);

                                // add click_listener to the edit button
                                delete_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        try {
//                                            Toast.makeText(context, donor.getString("index") + ": " + donor.getString("name"), Toast.LENGTH_LONG).show();
                                            // call delete alert method
                                            DeleteAlert(Integer.parseInt(donor.getString("index")));
                                        }catch (JSONException e){
                                            e.printStackTrace();
                                        }
                                    }
                                });
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

        // call donor PUT api if user clicks yes
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            String url = urlModel.delete_donor + donor_id + "/Delete";
            RequestQueue queue = Volley.newRequestQueue(context);
            StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(context, "Client: sent data to the API", Toast.LENGTH_SHORT).show();

                    try {
                        JSONObject respObj = new JSONObject(response);

                        String response_message = respObj.getString("response_message");
                        int response_code = Integer.parseInt(respObj.getString("response_code"));

                        if(response_code == 200){
                            finish();
                            startActivity(getIntent());
                            Toast.makeText(context, response_message, Toast.LENGTH_SHORT).show();

                        }else{
                            finish();
                            startActivity(getIntent());
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

                    params.put("id", String.valueOf(donor_id));

                    return params;
                }
            };
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

}



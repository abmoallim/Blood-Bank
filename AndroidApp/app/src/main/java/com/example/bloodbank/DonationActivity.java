package com.example.bloodbank;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DonationActivity extends AppCompatActivity {

    EditText et_quantity, et_date;
    Spinner sp_donor, sp_hospital;
    Button btn_donate;

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

        final String[] donors = {"Donor A", "Donor B", "Donor C"};
        final  String[] hospitals = {"Hospital A", "Hospital B", "Hospital C"};
        ArrayAdapter<String>DonorsAdapter = new ArrayAdapter<String>(DonationActivity.this, android.R.layout.simple_spinner_item, donors);
        ArrayAdapter<String>HospitalAdapter = new ArrayAdapter<String>(DonationActivity.this, android.R.layout.simple_spinner_item, hospitals);

        DonorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HospitalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_donor.setAdapter(DonorsAdapter);
        sp_hospital.setAdapter(HospitalAdapter);


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
        et_date.setOnClickListener(view -> new DatePickerDialog(DonationActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show());

    }
}

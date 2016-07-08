package com.example.hussain.bloodconnectserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class status extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
Spinner spinner;
    TextView tx;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
       pass = getIntent().getExtras().getString("arg");
        tx = (TextView) findViewById(R.id.textView);

        tx.setText(pass);
        spinners();
    }
    public void spinners() {
        spinner = (Spinner) findViewById(R.id.spinner1);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("Health Checkup");
        categories.add("Donation");
        categories.add("Refreshment");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        if(item.equalsIgnoreCase("Health Checkup"))
        {
            Intent intent = new Intent(status.this, HealthCheckup.class);

           intent.putExtra("arg", pass);
            startActivity(intent);
            finish();
        }
        if(item.equalsIgnoreCase("Donation"))
        {
            Intent intent = new Intent(status.this, donation.class);

             intent.putExtra("arg", pass);
            startActivity(intent);
            finish();
        }
        if(item.equalsIgnoreCase("Refreshment"))
        {
            Intent intent = new Intent(status.this, refreshment.class);

             intent.putExtra("arg", pass);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

package com.example.hussain.bloodconnectserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class donation extends AppCompatActivity {
TextView tx;
    String pass;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        pass = getIntent().getExtras().getString("arg");
        tx=(TextView)findViewById(R.id.textView);
        tx.setText(pass);
        b=(Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(donation.this,show_donor.class);
                i.putExtra("arg",pass);
                startActivity(i);
                finish();
            }
        });

    }
}

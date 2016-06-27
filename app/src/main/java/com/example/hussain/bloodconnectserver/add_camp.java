package com.example.hussain.bloodconnectserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_camp extends AppCompatActivity {
    Button b;
    String name;
    EditText ed1;
    select_camp obj=new select_camp();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_camp);
        b=(Button)findViewById(R.id.submit);
        ed1=(EditText)findViewById(R.id.editText2);
        name=ed1.getText().toString();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Camp Created!",Toast.LENGTH_SHORT).show();
               Intent i=new Intent(add_camp.this,select_camp.class);
                startActivity(i);
                finish();
            }
    });
}
}

package com.example.hussain.bloodconnectserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class select_camp extends AppCompatActivity {
    ListView listView;
String pass;
    boolean  checked=false;
public  List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_camp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listview_with_fab);
        list = new ArrayList<String>();

        list.add(" ");
        list.add("Camp 1");
        list.add("Camp 2");
        list.add("Camp 3");
        list.add("Camp 4");
        list.add("Camp 5");
        list.add("Camp 6");
        list.add("Camp 7");
        list.add("Camp 8");



                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, list);
                listView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(select_camp.this,add_camp.class);
                startActivity(intent);
                finish();




            }
        });
    }







}

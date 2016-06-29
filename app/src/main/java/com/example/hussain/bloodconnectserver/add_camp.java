package com.example.hussain.bloodconnectserver;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

public class add_camp extends AppCompatActivity {
    Button b;
    String name;
    EditText ed1;
    private String URL_NEW_PREDICTION = "http://blloodconnect.net16.net/camp_phpcode.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_camp);
        b=(Button)findViewById(R.id.submit);
        ed1=(EditText)findViewById(R.id.editText2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=ed1.getText().toString();
                if(ed1.length()!=0)
                new AddNewPrediction().execute(name);
                Toast.makeText(getApplicationContext(),"Camp Created!",Toast.LENGTH_SHORT).show();
               Intent i=new Intent(add_camp.this,select_camp.class);
               // startActivity(i);
                i.putExtra("args", name);
                setResult(Activity.RESULT_OK, i);
                finish();

            }
    });
                //Write your code if there's no result
            }
    private class AddNewPrediction extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... arg) {
            // TODO Auto-generated method stub
            String name = arg[0];



            // Preparing post params
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name));

            Sender serviceClient = new Sender();

            String json = serviceClient.makeServiceCall(URL_NEW_PREDICTION,
                    Sender.POST, params);

            Log.d("Create Prediction Request: ", "> " + json);

            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    boolean error = jsonObj.getBoolean("error");
                    // checking for error node in json
                    if (!error) {
                        // new category created successfully
                        Log.e("Prediction added successfully ",
                                "> " + jsonObj.getString("message"));
                    } else {
                        Log.e("Add Prediction Error: ",
                                "> " + jsonObj.getString("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e("JSON Data", "JSON data error!");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }






}


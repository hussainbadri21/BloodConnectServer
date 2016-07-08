package com.example.hussain.bloodconnectserver;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class refreshment extends AppCompatActivity {
    TextView tx;
    private String URL_NEW_PREDICTION = "http://blloodconnect.net16.net/accepted.php";
    String pass;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refreshment);
        pass = getIntent().getExtras().getString("arg");
        tx=(TextView)findViewById(R.id.textView);
        tx.setText(pass);
        b=(Button) findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(pass.length()!=0)
                new AddNewPrediction().execute(pass);
            Intent i = new Intent(refreshment.this, show_donor.class);
            startActivity(i);
            finish();
        }
    });
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

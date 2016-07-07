package com.example.hussain.bloodconnectserver;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class show_donor extends AppCompatActivity {
    ListView listView;

    public List<String> list;
    String myJSON;

    private static final String TAG_RESULTS="result";

    private static final String TAG_NAME = "name";

    private static final String TAG_STATUS= "status";

    public String status;


    JSONArray peoples = null;

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_donor);
        listView = (ListView) findViewById(R.id.listview_with_fab);
        list = new ArrayList<String>();
        getData();


        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                /*Toast.makeText(getApplicationContext(),
                        parent.getItemAtPosition(position) + "  is selected.", Toast.LENGTH_LONG)
                        .show();*/
                String s1=parent.getItemAtPosition(position).toString();
                int y=s1.indexOf(':');
                String s2=s1.substring(++y).trim();
              //  Toast.makeText(getApplicationContext(),s2,Toast.LENGTH_SHORT).show();
                if(s2.equalsIgnoreCase("Health Checkup")) {
                    //Toast.makeText(getApplicationContext(),s2,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(show_donor.this, HealthCheckup.class);
                    String x =s1.substring(0,--y);
                    intent.putExtra("arg", x);
                    startActivity(intent);
                }
                else
                if(s2.equalsIgnoreCase("Donation")) {
                    Intent intent = new Intent(show_donor.this, donation.class);
                    String x =s1.substring(0,y);
                    intent.putExtra("arg", x);
                    startActivity(intent);
                }
                else
                if(s2.equalsIgnoreCase("Refreshment")) {
                    Intent intent = new Intent(show_donor.this, refreshment.class);
                    String x =s1.substring(0,y);
                    intent.putExtra("arg", x);
                    startActivity(intent);
                }

            }

        });

    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://blloodconnect.net16.net/t2.php");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }  protected void showList(){
    try {
        JSONObject jsonObj = new JSONObject(myJSON);
        peoples = jsonObj.getJSONArray(TAG_RESULTS);

        for(int i=0;i<peoples.length();i++){
            JSONObject c = peoples.getJSONObject(i);

            String name = c.getString(TAG_NAME);
             status=c.getString(TAG_STATUS);
           String z=name+" : "+status;

            list.add(z);
            adapter.notifyDataSetChanged();



        }



    } catch (JSONException e) {
        e.printStackTrace();
    }

}
}

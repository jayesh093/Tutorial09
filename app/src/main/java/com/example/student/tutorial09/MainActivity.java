package com.example.student.tutorial09;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
 ListView listView;
 CustomAdpter customAdpter;
    String JsonString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.txtList);
        new MyAsyncTask().execute();
    }
    private class MyAsyncTask extends AsyncTask{
        ProgressDialog progressDialog;

        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.show();
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                InputStream is=getAssets().open("data.json");
                int size=is.available();
                byte [] buffer=new byte[size];
                is.read(buffer);
                is.close();
                JsonString=new String(buffer,"UTF-8");
                Log.i("json",JsonString);
                JSONObject jsonObject=new JSONObject(JsonString);
                JSONArray  jsonArray=jsonObject.getJSONArray("formulas");
                customAdpter=new CustomAdpter(MainActivity.this,jsonArray);
                listView.setAdapter(customAdpter);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();

        }
    }
}

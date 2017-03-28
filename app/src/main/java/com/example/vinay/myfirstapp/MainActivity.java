package com.example.vinay.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.vinay.myfirstapp.R.id.search_bar;

public class MainActivity extends AppCompatActivity {
    private Exception exception;
    TextView responseView;
    ProgressBar pBar;
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    protected void onPreExecute(){
        pBar.setVisibility(View.GONE);
        search.setText("");
    }

    public MainActivity() {


        protected String doInBackground(Void... urls) {
            String email = search.getText().toString();
            // Do some validation here

            try {
                URL url = new URL("localhost/get.php?" + "fnm=" + "t" + "&ser=" + email);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            search.setVisibility(View.GONE);
            Log.i("INFO", response);
            responseView.setText(response);
        }

    }


}

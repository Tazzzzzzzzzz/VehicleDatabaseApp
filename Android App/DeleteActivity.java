package com.example.vehicleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        //Creates the text view box for delete
        final TextView textView = (TextView) findViewById(R.id.DelT);
        //Creates delete button
        Button delBtn = (Button) findViewById(R.id.btnConfirm);
        //Creates the hashmap which is like key and than like value
        final HashMap<String, String> params = new HashMap<>();
        //creates a listener waiting for button to be clicked
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //once clicked it gets the vehicle id
            public void onClick(View v) {
                Gson gson = new Gson();
                int vehicleID = Integer.parseInt(textView.getText().toString());
                //creates the params for the api
                params.put("vehicle_id", String.valueOf(vehicleID));
                String url = null;
                try {
                    //inputs url with the data params
                    url = "http://10.0.2.2:8005/database/api?" + getDelDataString(params);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //Preforms the delete call using the url and the parameter passed
                performDelCall(url, params);


            }
        });
    }
    //The delete call has the hashmap setup e.g the url thean the key than value than the parameter
    private void performDelCall(String url, HashMap<String, String> params) {
        URL boogy;
        //Sets up response
        String response = "";
        try {
            boogy = new URL(url);

            //Create the connection object
            HttpURLConnection conn = (HttpURLConnection) boogy.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            //This time request method is a delete request
            conn.setRequestMethod("DELETE");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //Creates a response code
            int responseCode = conn.getResponseCode();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        //Creates a function to make the string
        private String getDelDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
            //Sets the map entry to be params
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                //Appends the results to make it correct format e.g &=
                result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
            //Returns the correct string

            return result.toString();

    }


}

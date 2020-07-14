package com.example.vehicleapp;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //not using activities so need to strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //Sets up all the textboxes that need user input

        final EditText VehID = (EditText) findViewById(R.id.AddID);
        final EditText Make = (EditText) findViewById(R.id.AddMake);
        final EditText Model = (EditText) findViewById(R.id.AddModel);
        final EditText Year = (EditText) findViewById(R.id.AddYear);
        final EditText Price = (EditText) findViewById(R.id.AddPrice);
        final EditText License = (EditText) findViewById(R.id.AddLicense);
        final EditText Colour = (EditText) findViewById(R.id.AddColour);
        final EditText Doors = (EditText) findViewById(R.id.AddDoors);
        final EditText Transmission = (EditText) findViewById(R.id.AddTransmission);
        final EditText Mileage = (EditText) findViewById(R.id.AddMileage);
        final EditText Fuel = (EditText) findViewById(R.id.AddFuel);
        final EditText Engine = (EditText) findViewById(R.id.AddEngine);
        final EditText Body = (EditText) findViewById(R.id.AddBody);
        final EditText Condition = (EditText) findViewById(R.id.AddCondition);
        final EditText Notes = (EditText) findViewById(R.id.AddNotes);
        //Creates button
        Button AddBtn = (Button) findViewById(R.id.btnAdd);
        //Creates hashamp
        final HashMap<String, String> params = new HashMap<>();
        //Creates listener for add button
        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //Onclick do this
            public void onClick(View v) {
                //Create GSON object
                Gson gson = new Gson();
                //Get all values for the vehicle object that needs to be created. So values gotta be parsed as ints
                int vehicleID = Integer.parseInt(VehID.getText().toString());
                String Makes = Make.getText().toString();
                String Models = Model.getText().toString();
                int Years = Integer.parseInt(Year.getText().toString());
                int Prices = Integer.parseInt(Price.getText().toString());
                String Licenses = License.getText().toString();
                String Colors = Colour.getText().toString();
                int DoorsA = Integer.parseInt(Doors.getText().toString());
                String Transmissions = Transmission.getText().toString();
                int Mileages = Integer.parseInt(Mileage.getText().toString());
                String Fuels = Fuel.getText().toString();
                int Engines = Integer.parseInt(Engine.getText().toString());
                String BodyA = Body.getText().toString();
                String Conditions = Condition.getText().toString();
                String NotesA = Notes.getText().toString();
                //Creates vehicle object with values above that have been passed by user
                Vehicle b = new Vehicle(vehicleID, Makes, Models, Years, Prices, Licenses, Colors, DoorsA, Transmissions, Mileages, Fuels, Engines, BodyA, Conditions, NotesA);
                //Formats it to json
                String json = gson.toJson(b);
                //Puts the params
                params.put("json", json);
                //URL for api
                String url = ://10.0.2.2:8005/database/api"http";
                //Preforms the call
                performPostCall(url, params);

            }
        });

    }

    public String performPostCall(String requestURL, HashMap<String, String> postDataParams) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            //Create the connection object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            //This time post request
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //Write/send/Post/ data to the connection using output stream and bufferd writer

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            System.out.println("ResponseCode = " + responseCode);
            //Displays response code if vehicle is added or not
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Toast.makeText(this, "Vehicle Saved ", Toast.LENGTH_LONG).show();
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                Toast.makeText(this, "Error failed to save Vehicle", Toast.LENGTH_LONG).show();
                response = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Response = " + response);
        return response;
    }
    //Creates the string with the hashmap
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}


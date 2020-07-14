package com.example.vehicleapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //Gets intent
        Bundle extras = getIntent().getExtras();
        //Gets vehicle object
        Vehicle theVehicle = (Vehicle) extras.get("vehicle");
        //Gets make and prints it
        System.err.println("received from the intent: " + theVehicle.getMake());
        //Text view created which is set to the correct ID text box. The value of the text box is set by getting the object e.g "vehicleid" from the vehicle object
        final TextView textView = (TextView) findViewById(R.id.VehicleID);
        textView.setText(String.valueOf(theVehicle.getVehicle_id()));

        final TextView textView1 = (TextView) findViewById(R.id.Make);
        textView1.setText(theVehicle.getMake());

        final TextView textView2 = (TextView) findViewById(R.id.Model);
        textView2.setText(theVehicle.getModel());

        final TextView textView3 = (TextView) findViewById(R.id.Year);
        textView3.setText(String.valueOf(theVehicle.getYear()));

        final TextView textView4 = (TextView) findViewById(R.id.Price);
        textView4.setText(String.valueOf(theVehicle.getPrice()));

        final TextView textView5 = (TextView) findViewById(R.id.License);
        textView5.setText(theVehicle.getLicense_number());

        final TextView textView6 = (TextView) findViewById(R.id.Colours);
        textView6.setText(theVehicle.getColour());

        final TextView textView7 = (TextView) findViewById(R.id.Doors);
        textView7.setText(String.valueOf(theVehicle.getNumber_doors()));

        final TextView textView8 = (TextView) findViewById(R.id.Transmission);
        textView8.setText(theVehicle.getTransmission());

        final TextView textView9 = (TextView) findViewById(R.id.Mileage);
        textView9.setText(String.valueOf(theVehicle.getMileage()));

        final TextView textView10 = (TextView) findViewById(R.id.FuelType);
        textView10.setText(theVehicle.getFuel_type());

        final TextView textView11 = (TextView) findViewById(R.id.EngineSize);
        textView11.setText(String.valueOf(theVehicle.getEngine_size()));

        final TextView textView12 = (TextView) findViewById(R.id.BodyStyle);
        textView12.setText(theVehicle.getBody_style());

        final TextView textView13 = (TextView) findViewById(R.id.Condition);
        textView13.setText(theVehicle.getCondition());

        final TextView textView14 = (TextView) findViewById(R.id.Notes);
        textView14.setText(theVehicle.getNotes());
        //creates the update button.
        Button UpdateBtn = (Button) findViewById(R.id.btnUpdate);
        //Creates the hashmap which is like key and than like value
        final HashMap<String, String> params = new HashMap<>();
        //Creates a listener, that waits for the button to be clicked
        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //Does everything below when the button is clicked
            public void onClick(View v) {
                //Creates a GSON object
                Gson gson = new Gson();
                //Gets the values from above, This is the edit update part
                int vehicleID = Integer.parseInt(textView.getText().toString());
                String Makes = textView1.getText().toString();
                String Models = textView2.getText().toString();
                int Years = Integer.parseInt(textView3.getText().toString());
                int Prices = Integer.parseInt(textView4.getText().toString());
                String Licenses = textView5.getText().toString();
                String Colors = textView6.getText().toString();
                int DoorsA = Integer.parseInt(textView7.getText().toString());
                String Transmissions = textView8.getText().toString();
                int Mileages = Integer.parseInt(textView9.getText().toString());
                String Fuels = textView10.getText().toString();
                int Engines = Integer.parseInt(textView11.getText().toString());
                String BodyA = textView12.getText().toString();
                String Conditions = textView13.getText().toString();
                String NotesA = textView14.getText().toString();
                //Creates the new vehicle object with all the new stuff
                Vehicle b = new Vehicle(vehicleID, Makes, Models, Years, Prices, Licenses, Colors, DoorsA, Transmissions, Mileages, Fuels, Engines, BodyA, Conditions, NotesA);
                //Does it as a json string so like json={}
                params.put("json", gson.toJson(b));
                String url = null;
                try {
                    //the url for the api + the params
                    url = "http://10.0.2.2:8005/database/api?" + getPutDataString(params);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //The put call which is update
                performPutCall(url, params);

            }
        });


    }
    //the putcall function
    private void performPutCall(String url, HashMap<String, String> params) {
        //The url is = to boogy
        URL boogy;
        //sets reponse to null
        String response = "";
        try {
            //sets the url
            boogy = new URL(url);

            //Create the connection object
            HttpURLConnection conn = (HttpURLConnection) boogy.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("PUT");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            int responseCode = conn.getResponseCode();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Creates a function to make the string
    private String getPutDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        //builds the string
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //Sets the map entry to be params
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");
            //Appends the results to make it correct format e.g &=
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        //Returns the correct string
        return result.toString();
    }

}
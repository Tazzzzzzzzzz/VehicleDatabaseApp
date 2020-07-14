package com.example.vehicleapp;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Creates string of vehicles names. Its an array. Also sets buttons
    String[] vehicleNames;
    Button btn;
    Button btnDel;

    @Override
    //On create method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //Creates list view as vehiclelist finds it by view id vehicle list
        ListView VehicleList = (ListView) findViewById(R.id.VehicleList);
        //Creates the add button
        btn = (Button) findViewById(R.id.Add);
        //creates del button
        btnDel = (Button) findViewById(R.id.Delete);
        //listen for del button press
        btnDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //on click takes you to the delete activity class
                Intent intent = new Intent(getApplicationContext(),DeleteActivity.class);
                startActivity(intent);
            }

                });
        //listens for add button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Onclick takes you to the add class
                Intent intent = new Intent(getApplicationContext(),AddActivity.class);
                startActivity(intent);

            }
        });



        //Making a http call
        HttpURLConnection urlConnection;
        InputStream in = null;
        try {
            // the url we wish to connect to
            URL url = new URL("http://10.0.2.2:8005/database/api");
            // open the connection to the specified URL
            urlConnection = (HttpURLConnection) url.openConnection();
            // get the response from the server in an input stream
            in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* covert the input stream to a string */
        String response = convertStreamToString(in);
        // print the response to android monitor/log cat
        System.out.println("Server response = " + response);
        final ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>();


        try {
            //Creates a json array with all vehicle data
            JSONArray jsonArray = new JSONArray(response);

            vehicleNames = new String[jsonArray.length()];
            //Loops througth the json array
            for (int i = 0; i < jsonArray.length(); i++) {
                int vehicle_id = Integer.decode(jsonArray.getJSONObject(i).get("vehicle_id").toString());
                String make = jsonArray.getJSONObject(i).get("make").toString();
                String model = jsonArray.getJSONObject(i).get("model").toString();
                int year = Integer.decode(jsonArray.getJSONObject(i).get("year").toString());
                int price = Integer.decode(jsonArray.getJSONObject(i).get("price").toString());
                String license = jsonArray.getJSONObject(i).get("license_number").toString();
                String colour = jsonArray.getJSONObject(i).get("colour").toString();
                int doors = Integer.decode(jsonArray.getJSONObject(i).get("number_doors").toString());
                String transmission = jsonArray.getJSONObject(i).get("transmission").toString();
                int mileage = Integer.decode(jsonArray.getJSONObject(i).get("mileage").toString());
                String fuel = jsonArray.getJSONObject(i).get("fuel_type").toString();
                int engine = Integer.decode(jsonArray.getJSONObject(i).get("engine_size").toString());
                String body = jsonArray.getJSONObject(i).get("body_style").toString();
                String condition = jsonArray.getJSONObject(i).get("condition").toString();
                String notes = jsonArray.getJSONObject(i).get("notes").toString();

                System.out.println("make = " +make);

                //Creates the vehicle object from json array
                Vehicle c = new Vehicle(vehicle_id,make,model,year,price,license,colour,doors,transmission,mileage,fuel,engine,body,condition,notes);
                allVehicles.add(c);
                //Adds and displays the stuff
                vehicleNames[i]=make+" "+model+" "+" "+license+" "+year;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vehicleNames);
        //set the adapter to the listview
        VehicleList.setAdapter(arrayAdapter);

        VehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Displays message on what ya clicked
                Toast.makeText(MainActivity.this, "you pressed " + allVehicles.get(i).getMake(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);

                intent.putExtra("vehicle", allVehicles.get(i));

                startActivity(intent);
            }

        });

    }



    @Override
    protected void onResume() {

        super.onResume();

    }

    public String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}

package com.a75f.floor.floorapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends Activity {
    private final String TAG = MainActivity.class.getName();
    private RelativeLayout relativeLayout;
    ArrayList<Item> list = new ArrayList<Item>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.layout);
        FetchAsyncTask fetchData = new FetchAsyncTask();
        fetchData.execute();
    }

    private String loadDefaultJsonData() {
        String jsonString = null;
        Log.d(TAG, "loadJsonData called");
        try {
            InputStream is = getAssets().open("floorDataJsonFormat.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
            Log.d(TAG, "json string locally:  " + jsonString);
        } catch (IOException ex) {
            Log.d(TAG, "execption caught" + ex.getMessage());
        }
        return jsonString;
    }

    class FetchAsyncTask extends AsyncTask<String, Void, ArrayList<Item>> {
        @Override
        protected ArrayList<Item> doInBackground(String... params) {
            Log.d(TAG, "doinbackground called");

            try {
                String jsonString = loadDefaultJsonData();
                Log.d("amit", "String is: " + jsonString);
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("first floor");
               int arraylength = jsonArray.length();

                for (int i = 0; i < arraylength; i++) {
                    String name = jsonArray.getJSONObject(i).getString("name");
                    JSONObject obj = jsonArray.getJSONObject(i).getJSONObject("position");
                    float xPos = Float.parseFloat(obj.getString("x"));
                    float yPos = Float.parseFloat(obj.getString("y"));
                    float width = Float.parseFloat(obj.getString("width"));
                    float height = Float.parseFloat(obj.getString("height"));
                    list.add(new Item(name, xPos, yPos, width, height));
                }
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Item> items) {
           Log.d(TAG, "onPostExecute called");
            super.onPostExecute(items);
            relativeLayout.addView(new CustomRectangleView(getApplicationContext(), items));
        }
    }

}







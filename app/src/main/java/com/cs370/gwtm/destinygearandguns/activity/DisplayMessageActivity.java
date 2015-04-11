package com.cs370.gwtm.destinygearandguns.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.cs370.gwtm.destinygearandguns.utility.VolleySingleton;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

class AcctJsonEvent {
    private String iconPath;
    private int membershipType;
    private String membershipId;
    private String displayName;

    private AcctJsonEvent(String iconPath, int membershipType, String membershipId, String displayName) {
        this.iconPath = iconPath;
        this.membershipType = membershipType;
        this.membershipId = membershipId;
        this.displayName = displayName;
    }
    @Override
    public String toString() {
        return String.format(" iconPath=%s \n membershipType=%d \n membershipId=%s \n displayName=%s",
                iconPath, membershipType, membershipId, displayName);
    }
}

public class DisplayMessageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RequestQueue myQueue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        // Get the message from intent
        Intent intent = getIntent();

        // serviceMemberName, Username/Account name
        String serviceMemberName = intent.getStringExtra(MainActivity.ACCOUNT_NAME);

        // membershipType, XBox Live = 1, PSN = 2.
        int membershipType;

        if ( intent.getIntExtra("XBLChecked", 0) == 1 )
            membershipType = intent.getIntExtra("XBLChecked", 0);
        else
            membershipType = intent.getIntExtra("PSNChecked", 0);

        String searchMemberUrl = "https://www.bungie.net/Platform/Destiny/SearchDestinyPlayer/"
                + membershipType + "/" + serviceMemberName + "/";

        // Response

        // Create the text view
        final TextView textView = new TextView(this);
        textView.setTextSize(24);
        //textView.setText(message); // Print String send from MainActivity ie: userName

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, searchMemberUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // response.getJSONArray("Response")
                            String jsonResponseString = response.getJSONArray("Response").toString();
                            JsonParser myParser = new JsonParser();

                            // BEWARE "JSONArray" is org.json & "JsonArray" is com.google.gson
                            JsonArray acctInfo = myParser.parse(jsonResponseString).getAsJsonArray();
                            //JSONArray acctInfo = myParser.parse(jsonResponseString).getAsJsonArray();

                            Gson myGson = new Gson();

                            AcctJsonEvent acctJsonEvent = myGson.fromJson(acctInfo.get(0), AcctJsonEvent.class);

                            textView.setText(acctJsonEvent.toString());
                            /*
                            for(int i = 0; i < acctInfo.length(); i++) {
                                //Log.v("Response", acctInfo.getString(0));
                                //textView.setText( "Response" + acctInfo.getString(0) );
                                textView.setText( acctInfo.getString(0) );
                            }
                            */
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }); // End JSON Object Request

        // Add JSON Object Request to Volley Queue
        myQueue.add(jsonObjectRequest);

        // Set the text view as the activity layout
        setContentView(textView);
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_display_message, menu);
        return true;   dafdf
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}

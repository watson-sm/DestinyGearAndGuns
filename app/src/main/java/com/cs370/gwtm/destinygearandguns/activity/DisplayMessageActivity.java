package com.cs370.gwtm.destinygearandguns.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
//import android.view.Menu;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cs370.gwtm.destinygearandguns.R;
import com.cs370.gwtm.destinygearandguns.utility.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DisplayMessageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_display_message);

        RequestQueue testQueue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        // Get the message from intent
        Intent intent = getIntent();
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String serviceMemberName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //String serviceMemberName = "Kiladre";
        int membershipType = 2;
        String searchMemberUrl = "https://www.bungie.net/Platform/Destiny/SearchDestinyPlayer/"
                + membershipType + "/" + serviceMemberName + "/";


        // Response
        //String jsonResponse = "";

        // Create the text view
        final TextView textView = new TextView(this);
        textView.setTextSize(40);
        //textView.setText(message); // Print String send from MainActivity ie: userName

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, searchMemberUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String jsonResponse = "";
                            JSONArray acctInfo = response.getJSONArray("Response");
                            for(int i = 0; i < acctInfo.length(); i++) {
                                //Log.v("Response", acctInfo.getString(0));
                                textView.setText( "Response" + acctInfo.getString(0) );
                                //jsonResponse += "Response" + acctInfo.getString(0);

                            }
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
        testQueue.add(jsonObjectRequest);

        // Display response
        //textView.setText(jsonResponse);

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
*/
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
}

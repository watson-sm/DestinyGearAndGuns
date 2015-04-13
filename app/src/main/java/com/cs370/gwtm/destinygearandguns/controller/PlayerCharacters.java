package com.cs370.gwtm.destinygearandguns.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cs370.gwtm.destinygearandguns.activity.DisplayCharactersActivity;
import com.cs370.gwtm.destinygearandguns.interfaces.IPlayerCharacterListener;
import com.cs370.gwtm.destinygearandguns.model.DestinyMembership;
import com.cs370.gwtm.destinygearandguns.utility.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kiladre on 4/12/15.
 * Handle process of collection of player's character information
 */
public class PlayerCharacters extends DisplayCharactersActivity {

    private Context ctx;
    private DestinyMembership destinyMembership = new DestinyMembership();
    private IPlayerCharacterListener iPCL;
    private PlayerCharacters pc;

    public PlayerCharacters(IPlayerCharacterListener playerCharacterListener) {
        iPCL = playerCharacterListener;
        ctx = (Context) playerCharacterListener;
    }
    public void getCharacters(int XBLChecked, String serviceMemberName) {
        RequestQueue myQueue = VolleySingleton.getInstance( ctx.getApplicationContext() ).getRequestQueue();

        // Membership Type: Xbox Live = 1, PSN = 2
        int membershipType;

        if ( XBLChecked == 1)
            membershipType = 1;
        else
            membershipType = 2;

        String searchMemberUrl = "https://www.bungie.net/Platform/Destiny/SearchDestinyPlayer/"
                + membershipType + "/" + serviceMemberName + "/";

        JsonObjectRequest jsonDestinyMembership = new JsonObjectRequest(Request.Method.GET, searchMemberUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            response.getJSONArray("Response");
                            String jsonResponseString = response.getJSONArray("Response").toString();
                            String emptyArray = "[]";
                            DestinyMembership destinyMembership = new DestinyMembership();

                            JsonParser myParser = new JsonParser();

                            // BEWARE "JSONArray" is org.json & "JsonArray" is com.google.gson
                            JsonArray jsonArray = myParser.parse(jsonResponseString).getAsJsonArray();

                            Gson myGson = new Gson();

                            // Detect if the array returned empty.
                            if( !jsonResponseString.equals(emptyArray) )
                                destinyMembership = myGson.fromJson(jsonArray.get(0), DestinyMembership.class);

                            setDestinyMembership(destinyMembership);
                            iPCL.playerCharacterCallback(destinyMembership);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        Log.v("Error", error.toString() );
                    }
                }); // End JSON Object Request

        // Add JSON Object Request to Volley Queue
        myQueue.add(jsonDestinyMembership);
    }

    private void setDestinyMembership(DestinyMembership dm) {
        destinyMembership = dm;
        //Log.v("set destinyMembership: ", destinyMembership.toString() );
    }

    public DestinyMembership getDestinyMembership() {
        //Log.v("get destinyMembership", destinyMembership.toString() );
        return destinyMembership;
    }
}

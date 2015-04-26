package com.cs370.gwtm.destinygearandguns.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cs370.gwtm.destinygearandguns.activity.DisplayInventoryActivity;
import com.cs370.gwtm.destinygearandguns.interfaces.ICharacterInventoryListener;
import com.cs370.gwtm.destinygearandguns.model.Equippable;
import com.cs370.gwtm.destinygearandguns.utility.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kiladre on 4/25/15.
 */
public class CharacterInventory extends DisplayInventoryActivity {

    private Context ctx;
    private ICharacterInventoryListener iCIL;

    public CharacterInventory(ICharacterInventoryListener iCharacterInventoryListener) {
        iCIL = iCharacterInventoryListener;
        ctx = (Context) iCharacterInventoryListener;
    }

    public void getInventory() {
        RequestQueue myQueue = VolleySingleton.getInstance(ctx.getApplicationContext()).getRequestQueue();

        /*
         * Destiny Character Inventory
         * https://www.bungie.net/platform/destiny
         * /{membershipType}/Account/{destinyMembershipId}/Character/{characterId}/Inventory/
         */
        String inventoryURL = "https://www.bungie.net/platform/destiny/"
                            + "2/Account/4611686018428756196/Character/2305843009215199142/Inventory/";

        JsonObjectRequest jsonCharacterInventory = new JsonObjectRequest(Request.Method.GET, inventoryURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String jsonResponseString = response.getJSONObject("Response")
                                                                .getJSONObject("data")
                                                                .getJSONObject("buckets")
                                                                .getJSONArray("Equippable")
                                                                .toString();

                            JsonParser myParser = new JsonParser();

                            // BEWARE "JSONArray" is org.json & "JsonArray" is com.google.gson
                            JsonArray jsonArray = myParser.parse(jsonResponseString).getAsJsonArray();

                            //Log.v("jsonResponseString", jsonResponseString);
                            Gson myGson = new Gson();

                            // TODO currently the following is only getting 1st element of array
                            // instead of all the elements.
                            Equippable equippable = myGson.fromJson( jsonArray.get(0), Equippable.class);

                            iCIL.playerCharacterInventoryCallback(equippable);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(ctx.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        Log.v("Error", error.toString());
                    }
                }); // End JSON Object Request

        // Add JSON Object Request to Volley Queue
        myQueue.add(jsonCharacterInventory);
    }
}

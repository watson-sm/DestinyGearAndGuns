package com.cs370.gwtm.destinygearandguns.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cs370.gwtm.destinygearandguns.activity.DisplayCharactersActivity;
import com.cs370.gwtm.destinygearandguns.interfaces.IPlayerCharacterListener;
import com.cs370.gwtm.destinygearandguns.model.DestinyCharacterInfo;
import com.cs370.gwtm.destinygearandguns.model.DestinyCharacters;
import com.cs370.gwtm.destinygearandguns.model.DestinyMembership;
import com.cs370.gwtm.destinygearandguns.utility.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kiladre on 4/12/15.
 * Handle process of collection of player's character information
 */
public class PlayerCharacters extends DisplayCharactersActivity {

    private Context ctx;
    private IPlayerCharacterListener iPCL;

    public PlayerCharacters(IPlayerCharacterListener playerCharacterListener) {
        iPCL = playerCharacterListener;
        ctx = (Context) playerCharacterListener;
    }
    public void pullMembership(int serviceChecked, String serviceMemberName) {
        RequestQueue myQueue = VolleySingleton.getInstance( ctx.getApplicationContext() ).getRequestQueue();

        // Membership Type: Xbox Live = 1, PSN = 2
        int membershipType;

        if ( serviceChecked == 1)
            membershipType = 1;
        else
            membershipType = 2;

        String searchMemberUrl = "https://www.bungie.net/Platform/Destiny/SearchDestinyPlayer/"
                + membershipType + "/" + serviceMemberName + "/";

        /*
         * Destiny Membership info request
         */
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

                            iPCL.playerMembershipCallback(destinyMembership);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(ctx.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        Log.v("Error", error.toString() );
                    }
                }); // End JSON Object Request

        // Add JSON Object Request to Volley Queue
        myQueue.add(jsonDestinyMembership);
    }

    public void pullCharacters(final int membershipType, final String serviceMemberId) {
        RequestQueue myQueue = VolleySingleton.getInstance( ctx.getApplicationContext() ).getRequestQueue();

        // Membership Type: Xbox Live = 1, PSN = 2

        // Search characters url
        String searchCharactersUrl = "https://www.bungie.net/Platform/Destiny/Stats/Account/"
                + membershipType + "/" + serviceMemberId + "/";

    /*
     * Destiny Character request
     */
        JsonObjectRequest jsonDestinyCharacters = new JsonObjectRequest(Request.Method.GET, searchCharactersUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String jsonResponseString = response.getJSONObject("Response").getJSONArray("characters").toString();

                            String emptyArray = "[]";
                            DestinyCharacters[] destinyCharacters = new DestinyCharacters[3];
                            destinyCharacters[0] = new DestinyCharacters("");
                            destinyCharacters[1] = new DestinyCharacters("");
                            destinyCharacters[2] = new DestinyCharacters("");

                            JsonParser myParser = new JsonParser();

                            // BEWARE "JSONArray" is org.json & "JsonArray" is com.google.gson
                            JsonArray jsonArray = myParser.parse(jsonResponseString).getAsJsonArray();

                            Gson myGson = new Gson();

                            // Detect if the array returned empty.
                            if( !jsonResponseString.equals(emptyArray) ) {
                                for( int i = 0; i < jsonArray.size(); i++) {
                                    destinyCharacters[i] = myGson.fromJson(jsonArray.get(i), DestinyCharacters.class);
                                    destinyCharacters[i].setMembershipId(serviceMemberId);
                                    destinyCharacters[i].setMembershipType(membershipType);
                                }
                            }

                            iPCL.playerCharacterCallback(destinyCharacters);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(ctx.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        Log.v("Error", error.toString() );
                    }
                }); // End JSON Object Request

        // Add JSON Object Request to Volley Queue
        myQueue.add(jsonDestinyCharacters);
    }

    public void pullCharacterInfo(int membershipType, String serviceMemberId, String characterId) {
        RequestQueue myQueue = VolleySingleton.getInstance( ctx.getApplicationContext() ).getRequestQueue();

        // Membership Type: Xbox Live = 1, PSN = 2

        // Search characters url
        // https://www.bungie.net/Platform/Destiny/2/Account/4611686018428756196/Character/2305843009215199142/
        String searchCharactersUrl = "https://www.bungie.net/Platform/Destiny/"
                + membershipType + "/Account/" + serviceMemberId + "/Character/" + characterId + "/";

    /*
     * Destiny Character info request
     */
        JsonObjectRequest jsonDestinyCharacterInfo = new JsonObjectRequest(Request.Method.GET, searchCharactersUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Gson myGson = new Gson();
                            DestinyCharacterInfo dcInfo;

                            // This is pulling the character base information
                            String jsonCharacterBase = response.getJSONObject("Response")
                                                                .getJSONObject("data")
                                                                .getJSONObject("characterBase")
                                                                .toString();

                            // This is pulling the leveling information
                            // from destiny character summary
                            // ex file: destiny_character_summary_hr.txt
                            String jsonCharacterLevelInfo = response.getJSONObject("Response")
                                    .getJSONObject("data")
                                    .getJSONObject("levelProgression")
                                    .toString();

                            // Essentially pulling everything
                            String jsonCharacterData = response.getJSONObject("Response")
                                    .getJSONObject("data")
                                    .toString();

                            //String emptyArray = "[]";

                            //String jsonConCat = jsonCharacterBase + jsonCharacterLevelInfo +jsonCharacterData;

                            JsonParser myParser = new JsonParser();

                            Log.v("jCB -> ", jsonCharacterBase);
                            Log.v("jCLI -> ", jsonCharacterLevelInfo);
                            Log.v("jCD -> ", jsonCharacterData);



                            JsonElement tmp = myParser.parse(jsonCharacterData);

                            // BEWARE "JSONArray" is org.json & "JsonArray" is com.google.gson
                            //JsonElement jsonCB = myParser.parse(jsonCharacterBase);
                            //JsonElement jsonCLI = myParser.parse(jsonCharacterLevelInfo);
                            //JsonElement jsonCD = myParser.parse(jsonCharacterData);
                            //JsonElement jsonElement = myParser.parse(jsonConCat);

                            //Log.v("jsonCB: ", jsonCB.toString() );
                            //Log.v("jsonCLI: ", jsonCLI.toString() );
                            //Log.v("jsonCD: ", jsonCD.toString() );
                            //Log.v("tmp-> ", tmp.toString() );

                            //dcInfo = myGson.fromJson(jsonCB, DestinyCharacterInfo.class);
                            //dcInfo = myGson.fromJson(jsonCLI, DestinyCharacterInfo.class);
                            //dcInfo = myGson.fromJson(jsonCharacterData, DestinyCharacterInfo.class);
                            //dcInfo = myGson.fromJson(jsonConCat, DestinyCharacterInfo.class);
                            dcInfo = myGson.fromJson(tmp, DestinyCharacterInfo.class);

                            iPCL.playerCharacterInfoCallback(dcInfo);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(ctx.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        Log.v("Error", error.toString() );
                    }
                }); // End JSON Object Request

        // Add JSON Object Request to Volley Queue
        myQueue.add(jsonDestinyCharacterInfo);
    }
}

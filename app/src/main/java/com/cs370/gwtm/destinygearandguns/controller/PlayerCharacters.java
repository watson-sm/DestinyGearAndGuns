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
import com.cs370.gwtm.destinygearandguns.model.CharacterClass;
import com.cs370.gwtm.destinygearandguns.model.DestinyCharacterInfo;
import com.cs370.gwtm.destinygearandguns.model.DestinyCharacters;
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
    private IPlayerCharacterListener iPCL;
    //final String BUNGIE_URL = "https://www.bungie.net";

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
                            DestinyCharacterInfo dcInfo = new DestinyCharacterInfo();
                            // Essentially pulling everything
                            String jsonCharacterData = response.getJSONObject("Response")
                                    .getJSONObject("data")
                                    .toString();


                            String membershipId_tag = "\"membershipId\":";
                            String membershipId_val = parseCharacterInfoString(membershipId_tag, jsonCharacterData);
                            dcInfo.setMembershipId(membershipId_val);

                            String membershipType_tag = "\"membershipType\":";
                            String membershipType_val = parseCharacterInfoNum(membershipType_tag, jsonCharacterData);
                            dcInfo.setMembershipType( Integer.parseInt(membershipType_val) );

                            String characterId_tag = "\"characterId\":";
                            String characterId_val = parseCharacterInfoString(characterId_tag, jsonCharacterData);
                            dcInfo.setCharacterId(characterId_val);

                            String raceHash_tag = "\"raceHash\":";
                            String raceHash_val = parseCharacterInfoNum(raceHash_tag, jsonCharacterData);
                            dcInfo.setRaceHash( Long.parseLong( raceHash_val.trim() ) );

                            String genderHash_tag = "\"genderHash\":";
                            String genderHash_val = parseCharacterInfoNum(genderHash_tag, jsonCharacterData);
                            dcInfo.setGenderHash( Long.parseLong(genderHash_val) );

                            String classHash_tag = "\"classHash\":";
                            String classHash_val = parseCharacterInfoNum(classHash_tag, jsonCharacterData);
                            dcInfo.setClassHash( Long.parseLong(classHash_val) );

                            String genderType_tag = "\"genderType\":";
                            String genderType_val = parseCharacterInfoNum(genderType_tag, jsonCharacterData);
                            dcInfo.setGenderType( Integer.parseInt(genderType_val) );

                            String classType_tag = "\"classType\":";
                            String classType_val = parseCharacterInfoNum(classType_tag, jsonCharacterData);
                            dcInfo.setClassType( Integer.parseInt(classType_val) );

                            String emblemPath_tag = "\"emblemPath\":";
                            String emblemPath_val = parseCharacterInfoString(emblemPath_tag, jsonCharacterData).replace("\\", "");
                            dcInfo.setEmblemPath( emblemPath_val );

                            String backgroundPath_tag = "\"backgroundPath\":";
                            String backgroundPath_val = parseCharacterInfoString(backgroundPath_tag, jsonCharacterData).replace("\\", "");
                            dcInfo.setBackgroundPath( backgroundPath_val );

                            String emblemHash_tag = "\"emblemHash\":";
                            String emblemHash_val = parseCharacterInfoNum(emblemHash_tag, jsonCharacterData);
                            dcInfo.setEmblemHash( Long.parseLong(emblemHash_val) );

                            String characterLevel_tag = "\"characterLevel\":";
                            String characterLevel_val = parseCharacterInfoNum(characterLevel_tag, jsonCharacterData);
                            dcInfo.setCharacterLevel( Integer.parseInt(characterLevel_val) );

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

    public void pullCharacterClass(long classHash) {
        RequestQueue myQueue = VolleySingleton.getInstance( ctx.getApplicationContext() ).getRequestQueue();
        String hash = Long.toString(classHash);

        String searchClassURL = "https://www.bungie.net/Platform/Destiny/Manifest/class/" + hash + "/";

        /*
         * Destiny Character Class info request
         */
        final JsonObjectRequest jsonCharacterClass = new JsonObjectRequest(Request.Method.GET, searchClassURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            CharacterClass characterClass = new CharacterClass();
                            //Log.v("We made: ", "it");

                            // This is pulling the character class information
                            String jsonCharacterClass = response.getJSONObject("Response").getJSONObject("data").toString();

                            String class_tag = "\"className\":";
                            String class_val = parseCharacterInfoString(class_tag, jsonCharacterClass);
                            characterClass.setCharacterClass(class_val);

                            iPCL.playerCharacterClassCallback(characterClass);
                            //Log.v("Class: ", class_val);


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
        myQueue.add(jsonCharacterClass);
    }

    String parseCharacterInfoNum(String attribute, String jsonCharacterData) {
        int tmpFirstIdx = jsonCharacterData.indexOf(attribute);

        // Parse the attribute value
        return jsonCharacterData.substring(
                tmpFirstIdx + attribute.length(),
                jsonCharacterData.indexOf(",", tmpFirstIdx + attribute.length() + 1)
        );
    }

    String parseCharacterInfoString(String attribute, String jsonCharacterData) {
        int tmpFirstIdx = jsonCharacterData.indexOf(attribute);

        // Parse the attribute value
        return jsonCharacterData.substring(
                tmpFirstIdx + attribute.length() + 1,
                jsonCharacterData.indexOf(",", tmpFirstIdx + attribute.length() ) - 1
        );
    }
}

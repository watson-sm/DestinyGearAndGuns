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
import com.cs370.gwtm.destinygearandguns.model.DestinyInventoryItem;
import com.cs370.gwtm.destinygearandguns.model.Equippable;
import com.cs370.gwtm.destinygearandguns.utility.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Kiladre on 4/25/15.
 * Character Inventory Controller
 * Pull character's inventory for activity to display
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

        // TODO update inventoryURL so it's not hard coded.
        // Change values depending on which character the user selected.
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

                            Gson myGson = new Gson();

                            Type equippableType = new TypeToken<List<Equippable>>() {}.getType();

                            List<Equippable> equippable = myGson.fromJson( jsonArray, equippableType);

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


    public void pullItemInfo(String itemHash) {
        RequestQueue myQueue = VolleySingleton.getInstance( ctx.getApplicationContext() ).getRequestQueue();

        String searchClassURL = "https://www.bungie.net/Platform/Destiny/Manifest/inventoryItem/" + itemHash + "/";

        /*
         * Destiny Character Class info request
         */
        final JsonObjectRequest jsonCharacterClass = new JsonObjectRequest(Request.Method.GET, searchClassURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            DestinyInventoryItem inventoryItem = new DestinyInventoryItem();
                            //Log.v("We made: ", "it");

                            // This is pulling the character class information
                            String jsonCharacterClass = response.getJSONObject("Response").getJSONObject("data").toString();

                            String item_tag = "\"itemName\":";
                            String item_name = parseCharacterInfoString(item_tag, jsonCharacterClass);
                            inventoryItem.setItemName(item_name);
                            Log.v("Item Name Pull: ", item_name);

                            String description_tag = "\"itemDescription\":";
                            String item_description = parseCharacterInfoString(description_tag, jsonCharacterClass);
                            inventoryItem.setItemDescription(item_description);
                            Log.v("Item Description Pull: ", item_description);

                            String type_tag = "\"itemTypeName\":";
                            String item_type = parseCharacterInfoString(type_tag, jsonCharacterClass);
                            inventoryItem.setItemTypeName(item_type);
                            Log.v("Item Type Pull: ", item_type);

                            String tier_tag = "\"tierTypeName\":";
                            String tier_type = parseCharacterInfoString(tier_tag, jsonCharacterClass);
                            inventoryItem.setTierTypeName(tier_type);
                            Log.v("Tier Type Pull: ", tier_type);

                            String icon_tag = "\"icon\":";
                            String icon_val = parseCharacterInfoString(icon_tag, jsonCharacterClass.replace("\\", ""));
                            inventoryItem.setIconPath(icon_val);
                            Log.v("Icon Path Pull: ", icon_val);

                            iCIL.playerItemCallback(inventoryItem);

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


    String parseCharacterInfoString(String attribute, String jsonCharacterData) {
        int tmpFirstIdx = jsonCharacterData.indexOf(attribute);

        // Parse the attribute value
        return jsonCharacterData.substring(
                tmpFirstIdx + attribute.length() + 1,
                jsonCharacterData.indexOf(",", tmpFirstIdx + attribute.length() ) - 1
        );
    }
}

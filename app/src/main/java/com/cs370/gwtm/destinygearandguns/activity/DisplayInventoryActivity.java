package com.cs370.gwtm.destinygearandguns.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.android.volley.toolbox.ImageLoader;
import com.cs370.gwtm.destinygearandguns.R;
import com.cs370.gwtm.destinygearandguns.controller.CharacterInventory;
import com.cs370.gwtm.destinygearandguns.interfaces.ICharacterInventoryListener;
import com.cs370.gwtm.destinygearandguns.model.DestinyInventoryItem;
import com.cs370.gwtm.destinygearandguns.model.Equippable;

import android.util.Log;
import android.widget.ListView;
import com.cs370.gwtm.destinygearandguns.interfaces.InventoryArrayAdapter;
import com.cs370.gwtm.destinygearandguns.model.DestinyInventory;
import com.cs370.gwtm.destinygearandguns.utility.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

public class DisplayInventoryActivity extends ActionBarActivity implements ICharacterInventoryListener {

    private CharacterInventory CI;
    public ArrayList<DestinyInventoryItem> inventoryItems = new ArrayList();
    int equippableSize = 0;
    int count = 0;

    @Override
    public void playerCharacterInventoryCallback(List<Equippable> equippable) {
        equippableSize = equippable.size();
        // Inventory

        // TODO NOTE THIS IS A LITTLE BACKWARDS DUE TO JSON
        // So...each "items" array only holds 1 array element
        /*
         * Heirarchy
         *  - Equippable[0 : size]
         *  - - items [0 only]
         *  - - - nodes[0 : size]
         *
         *  etc.
         *  So think of it like equippable[0] is the first "item"
         *  equippable[1] is the 2nd "item" etc.
         *  though equippable[0] if I'm correct is actually the selected class's chosen subclass.
         *
         *  review destiny_character_inventory_hr.json for more information on the layout.
         */
        // Bucket has seems to have been populated by the gson
        //Log.v("Bucket Hash: ", String.valueOf( equippable.get(0).getBucketHash() ) );

        // Trying to figure out how to check the nested arrays
        // This currently only works with a value of 0, anything > 0 gives a throws
        // an array out of bounds exception
        //Log.v("Item Hash: ", String.valueOf(equippable.get(0).items.get(0).getItemHash()));
        //Log.v("Size of Item List: ", String.valueOf( equippable.get(0).items.size() ) );
        //Log.v("Size of Equippable: ", String.valueOf( equippable.size() ) );

        // Nodes list seems to be populating fine.
        //Log.v("Node Hash: ", String.valueOf(equippable.get(0).items.get(0).nodes.get(2).getNodeHash()));
        for (int i = 0; i < equippable.size(); i++) {
            //Log.v("i: ", Integer.toString(i));
            CI.pullItemInfo(String.valueOf(equippable.get(i).items.get(0).getItemHash()));
            //Log.v("Item Hash: ", String.valueOf(equippable.get(i).items.get(0).getItemHash()));

        }

        /*// Create the adapter to convert the array to views
        InventoryArrayAdapter adapter = new InventoryArrayAdapter(this, itemList);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.inventoryList);
        listView.setAdapter(adapter);*/
    }


    @Override
    public void playerItemCallback(DestinyInventoryItem inventoryItem) {
        /*Log.v("Name: ", inventoryItem.getItemName());
        Log.v("Description: ", inventoryItem.getItemDescription());
        Log.v("Type: ", inventoryItem.getItemTypeName());
        Log.v("Tier", inventoryItem.getTierTypeName());*/

        ImageLoader imageLoader;

        imageLoader = VolleySingleton.getInstance(this).getImageLoader();

        if( imageLoader == null ) {
            Log.v("imageLoader: ", "Not getting assigned");
        }

        //Log.v("Item Name Callback: ", tempItemName);
        inventoryItems.add(new DestinyInventoryItem(inventoryItem.getItemName(), inventoryItem.getItemDescription(),
                inventoryItem.getItemTypeName(), inventoryItem.getTierTypeName(), inventoryItem.getIconPath(), imageLoader));
        //Log.v("Item Name Callback: ", tempItemName);
        count = count + 1;
        if(count == equippableSize) {
            createView();
        }
//        Log.v("Item Name: ", tempItemName);
        //itemList.add(new DestinyInventoryItem(inventoryItem.getItemName()));
        // Create the adapter to convert the array to views
        //InventoryArrayAdapter adapter = new InventoryArrayAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        //ListView listView = (ListView) findViewById(R.id.inventoryList);
        //listView.setAdapter(adapter);

    }


    public void createView() {
        // Create the adapter to convert the array to views
        InventoryArrayAdapter adapter = new InventoryArrayAdapter(this, inventoryItems);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.inventoryList);
        listView.setAdapter(adapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_inventory);

        CI = new CharacterInventory(this);

        Intent intent = getIntent();

        CI.getInventory();

    }

/*    private void populateUsersList() {


        CI = new CharacterInventory(this);

        Intent intent = getIntent();

        CI.getInventory();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_inventory, menu);
        return true;
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

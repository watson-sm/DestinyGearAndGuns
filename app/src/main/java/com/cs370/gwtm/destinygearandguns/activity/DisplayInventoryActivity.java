package com.cs370.gwtm.destinygearandguns.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.cs370.gwtm.destinygearandguns.R;
import com.cs370.gwtm.destinygearandguns.controller.CharacterInventory;
import com.cs370.gwtm.destinygearandguns.interfaces.ICharacterInventoryListener;
import com.cs370.gwtm.destinygearandguns.model.Equippable;

import android.util.Log;
import android.widget.ListView;
import com.cs370.gwtm.destinygearandguns.interfaces.InventoryArrayAdapter;
import com.cs370.gwtm.destinygearandguns.model.DestinyInventory;

import java.util.ArrayList;
import java.util.List;

public class DisplayInventoryActivity extends ActionBarActivity implements ICharacterInventoryListener {

    private CharacterInventory CI;

    /*
    @Override
    public void playerCharacterInventoryCallback(ArrayList<Equippable> equippable) {
        // Inventory
    }
    */
    @Override
    public void playerCharacterInventoryCallback(List<Equippable> equippable) {
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
        Log.v("Bucket Hash: ", String.valueOf( equippable.get(0).getBucketHash() ) );

        // Trying to figure out how to check the nested arrays
        // This currently only works with a value of 0, anything > 0 gives a throws
        // an array out of bounds exception
        Log.v("Item Hash: ", String.valueOf(equippable.get(0).items.get(0).getItemHash()));
        Log.v("Size of Item List: ", String.valueOf( equippable.get(0).items.size() ) );
        Log.v("Size of Equippable: ", String.valueOf( equippable.size() ) );

        // Nodes list seems to be populating fine.
        Log.v("Node Hash: ", String.valueOf(equippable.get(0).items.get(0).nodes.get(2).getNodeHash()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_inventory);
        populateUsersList();
    }

    private void populateUsersList() {
        // Construct the data source
        ArrayList<DestinyInventory> arrayOfUsers = DestinyInventory.getInventory();
        // Create the adapter to convert the array to views
        InventoryArrayAdapter adapter = new InventoryArrayAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.inventoryList);
        listView.setAdapter(adapter);


        CI = new CharacterInventory(this);

        Intent intent = getIntent();

        CI.getInventory();
    }

/*
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

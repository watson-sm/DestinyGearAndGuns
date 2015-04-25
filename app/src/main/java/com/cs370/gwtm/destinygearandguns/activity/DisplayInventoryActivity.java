package com.cs370.gwtm.destinygearandguns.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.cs370.gwtm.destinygearandguns.R;
import com.cs370.gwtm.destinygearandguns.controller.CharacterInventory;
import com.cs370.gwtm.destinygearandguns.interfaces.ICharacterInventoryListener;
import com.cs370.gwtm.destinygearandguns.model.Equippable;
import android.widget.ListView;
import com.cs370.gwtm.destinygearandguns.interfaces.InventoryArrayAdapter;
import com.cs370.gwtm.destinygearandguns.model.DestinyInventory;

import java.util.ArrayList;

public class DisplayInventoryActivity extends ActionBarActivity implements ICharacterInventoryListener {

    private CharacterInventory CI;

    /*
    @Override
    public void playerCharacterInventoryCallback(ArrayList<Equippable> equippable) {
        // Inventory
    }
    */
    @Override
    public void playerCharacterInventoryCallback(Equippable equippable) {
        // Inventory
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

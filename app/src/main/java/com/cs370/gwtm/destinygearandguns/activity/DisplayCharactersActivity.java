package com.cs370.gwtm.destinygearandguns.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.cs370.gwtm.destinygearandguns.R;
import com.cs370.gwtm.destinygearandguns.controller.PlayerCharacters;
import com.cs370.gwtm.destinygearandguns.interfaces.CharacterArrayAdapter;
import com.cs370.gwtm.destinygearandguns.interfaces.IPlayerCharacterListener;
import com.cs370.gwtm.destinygearandguns.model.CharacterClass;
import com.cs370.gwtm.destinygearandguns.model.DestinyCharacterInfo;
import com.cs370.gwtm.destinygearandguns.model.DestinyCharacters;
import com.cs370.gwtm.destinygearandguns.model.DestinyMembership;
import com.cs370.gwtm.destinygearandguns.utility.VolleySingleton;

import java.util.ArrayList;
import java.util.Arrays;

public class DisplayCharactersActivity extends ActionBarActivity implements IPlayerCharacterListener {

    private TextView textView;
    private PlayerCharacters pc;
    final static private String BUNGIE_URL = "https://www.bungie.net";
    public ArrayList<DestinyCharacterInfo> characterInfo = new ArrayList<DestinyCharacterInfo>();
    String Class;

    @Override
    public void playerMembershipCallback(DestinyMembership dm) {
        if ( !dm.getMembershipId().isEmpty() )
            pc.pullCharacters(dm.getMembershipType(), dm.getMembershipId());
        else
            textView.setText("MembershipID is blank");
        Log.v("Here: ", characterInfo.toString());
    }

    @Override
    public void playerCharacterCallback(DestinyCharacters[] dc) {

        // Player's can have up to 3 characters.
        for(int i = 0; i < 3; i++) {
            pc.pullCharacterInfo(dc[i].getMembershipType(), dc[i].getMembershipId(), dc[i].getCharacterId());
        }

    }

    @Override
    public void playerCharacterInfoCallback(DestinyCharacterInfo dcInfo) {
        // TODO figure out how to use emblem and background path to load images from url
        // and put in character level.

        pc.pullCharacterClass(dcInfo.getClassHash());

        //Log.v("Class Hash", Long.toString(dcInfo.getClassHash()));

        ImageLoader imageLoader;
        imageLoader = VolleySingleton.getInstance(this).getImageLoader();
        characterInfo.add(new DestinyCharacterInfo(dcInfo.getCharacterId(), dcInfo.getRaceHash(), dcInfo.getEmblemPath(), dcInfo.getBackgroundPath(), imageLoader));

        CharacterArrayAdapter adapter = new CharacterArrayAdapter(this, characterInfo);
        ListView listView = (ListView) findViewById(R.id.characterList);
        listView.setAdapter(adapter);

        // Model is loaded...here are example logs see about loading this information into
        // the view
        //ImageLoader imageLoader;
        //NetworkImageView networkImageView;
        // Get the NetworkImageView that will display the image.
        //networkImageView = (NetworkImageView) findViewById(R.id.networkImageView);

        // Get the ImageLoader through your singleton class.

        // Set the URL of the image that should be loaded into this view, and
        // specify the ImageLoader that will be used to make the request.
        //networkImageView.setImageUrl(BUNGIE_URL + dcInfo.getBackgroundPath().replace("\\", ""), imageLoader);
        //Log.v("character Id: ", dcInfo.getCharacterId() );
        //Log.v("emblem path: ", dcInfo.getEmblemPath() );
        //Log.v("background path: ", dcInfo.getBackgroundPath() );
        //Log.v("character level: ", String.valueOf( dcInfo.getCharacterLevel() ) );
        //Log.v("race hash: ", String.valueOf(dcInfo.getRaceHash()));
    }

    public void playerCharacterClassCallback(CharacterClass classType) {
        Log.v("Character Class: ", classType.getCharacterClass());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        //textView = new TextView(this);

        //PlayerCharacters pc = new PlayerCharacters(this);
        pc = new PlayerCharacters(this);

        // Get the message from intent
        Intent intent = getIntent();

        // serviceMemberName, Username/Account name
        String serviceMemberName = intent.getStringExtra(MainActivity.ACCOUNT_NAME);

        // service checked either Xbox Live or PSN
        int serviceChecked = intent.getIntExtra("XBLChecked", 0);

        pc.pullMembership(serviceChecked, serviceMemberName);

        //populateUsersList();
    }

    /*private void populateUsersList() {
    // Construct the data source
    //    ArrayList<User> arrayOfUsers = User.getUsers();
    // Create the adapter to convert the array to views
        CharacterArrayAdapter adapter = new CharacterArrayAdapter(this, characterInfo);
    // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.characterList);
        listView.setAdapter(adapter);
    }*/

    public void getInventory(View view) {
        Intent inventoryIntent = new Intent(this, DisplayInventoryActivity.class);

        startActivity(inventoryIntent);
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_display_message, menu);
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
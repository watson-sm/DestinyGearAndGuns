package com.cs370.gwtm.destinygearandguns.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

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

public class DisplayCharactersActivity extends ActionBarActivity implements IPlayerCharacterListener {

    //private TextView textView = (TextView) findViewById(R.id.);
    private PlayerCharacters pc;
    final static private String BUNGIE_URL = "https://www.bungie.net";

    public ArrayList<DestinyCharacterInfo> characterInfo = new ArrayList<>();
    public CharacterClass publicCharacterClass = new CharacterClass();

    @Override
    public void playerMembershipCallback(DestinyMembership dm) {
        if ( !dm.getMembershipId().isEmpty() )
            pc.pullCharacters(dm.getMembershipType(), dm.getMembershipId());
        //textView.setText("MembershipID is blank");
    }

    @Override
    public void playerCharacterCallback(DestinyCharacters[] dc) {
        //ArrayList<DestinyCharacters> Ids = new ArrayList<>();
        ArrayList<DestinyCharacterInfo> Ids = new ArrayList<>();
        setContentView(R.layout.activity_display_character_list);
        //setContentView(R.layout.characters_activity);

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

        ImageLoader imageLoader;

        imageLoader = VolleySingleton.getInstance(this).getImageLoader();

        if( imageLoader == null ) {
            Log.v("imageLoader: ", "Not getting assigned");
        }

        characterInfo.add( new DestinyCharacterInfo(dcInfo.getCharacterId(),
                                                    dcInfo.getCharacterLevel(),
                                                    dcInfo.getEmblemPath(),
                                                    dcInfo.getBackgroundPath(),
                                                    imageLoader));

        CharacterArrayAdapter adapter = new CharacterArrayAdapter(this, characterInfo);
        ListView listView = (ListView) findViewById(R.id.characterList);
        listView.setAdapter(adapter);
    }

    public void playerCharacterClassCallback(CharacterClass classType) {
        Log.v("Class: ", classType.getCharacterClass());
        publicCharacterClass = classType;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_display_message);
        setContentView(R.layout.activity_display_character_list);

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

    public void getInventory(View view) {
        Intent inventoryIntent = new Intent(this, DisplayInventoryActivity.class);

        startActivity(inventoryIntent);
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_display_character_list, menu);
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
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

    private String info[] = new String[6];
    private int count = 0;
    private int previousCount = 0;
    private int total = 0;

    private ArrayList<DestinyCharacterInfo> characterInfo = new ArrayList<>();
    private CharacterClass publicCharacterClass = new CharacterClass();
    private DestinyCharacterInfo publicDestinyCharacacterInfo = new DestinyCharacterInfo();

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

        //count = 0;

        publicDestinyCharacacterInfo = dcInfo;

        pc.pullCharacterClass(dcInfo.getClassHash());

        // String array to store character level and background path
        info[count] = Integer.toString(publicDestinyCharacacterInfo.getCharacterLevel());
        count = count+1;
        info[count] = publicDestinyCharacacterInfo.getBackgroundPath();
        count = count+1;

        //Total number of characters pulled
        total = total+1;
    }

    public void playerCharacterClassCallback(CharacterClass classType) {

        publicCharacterClass = classType;

        ImageLoader imageLoader;

        imageLoader = VolleySingleton.getInstance(this).getImageLoader();

        if( imageLoader == null ) {
            Log.v("imageLoader: ", "Not getting assigned");
        }

        // Keep track of how many times playerCharacterClassCallback has been called
        previousCount = previousCount + 1;

        Log.v("Count: ", Integer.toString(count));
        // TODO put in a method (aka a function)
        // Test to see if more than one character has been pulled before calling playerCharacterClassCallback
        if(total != previousCount) {
            // Test to see if all characters were pulled before calling playerCharacterClassCallback
            if(count == 6 && previousCount == 1) {
                characterInfo.add(new DestinyCharacterInfo(publicCharacterClass.getCharacterClass(),
                        Integer.parseInt(info[count - 6]),
                        publicDestinyCharacacterInfo.getEmblemPath(),
                        info[count-5],
                        imageLoader));
                }

            // Test to see if two characters were pulled before calling playerCharacterClassCallback
            else if(count == 6 && previousCount > 1) {
                characterInfo.add(new DestinyCharacterInfo(publicCharacterClass.getCharacterClass(),
                        Integer.parseInt(info[count - 4]),
                        publicDestinyCharacacterInfo.getEmblemPath(),
                        info[count-3],
                        imageLoader));
            }

            else {
                characterInfo.add(new DestinyCharacterInfo(publicCharacterClass.getCharacterClass(),
                        Integer.parseInt(info[count - 4]),
                        publicDestinyCharacacterInfo.getEmblemPath(),
                        info[count-3],
                        imageLoader));
                }
            }

        // If only one character was pulled since calling playerCharacterClassCallback
        else {
            characterInfo.add(new DestinyCharacterInfo(publicCharacterClass.getCharacterClass(),
                    Integer.parseInt(info[count - 2]),
                    info[count - 1],
                    publicDestinyCharacacterInfo.getBackgroundPath(),
                    imageLoader));
        }

        CharacterArrayAdapter adapter = new CharacterArrayAdapter(this, characterInfo);
        ListView listView = (ListView) findViewById(R.id.characterList);
        listView.setAdapter(adapter);

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
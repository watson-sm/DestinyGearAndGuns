package com.cs370.gwtm.destinygearandguns.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.cs370.gwtm.destinygearandguns.R;
import com.cs370.gwtm.destinygearandguns.controller.PlayerCharacters;
import com.cs370.gwtm.destinygearandguns.interfaces.CharacterArrayAdapter;
import com.cs370.gwtm.destinygearandguns.interfaces.IPlayerCharacterListener;
import com.cs370.gwtm.destinygearandguns.model.DestinyCharacterInfo;
import com.cs370.gwtm.destinygearandguns.model.DestinyCharacters;
import com.cs370.gwtm.destinygearandguns.model.DestinyMembership;

import java.util.ArrayList;

public class DisplayCharactersActivity extends ActionBarActivity implements IPlayerCharacterListener {

    private TextView textView;
    private PlayerCharacters pc;
    private String bungieURL = "https://www.bungie.net";

    @Override
    public void playerMembershipCallback(DestinyMembership dm) {
        if ( !dm.getMembershipId().isEmpty() )
            pc.pullCharacters(dm.getMembershipType(), dm.getMembershipId());
        else
            textView.setText("MembershipID is blank");
    }

    @Override
    public void playerCharacterCallback(DestinyCharacters[] dc) {
        ArrayList<DestinyCharacters> Ids = new ArrayList<>();
        setContentView(R.layout.activity_display_message);

        // Player's can have up to 3 characters.
        for(int i = 0; i < 3; i++) {
            pc.pullCharacterInfo(dc[i].getMembershipType(), dc[i].getMembershipId(), dc[i].getCharacterId());
            //Log.v("Character: ", dc[i].toString());
            Ids.add(new DestinyCharacters(dc[i].toString()));
            CharacterArrayAdapter adapter = new CharacterArrayAdapter(this, Ids);
            ListView listView = (ListView) findViewById(R.id.characterList);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void playerCharacterInfoCallback(DestinyCharacterInfo dcInfo) {
        // TODO figure out how to use emblem and background path to load images from url
        // and put in character level
        Log.v("emblem path: ", dcInfo.getEmblemPath() );
        Log.v("background path: ", dcInfo.getBackgroundPath() );
        Log.v("character level: ", String.valueOf( dcInfo.getCharacterLevel() ) );

        // TODO race hash isn't currently working. Has to do with nested JSON objects
        Log.v("race hash: ", String.valueOf( dcInfo.getRaceHash() ) );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textView = new TextView(this);

        //PlayerCharacters pc = new PlayerCharacters(this);
        pc = new PlayerCharacters(this);

        // Get the message from intent
        Intent intent = getIntent();

        // serviceMemberName, Username/Account name
        String serviceMemberName = intent.getStringExtra(MainActivity.ACCOUNT_NAME);

        // service checked either Xbox Live or PSN
        int serviceChecked = intent.getIntExtra("XBLChecked", 0);

        pc.pullMembership(serviceChecked, serviceMemberName);
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

package com.cs370.gwtm.destinygearandguns.interfaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.cs370.gwtm.destinygearandguns.R;
import com.cs370.gwtm.destinygearandguns.model.DestinyCharacterInfo;

import java.util.ArrayList;

/**
 * Created by josh on 4/12/15.
 * Array Adapter
 */
public class CharacterArrayAdapter extends ArrayAdapter<DestinyCharacterInfo> {

    final static private String BUNGIE_URL = "https://www.bungie.net";
    public CharacterArrayAdapter(Context context, ArrayList<DestinyCharacterInfo> users) {
        super(context, 0, users);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        DestinyCharacterInfo characters = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.characters_activity, parent, false);
        }

        // Lookup view for data population
        TextView level = (TextView) convertView.findViewById(R.id.level);
        //TextView hours = (TextView) convertView.findViewById(R.id.hours);
        //TextView character = (TextView) convertView.findViewById(R.id.character);
        NetworkImageView networkImageView = (NetworkImageView) convertView.findViewById(R.id.networkImageView);

        // Populate the data into the template view using the data object
        level.setText(Integer.toString(characters.getCharacterLevel()));
        //hours.setText(characters.getEmblemPath().replace("\\", ""));
        //character.setText(characters.getCharacterId());
        networkImageView.setImageUrl(BUNGIE_URL + characters.getBackgroundPath().replace("\\", ""), characters.getImageLoader());

        // Return the completed view to render on screen
        return convertView;
    }
}

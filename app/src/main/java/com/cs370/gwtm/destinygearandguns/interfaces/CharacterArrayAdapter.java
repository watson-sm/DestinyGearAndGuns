package com.cs370.gwtm.destinygearandguns.interfaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cs370.gwtm.destinygearandguns.R;
import com.cs370.gwtm.destinygearandguns.model.DestinyCharacterInfo;
import com.cs370.gwtm.destinygearandguns.model.DestinyCharacters;

import java.util.ArrayList;

/**
 * Created by josh on 4/12/15.
 */
public class CharacterArrayAdapter extends ArrayAdapter<DestinyCharacterInfo> {

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
        TextView hours = (TextView) convertView.findViewById(R.id.hours);
        TextView character = (TextView) convertView.findViewById(R.id.character);

        // Populate the data into the template view using the data object
        level.setText(Integer.toString(characters.getCharacterLevel()));
        hours.setText(characters.getEmblemPath().replace("\\", ""));
        character.setText(characters.getCharacterId());

        // Return the completed view to render on screen
        return convertView;
    }
}

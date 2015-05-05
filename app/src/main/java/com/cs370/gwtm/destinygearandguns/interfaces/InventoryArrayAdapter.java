package com.cs370.gwtm.destinygearandguns.interfaces;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.cs370.gwtm.destinygearandguns.R;
import com.cs370.gwtm.destinygearandguns.model.DestinyInventory;
import com.cs370.gwtm.destinygearandguns.model.DestinyInventoryItem;

import java.util.ArrayList;

/**
 * Created by josh on 4/18/15.
 */
public class InventoryArrayAdapter extends ArrayAdapter<DestinyInventoryItem> {

    final static private String BUNGIE_URL = "https://www.bungie.net";

    public InventoryArrayAdapter(Context context, ArrayList<DestinyInventoryItem> inventory) {
        super(context, 0, inventory);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        DestinyInventoryItem items = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_inventory, parent, false);
        }

        //Log.v("URL: ", BUNGIE_URL + items.getIconPath().replace("//", "/"));

        TextView name = (TextView) convertView.findViewById(R.id.itemName);
        TextView type = (TextView) convertView.findViewById(R.id.itemType);
        TextView description = (TextView) convertView.findViewById(R.id.itemDescription);
        TextView tier = (TextView) convertView.findViewById(R.id.tierName);
        NetworkImageView networkImageView = (NetworkImageView) convertView.findViewById(R.id.type);

        /*if(items.getItemTypeName().equals("Helmet")) {
            itemImage.setImageResource(R.drawable.armour);
        }

        else { itemImage.setImageResource(R.drawable.weapon); }*/

        name.setText(items.getItemName());
        type.setText(items.getItemTypeName());
        description.setText(items.getItemDescription());
        tier.setText(items.getTierTypeName());
        networkImageView.setImageUrl(BUNGIE_URL + items.getIconPath().replace("//", "/"), items.getImageLoader());

        return convertView;
    }


}

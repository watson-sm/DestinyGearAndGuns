package com.cs370.gwtm.destinygearandguns.interfaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs370.gwtm.destinygearandguns.R;
import com.cs370.gwtm.destinygearandguns.model.DestinyInventory;

import java.util.ArrayList;

/**
 * Created by josh on 4/18/15.
 */
public class InventoryArrayAdapter extends ArrayAdapter<DestinyInventory> {

    public InventoryArrayAdapter(Context context, ArrayList<DestinyInventory> inventory) {
        super(context, 0, inventory);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        DestinyInventory items = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_inventory, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.itemName);
        TextView type = (TextView) convertView.findViewById(R.id.itemType);
        TextView description = (TextView) convertView.findViewById(R.id.itemDescription);
        ImageView itemImage = (ImageView) convertView.findViewById(R.id.type);

        if(items.getItemTypeName().equals("Helmet")) {
            itemImage.setImageResource(R.drawable.armour);
        }

        else { itemImage.setImageResource(R.drawable.weapon); }

        name.setText(items.getItemName());
        type.setText(items.getItemTypeName());
        description.setText(items.getItemDescription());

        return convertView;
    }


}

package com.cs370.gwtm.destinygearandguns.model;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by josh on 5/3/15.
 */

public class DestinyInventoryItem {
    String itemName;
    String itemDescription;
    String itemTypeName;
    String tierTypeName;
    String iconPath;
    ImageLoader imageLoader;

    public DestinyInventoryItem() {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemTypeName = itemTypeName;
        this.tierTypeName = tierTypeName;
    }

    public DestinyInventoryItem(String itemName, String itemDescription, String itemTypeName, String tierTypeName, String iconPath,
                                ImageLoader imageLoader) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemTypeName = itemTypeName;
        this.tierTypeName = tierTypeName;
        this.iconPath = iconPath;
        this.imageLoader = imageLoader;
    }

    public String getItemName() { return itemName; }

    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getItemDescription() { return itemDescription; }

    public void setItemDescription(String itemDescription) { this.itemDescription = itemDescription; }

    public String getItemTypeName() { return itemTypeName; }

    public void setItemTypeName(String itemTypeName) { this.itemTypeName = itemTypeName; }

    public String getTierTypeName() { return tierTypeName; }

    public void setTierTypeName(String tierTypeName) { this.tierTypeName = tierTypeName; }

    public String getIconPath() { return iconPath; }

    public void setIconPath(String iconPath) { this.iconPath = iconPath; }

    public ImageLoader getImageLoader() { return imageLoader; }

    public void setImageLoader(ImageLoader imageLoader) { this.imageLoader = imageLoader; }


}

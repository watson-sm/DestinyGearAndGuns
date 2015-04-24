package com.cs370.gwtm.destinygearandguns.model;

import java.util.ArrayList;

/**
 * Created by josh on 4/18/15.
 */
public class DestinyInventory {
    private String itemName;
    private String itemDescription;
    private String itemTypeName;
    private String itemType;


    public ArrayList<DestinyInventory> inventoryItems;

    public DestinyInventory (String itemName, String itemDescription, String itemTypeName, String itemType) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemTypeName = itemTypeName;
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public ArrayList<DestinyInventory> getDestinyInventory() {
        return inventoryItems;
    }

    public void setInventoryItem(ArrayList<DestinyInventory> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    public static ArrayList<DestinyInventory> getInventory() {
        ArrayList<DestinyInventory> users = new ArrayList<DestinyInventory>();
        users.add(new DestinyInventory("Sun Singer", "Certainly not one of the usual suspects.", "Pulse Rifle", "Weapon"));
        users.add(new DestinyInventory("Apotheosis Veil", "Most helmets protect the mind from the universe. Not this one.", "Helmet", "Armour"));
        users.add(new DestinyInventory("Super Good Advice", "This weapon is full of it.", "Machine Gun", "Weapon"));
        return users;
    }

}

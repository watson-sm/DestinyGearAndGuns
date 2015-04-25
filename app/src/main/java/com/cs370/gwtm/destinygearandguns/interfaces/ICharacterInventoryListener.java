package com.cs370.gwtm.destinygearandguns.interfaces;

import com.cs370.gwtm.destinygearandguns.model.Equippable;

import java.util.ArrayList;

/**
 * Created by Kiladre on 4/25/15.
 * Listener for the inventory call
 */
public interface ICharacterInventoryListener {
    //public void playerCharacterInventoryCallback(ArrayList<Equippable> equippable);
    public void playerCharacterInventoryCallback(Equippable equippable);
}

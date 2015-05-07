package com.cs370.gwtm.destinygearandguns.interfaces;

import com.cs370.gwtm.destinygearandguns.model.DestinyInventoryItem;
import com.cs370.gwtm.destinygearandguns.model.Equippable;

import java.util.List;

/**
 * Created by Kiladre on 4/25/15.
 * Listener for the inventory call
 */
public interface ICharacterInventoryListener {
    void playerCharacterInventoryCallback(List<Equippable> equippable);
    void playerItemCallback(DestinyInventoryItem item);
}

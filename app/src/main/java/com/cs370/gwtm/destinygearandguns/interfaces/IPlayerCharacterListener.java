package com.cs370.gwtm.destinygearandguns.interfaces;

import com.cs370.gwtm.destinygearandguns.model.CharacterClass;
import com.cs370.gwtm.destinygearandguns.model.DestinyCharacterInfo;
import com.cs370.gwtm.destinygearandguns.model.DestinyCharacters;
import com.cs370.gwtm.destinygearandguns.model.DestinyMembership;

/**
 * Created by Kiladre on 4/12/15.
 * Listener
 */
public interface IPlayerCharacterListener {
    public void playerMembershipCallback(DestinyMembership dm);
    public void playerCharacterCallback(DestinyCharacters[] dc);
    public void playerCharacterInfoCallback(DestinyCharacterInfo dcInfo);
    public void playerCharacterClassCallback(CharacterClass characterClass);
}

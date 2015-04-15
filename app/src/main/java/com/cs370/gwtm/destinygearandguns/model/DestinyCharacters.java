package com.cs370.gwtm.destinygearandguns.model;

import java.util.ArrayList;

/**
 * Created by Kiladre on 4/14/15.
 * Model for storing character information
 */
public class DestinyCharacters {
    private String characterId;
    //private boolean deleted;
    private ArrayList<DestinyCharacters> destinyCharacters;

    public DestinyCharacters(String characterId) {
        this.characterId = characterId;
        //deleted = false;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    public String getCharacterId() {
        return characterId;
    }

    // public void setDeleted(boolean deleted) { this.deleted = deleted; }

    // public boolean getDeleted() { return deleted; }

    public ArrayList<DestinyCharacters> getDestinyCharacters() { return destinyCharacters; }

    public void setDestinyCharacters(ArrayList<DestinyCharacters> destinyCharacters) {
        this.destinyCharacters = destinyCharacters;
    }

    @Override
    public String toString() {
        return String.format("%s", characterId);
    }
}

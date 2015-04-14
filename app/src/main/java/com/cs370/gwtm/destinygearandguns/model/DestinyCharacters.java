package com.cs370.gwtm.destinygearandguns.model;

/**
 * Created by Kiladre on 4/14/15.
 * Model for storing character information
 */
public class DestinyCharacters {
    private String characterId;
    private boolean deleted;

    public DestinyCharacters() {
        characterId = "";
        deleted = false;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    public String getCharacterId() {
        return characterId;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean getDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return String.format("CharacterID = %s", characterId);
    }
}

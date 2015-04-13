package com.cs370.gwtm.destinygearandguns.model;

import java.util.ArrayList;

/**
 * Created by josh on 4/12/15.
 */
public class Characters {

    public String characterHours;
    public String characterLevel;

    public Characters(String characterHours, String characterLevel) {
        this.characterHours = characterHours;
        this.characterLevel = characterLevel;
    }

    public String getCharacterHours() {
        return characterHours;
    }

    public String getCharacterLevel() {
        return characterLevel;
    }

    // Dummy data so the view can be populated using CharacterArrayAdapter
    public static ArrayList<Characters> getUsers() {
        ArrayList<Characters> characters = new ArrayList<Characters>();
        characters.add(new Characters("700", "9"));
        characters.add(new Characters("900", "15"));
        characters.add(new Characters("400", "13"));
        characters.add(new Characters("400", "13"));
        characters.add(new Characters("400", "13"));
        characters.add(new Characters("400", "13"));
        characters.add(new Characters("400", "13"));
        characters.add(new Characters("400", "13"));
        characters.add(new Characters("400", "13"));
        characters.add(new Characters("400", "13"));
        characters.add(new Characters("400", "13"));
        characters.add(new Characters("400", "13"));
        characters.add(new Characters("400", "13"));
        characters.add(new Characters("400", "13"));
        return characters;
    }

}

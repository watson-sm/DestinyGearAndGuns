package com.cs370.gwtm.destinygearandguns.model;

/**
 * Created by Kiladre on 4/14/15.
 * Model for storing character information
 */
public class DestinyCharacters {
    private String characterId;
    private int membershipType;
    private String membershipId;

    public DestinyCharacters(String characterId) {
        this.characterId = characterId;
    }

    public int getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(int membershipType) {
        this.membershipType = membershipType;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public String getCharacterId() {
        return characterId;
    }

    @Override
    public String toString() {
        return String.format("%s", characterId);
    }
}

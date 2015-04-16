package com.cs370.gwtm.destinygearandguns.model;

/**
 * Created by Kiladre on 4/15/15.
 * For storing information for particular characters
 */
public class DestinyCharacterInfo {
    String membershipId = "";
    int membershipType = 0;
    String characterId = "";
    int characterLevel = 0;
    int baseCharacterLevel = 0;
    boolean isPrestigeLevel = false;
    int raceHash = 0;
    int genderHash = 0;
    int classHash = 0;
    int genderType = 0;
    int classType = 0;
    double percentToNextLevel = 0.0;
    String emblemPath = "";
    String backgroundPath = "";

    public DestinyCharacterInfo() {
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public int getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(int membershipType) {
        this.membershipType = membershipType;
    }

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    public int getCharacterLevel() {
        return characterLevel;
    }

    public void setCharacterLevel(int characterLevel) {
        this.characterLevel = characterLevel;
    }

    public int getBaseCharacterLevel() {
        return baseCharacterLevel;
    }

    public void setBaseCharacterLevel(int baseCharacterLevel) {
        this.baseCharacterLevel = baseCharacterLevel;
    }

    public boolean isPrestigeLevel() {
        return isPrestigeLevel;
    }

    public void setPrestigeLevel(boolean isPrestigeLevel) {
        this.isPrestigeLevel = isPrestigeLevel;
    }

    public int getRaceHash() {
        return raceHash;
    }

    public void setRaceHash(int raceHash) {
        this.raceHash = raceHash;
    }

    public int getGenderHash() {
        return genderHash;
    }

    public void setGenderHash(int genderHash) {
        this.genderHash = genderHash;
    }

    public int getClassHash() {
        return classHash;
    }

    public void setClassHash(int classHash) {
        this.classHash = classHash;
    }

    public int getGenderType() {
        return genderType;
    }

    public void setGenderType(int genderType) {
        this.genderType = genderType;
    }

    public int getClassType() {
        return classType;
    }

    public void setClassType(int classType) {
        this.classType = classType;
    }

    public double getPercentToNextLevel() {
        return percentToNextLevel;
    }

    public void setPercentToNextLevel(double percentToNextLevel) {
        this.percentToNextLevel = percentToNextLevel;
    }

    public String getEmblemPath() {
        return emblemPath;
    }

    public void setEmblemPath(String emblemPath) {
        this.emblemPath = emblemPath;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public void setBackgroundPath(String backgroundPath) {
        this.backgroundPath = backgroundPath;
    }
}

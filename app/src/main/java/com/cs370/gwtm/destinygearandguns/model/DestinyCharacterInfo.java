package com.cs370.gwtm.destinygearandguns.model;

import com.android.volley.toolbox.ImageLoader;


/**
 * Created by Kiladre on 4/15/15.
 * For storing information for particular characters
 */
public class DestinyCharacterInfo {
    String membershipId;
    int membershipType;
    String characterId;
    int characterLevel;
    long raceHash;
    long genderHash;
    long classHash;
    long emblemHash;
    int genderType;
    int classType;
    String emblemPath;
    String backgroundPath;



    String characterClass;
    //NetworkImageView backgroundImg;
    ImageLoader imageLoader;

    public DestinyCharacterInfo() {
        //membershipId = "";
        //membershipType = 0;
        characterClass = "";
        characterId = "";
        characterLevel = 0;
        raceHash = 0;
        //genderHash = 0;
        classHash = 0;
        /*emblemHash = 0;
        genderType = 0;
        classType = 0;*/
        emblemPath = "";
        backgroundPath = "";
    }
/*
    public NetworkImageView getBackgroundImg() {
        return backgroundImg;
    }
*/
/*
    public void setBackgroundImg(NetworkImageView backgroundImg) {
        this.backgroundImg = backgroundImg;
    }
*/
/*
    public long getEmblemHash() {
        return emblemHash;
    }
*/
    public DestinyCharacterInfo(String characterClass, int characterLevel, String emblemPath, String
                                backgroundPath, ImageLoader imageLoader) {
        this.characterClass = characterClass;
        this.characterLevel = characterLevel;
        this.emblemPath = emblemPath;
        this.backgroundPath = backgroundPath;
        this.imageLoader = imageLoader;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public void setEmblemHash(long emblemHash) {
        this.emblemHash = emblemHash;
    }
/*
    public String getMembershipId() {
        return membershipId;
    }
*/
    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }
/*
    public int getMembershipType() {
        return membershipType;
    }
*/
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

    public long getRaceHash() {
        return raceHash;
    }

    public void setRaceHash(long raceHash) {
        this.raceHash = raceHash;
    }
/*
    public long getGenderHash() {
        return genderHash;
    }
*/
    public void setGenderHash(long genderHash) {
        this.genderHash = genderHash;
    }

    public long getClassHash() {
        return classHash;
    }

    public void setClassHash(long classHash) {
        this.classHash = classHash;
    }
/*
    public int getGenderType() {
        return genderType;
    }
*/
    public void setGenderType(int genderType) {
        this.genderType = genderType;
    }
/*
    public int getClassType() {
        return classType;
    }
*/
    public void setClassType(int classType) {
        this.classType = classType;
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

    public ImageLoader getImageLoader() { return imageLoader; }
/*
    public void setImageLoader(ImageLoader imageLoader) { this.imageLoader = imageLoader; }
*/

}

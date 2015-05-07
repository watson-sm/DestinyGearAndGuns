package com.cs370.gwtm.destinygearandguns.model;

/**
 * Created by Kiladre on 4/11/15.
 * Model for storing Player's platform(membershipType),
 * their displayed name and membership id(How Bungie keeps track of players).
 */
public class DestinyMembership {
    private String iconPath;
    private int membershipType;
    private String membershipId;
    private String displayName;

    public DestinyMembership() {
        iconPath = "";
        membershipType = 0;
        membershipId = "";
        displayName = "";
    }

    public String getMembershipId() {
        return membershipId;
    }

    public int getMembershipType() {
        return membershipType;
    }

/*

    public String getDisplayName() {
        return displayName;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String path) {
        iconPath = path;
    }

    public void setMembershipType(int type) {
        membershipType = type;
    }

    public void setMembershipId(String id) {
        membershipId = id;
    }

    public void setDisplayName(String acctName) {
        displayName = acctName;
    }
*/
    @Override
    public String toString() {
        return String.format(" iconPath=%s \n membershipType=%d \n membershipId=%s \n displayName=%s",
            iconPath, membershipType, membershipId, displayName);
    }
}

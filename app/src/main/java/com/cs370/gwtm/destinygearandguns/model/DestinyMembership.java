package com.cs370.gwtm.destinygearandguns.model;

import java.util.ArrayList;

/**
 * Created by Kiladre on 4/11/15.
 */
public class DestinyMembership {
    private String iconPath;
    private int membershipType;
    private String membershipId;
    private String displayName;

    public DestinyMembership(String iconPath, int membershipType, String membershipId, String displayName) {
        this.iconPath = iconPath;
        this.membershipType = membershipType;
        this.membershipId = membershipId;
        this.displayName = displayName;
    }
    @Override
    public String toString() {
        return String.format(" iconPath=%s \n membershipType=%d \n membershipId=%s \n displayName=%s",
            iconPath, membershipType, membershipId, displayName);
    }

}

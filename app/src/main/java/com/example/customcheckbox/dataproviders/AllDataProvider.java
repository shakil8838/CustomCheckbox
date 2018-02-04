package com.example.customcheckbox.dataproviders;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anonymous on 1/30/2018.
 */
public class AllDataProvider {

    @SerializedName("Name")
    private String fullName;

    @SerializedName("ActiveStatus")
    private String activeId;

    @SerializedName("ProfileImage")
    private String imageUrl;

    public String getFullName() {
        return fullName;
    }

    public String getActiveId() {
        return activeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

package com.example.flickrimagesseeker.api.entities.photos_info_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlickrImageInfoSearchResult {

    @SerializedName("photo")
    @Expose
    private FlickrImageInfo photoInfo;

    public FlickrImageInfo getPhotoInfo() {
        return photoInfo;
    }

    public void setPhotoInfo(FlickrImageInfo photoInfo) {
        this.photoInfo = photoInfo;
    }
}

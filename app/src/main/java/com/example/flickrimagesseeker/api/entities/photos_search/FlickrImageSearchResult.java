package com.example.flickrimagesseeker.api.entities.photos_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlickrImageSearchResult {

    @SerializedName("photos")
    @Expose
    private FlickrImageSet photos;

    public FlickrImageSet getPhotos() {
        return photos;
    }

    public void setPhotos(FlickrImageSet photos) {
        this.photos = photos;
    }
}

package com.example.flickrimagesseeker.api.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageSearchResult {

    @SerializedName("photos")
    @Expose
    private ImagesSet photos;

    public ImagesSet getPhotos() {
        return photos;
    }

    public void setPhotos(ImagesSet photos) {
        this.photos = photos;
    }
}

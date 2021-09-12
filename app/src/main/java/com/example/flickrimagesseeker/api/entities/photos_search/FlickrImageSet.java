package com.example.flickrimagesseeker.api.entities.photos_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FlickrImageSet {

    @SerializedName("photo")
    @Expose
    private ArrayList<FlickrImage> photoList;

    @SerializedName("page")
    @Expose
    private int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<FlickrImage> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(ArrayList<FlickrImage> photoList) {
        this.photoList = photoList;
    }




}

package com.example.flickrimagesseeker.api.objects;

import com.example.flickrimagesseeker.data.entities.FlickrImage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ImagesSet {

    @SerializedName("photo")
    @Expose
    private ArrayList<FlickrImage> photoList;

    private int page;
    private String pages;
    private int perpage;
    private String total;
    private String stat;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<FlickrImage> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(ArrayList<FlickrImage> photoList) {
        this.photoList = photoList;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }



}

package com.example.flickrimagesseeker.data.entities;

import com.example.flickrimagesseeker.api.entities.photos_info_search.FlickrImageInfo;

import java.util.Objects;

public class ListImage {

    private int pageIndex;
    private FlickrImageInfo photoInfo;

    public ListImage(int pageIndex, FlickrImageInfo photoInfo) {
        this.pageIndex = pageIndex;
        this.photoInfo = photoInfo;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public FlickrImageInfo getPhotoInfo() {
        return photoInfo;
    }

    public void setPhotoInfo(FlickrImageInfo photoInfo) {
        this.photoInfo = photoInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListImage)) return false;
        ListImage listImage = (ListImage) o;
        return pageIndex == listImage.pageIndex &&
                Objects.equals(photoInfo, listImage.photoInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageIndex, photoInfo);
    }
}

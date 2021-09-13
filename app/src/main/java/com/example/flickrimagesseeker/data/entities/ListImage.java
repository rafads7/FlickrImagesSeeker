package com.example.flickrimagesseeker.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.flickrimagesseeker.api.entities.photos_info_search.FlickrImageInfo;

import java.util.Objects;

public class ListImage implements Parcelable {

    private int pageIndex;
    private FlickrImageInfo photoInfo;

    public ListImage(int pageIndex, FlickrImageInfo photoInfo) {
        this.pageIndex = pageIndex;
        this.photoInfo = photoInfo;
    }

    protected ListImage(Parcel in) {
        pageIndex = in.readInt();
        photoInfo = in.readParcelable(FlickrImageInfo.class.getClassLoader());
    }

    public static final Creator<ListImage> CREATOR = new Creator<ListImage>() {
        @Override
        public ListImage createFromParcel(Parcel in) {
            return new ListImage(in);
        }

        @Override
        public ListImage[] newArray(int size) {
            return new ListImage[size];
        }
    };

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

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(photoInfo, flags);
        parcel.writeInt(pageIndex);
    }

}

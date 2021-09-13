package com.example.flickrimagesseeker.api.entities.photos_info_search;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class FlickrImageOwner implements Parcelable {

    @Expose
    private String realname;

    protected FlickrImageOwner(Parcel in) {
        realname = in.readString();
    }

    public static final Creator<FlickrImageOwner> CREATOR = new Creator<FlickrImageOwner>() {
        @Override
        public FlickrImageOwner createFromParcel(Parcel in) {
            return new FlickrImageOwner(in);
        }

        @Override
        public FlickrImageOwner[] newArray(int size) {
            return new FlickrImageOwner[size];
        }
    };

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlickrImageOwner)) return false;
        FlickrImageOwner that = (FlickrImageOwner) o;
        return Objects.equals(realname, that.realname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(realname);
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(realname);
    }
}

package com.example.flickrimagesseeker.api.entities.photos_info_search;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class FlickrImageDates implements Parcelable {

    @Expose
    private long posted;

    protected FlickrImageDates(Parcel in) {
        posted = in.readLong();
    }

    public static final Creator<FlickrImageDates> CREATOR = new Creator<FlickrImageDates>() {
        @Override
        public FlickrImageDates createFromParcel(Parcel in) {
            return new FlickrImageDates(in);
        }

        @Override
        public FlickrImageDates[] newArray(int size) {
            return new FlickrImageDates[size];
        }
    };

    public long getPosted() {
        return posted;
    }

    public void setPosted(long posted) {
        this.posted = posted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlickrImageDates)) return false;
        FlickrImageDates that = (FlickrImageDates) o;
        return Objects.equals(posted, that.posted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posted);
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(posted);
    }
}

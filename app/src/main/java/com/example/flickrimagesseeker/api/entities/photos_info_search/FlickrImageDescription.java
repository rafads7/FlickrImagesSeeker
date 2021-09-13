package com.example.flickrimagesseeker.api.entities.photos_info_search;

import android.os.Parcel;
import android.os.Parcelable;

public class FlickrImageDescription implements Parcelable {

    private String _content;


    protected FlickrImageDescription(Parcel in) {
        _content = in.readString();
    }

    public static final Creator<FlickrImageDescription> CREATOR = new Creator<FlickrImageDescription>() {
        @Override
        public FlickrImageDescription createFromParcel(Parcel in) {
            return new FlickrImageDescription(in);
        }

        @Override
        public FlickrImageDescription[] newArray(int size) {
            return new FlickrImageDescription[size];
        }
    };

    public String get() {
        return _content;
    }

    public void set(String _content) {
        this._content = _content;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_content);
    }
}

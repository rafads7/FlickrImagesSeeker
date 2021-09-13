package com.example.flickrimagesseeker.api.entities.photos_info_search;

import android.os.Parcel;
import android.os.Parcelable;

public class FlickrImageTitle implements Parcelable {

    private String _content;


    protected FlickrImageTitle(Parcel in) {
        _content = in.readString();
    }

    public static final Creator<FlickrImageTitle> CREATOR = new Creator<FlickrImageTitle>() {
        @Override
        public FlickrImageTitle createFromParcel(Parcel in) {
            return new FlickrImageTitle(in);
        }

        @Override
        public FlickrImageTitle[] newArray(int size) {
            return new FlickrImageTitle[size];
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

package com.example.flickrimagesseeker.api.entities.photos_info_search;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.flickrimagesseeker.api.entities.photos_search.FlickrImage;
import com.google.gson.annotations.Expose;

import java.util.Objects;

public class FlickrImageInfo implements Parcelable {

    @Expose
    private FlickrImageOwner owner;

    @Expose
    private FlickrImageDates dates;

    @Expose
    private String id;

    @Expose
    private String secret;

    @Expose
    private FlickrImageTitle title;

    @Expose
    private String server;

    @Expose
    private int farm;

    @Expose
    private FlickrImageDescription description;

    protected FlickrImageInfo(Parcel in) {
        id = in.readString();
        secret = in.readString();
        server = in.readString();
        farm = in.readInt();
    }

    public static final Creator<FlickrImageInfo> CREATOR = new Creator<FlickrImageInfo>() {
        @Override
        public FlickrImageInfo createFromParcel(Parcel in) {
            return new FlickrImageInfo(in);
        }

        @Override
        public FlickrImageInfo[] newArray(int size) {
            return new FlickrImageInfo[size];
        }
    };

    public FlickrImageDates getDates() {
        return dates;
    }

    public void setDates(FlickrImageDates dates) {
        this.dates = dates;
    }

    public FlickrImageOwner getOwner() {
        return owner;
    }

    public void setOwner(FlickrImageOwner owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public FlickrImageDescription getDescription() {
        return description;
    }

    public void setDescription(FlickrImageDescription description) {
        this.description = description;
    }

    public FlickrImageTitle getTitle() {
        return title;
    }

    public void setTitle(FlickrImageTitle title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlickrImageInfo)) return false;
        FlickrImageInfo that = (FlickrImageInfo) o;
        return farm == that.farm &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(id, that.id) &&
                Objects.equals(secret, that.secret) &&
                Objects.equals(server, that.server) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, id, secret, server, farm, description);
    }


    public String getThumbnail() {
        String url = String.format("https://farm%s.staticflickr.com/%s/%s_%s_t.jpg", getFarm(), getServer(), getId(), getSecret());
        return url;
    }

    public String getUrl() {
        String url = String.format("https://farm%s.staticflickr.com/%s/%s_%s.jpg", getFarm(), getServer(), getId(), getSecret());
        return url;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(secret);
        dest.writeString(server);
        dest.writeInt(farm);
        dest.writeParcelable(owner, flags);
        dest.writeParcelable(description, flags);
        dest.writeParcelable(title, flags);
    }
}

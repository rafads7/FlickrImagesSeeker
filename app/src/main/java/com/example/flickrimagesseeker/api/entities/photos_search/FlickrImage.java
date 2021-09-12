package com.example.flickrimagesseeker.api.entities.photos_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class FlickrImage {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("secret")
    @Expose
    private String secret;

    @SerializedName("server")
    @Expose
    private String server;

    @SerializedName("farm")
    @Expose
    private int farm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlickrImage)) return false;
        FlickrImage that = (FlickrImage) o;
        return farm == that.farm &&
                Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(secret, that.secret) &&
                Objects.equals(server, that.server);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, secret, server, farm);
    }

    public String getUrl() {
        String url = String.format("https://farm%s.staticflickr.com/%s/%s_%s_t.jpg", getFarm(), getServer(), getId(), getSecret());
        return url;
    }
}

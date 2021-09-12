package com.example.flickrimagesseeker.api.entities.photos_info_search;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class FlickrImageOwner {

    @Expose
    private String realname;

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
}

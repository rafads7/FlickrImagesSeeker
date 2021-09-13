package com.example.flickrimagesseeker.data.datasources;

import com.example.flickrimagesseeker.api.FlickrApi;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FlickrRemoteDataSource {

    private FlickrApi mApi;

    @Inject
    public FlickrRemoteDataSource(FlickrApi flickrApi){
        mApi = flickrApi;
    }

    public FlickrApi getApi() {
        return mApi;
    }
}

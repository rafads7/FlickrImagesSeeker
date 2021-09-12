package com.example.flickrimagesseeker.data.repositories;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.Pager;

import com.example.flickrimagesseeker.api.entities.photos_search.FlickrImage;

public interface Repository {

    MutableLiveData<Pager<Integer, FlickrImage>> getSearchResult(String query);
}

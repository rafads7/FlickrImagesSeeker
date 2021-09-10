package com.example.flickrimagesseeker.data.repositories;

import androidx.paging.PagingData;

import com.example.flickrimagesseeker.data.entities.FlickrImage;

import org.jetbrains.annotations.NotNull;

import io.reactivex.rxjava3.core.Flowable;

public interface Repository {

    @NotNull Flowable<PagingData<FlickrImage>> getSearchResult(String query);
}

package com.example.flickrimagesseeker.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import com.example.flickrimagesseeker.data.datasources.FlickrImagePagingSource;
import com.example.flickrimagesseeker.data.datasources.FlickrLocalDataSource;
import com.example.flickrimagesseeker.data.datasources.FlickrRemoteDataSource;
import com.example.flickrimagesseeker.data.entities.ListImage;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FlickrRepository{

    private final FlickrLocalDataSource mLocalDataSource;
    private final FlickrRemoteDataSource mRemoteDataSource;

    @Inject
    public FlickrRepository (FlickrLocalDataSource localDataSource, FlickrRemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public @NotNull LiveData<PagingData<ListImage>> getSearchResult(String query) {

        Pager<Integer, ListImage> pager = new Pager(
                new PagingConfig(20), //new PagingConfig(20, 10, true, 100)
                () -> new FlickrImagePagingSource(mRemoteDataSource.getApi(), query));

        return PagingLiveData.getLiveData(pager);
    }

}

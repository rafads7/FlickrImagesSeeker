package com.example.flickrimagesseeker.data.repositories;

import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.flickrimagesseeker.data.datasources.FlickrImagePagingSource;
import com.example.flickrimagesseeker.data.datasources.FlickrLocalDataSource;
import com.example.flickrimagesseeker.data.datasources.FlickrRemoteDataSource;
import com.example.flickrimagesseeker.data.entities.FlickrImage;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Flowable;


@Singleton
public class FlickrRepository implements Repository{

    private FlickrLocalDataSource mLocalDataSource;
    private FlickrRemoteDataSource mRemoteDataSource;

    @Inject
    public FlickrRepository (FlickrLocalDataSource localDataSource, FlickrRemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public @NotNull Flowable<PagingData<FlickrImage>> getSearchResult(String query) {

        Pager<Integer, FlickrImage> pager = new Pager(
                new PagingConfig(20), // new PagingConfig(20, 10, false, 100),
                () -> new FlickrImagePagingSource(mRemoteDataSource.getApi(), query));

        return PagingRx.getFlowable(pager);
    }

}

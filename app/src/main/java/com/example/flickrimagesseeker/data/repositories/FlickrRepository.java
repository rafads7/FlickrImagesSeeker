package com.example.flickrimagesseeker.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import com.example.flickrimagesseeker.data.datasources.FlickrImagePagingSource;
import com.example.flickrimagesseeker.data.datasources.FlickrLocalDataSource;
import com.example.flickrimagesseeker.data.datasources.FlickrRemoteDataSource;
import com.example.flickrimagesseeker.api.entities.photos_search.FlickrImage;
import com.example.flickrimagesseeker.data.entities.ListImage;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class FlickrRepository{

    private FlickrLocalDataSource mLocalDataSource;
    private FlickrRemoteDataSource mRemoteDataSource;
    private @NotNull LiveData<PagingData<ListImage>> mImages = new MutableLiveData<>();


    @Inject
    public FlickrRepository (FlickrLocalDataSource localDataSource, FlickrRemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public @NotNull LiveData<PagingData<ListImage>> getSearchResult(String query) {

        Pager<Integer, ListImage> pager = new Pager(
                new PagingConfig(20), // new PagingConfig(20, 10, false, 100),
                () -> new FlickrImagePagingSource(mRemoteDataSource.getApi(), query));

        mImages = PagingLiveData.getLiveData(pager);
        return mImages;

        /*
        Pager<Integer, FlickrImage> pager2 = Pager<>(
                new PagingConfig(20),
                () -> FlickrImagePagingSource(mRemoteDataSource.getApi(), query));
*/

        //return PagingRx.getFlowable(pager);
    }

}

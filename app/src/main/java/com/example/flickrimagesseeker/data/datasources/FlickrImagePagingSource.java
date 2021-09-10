package com.example.flickrimagesseeker.data.datasources;

import androidx.annotation.NonNull;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.flickrimagesseeker.api.FlickrApi;
import com.example.flickrimagesseeker.api.objects.ImageSearchResult;
import com.example.flickrimagesseeker.data.entities.FlickrImage;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FlickrImagePagingSource extends RxPagingSource<Integer, FlickrImage> {

    private static final int FIRST_PAGE_INDEX = 1;

    @NonNull
    private final FlickrApi mApi;
    @NonNull
    private final String mQuery;

    public FlickrImagePagingSource(@NonNull FlickrApi api, @NonNull String query) {
        mApi = api;
        mQuery = query;
    }

    @Override
    public @NotNull Single<LoadResult<Integer, FlickrImage>> loadSingle(@NotNull LoadParams<Integer> loadParams) {

        int perPage = loadParams.getLoadSize();
        int pageIndex = loadParams.getKey() == null ? FIRST_PAGE_INDEX : loadParams.getKey();

        //TODO ApiKEY
        return mApi.searchPhotos("", mQuery, pageIndex, perPage)
                .subscribeOn(Schedulers.io())
                .map(response -> toLoadResult(response))
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer, FlickrImage> toLoadResult(@NonNull ImageSearchResult response) {

        return new LoadResult.Page<>(
                response.getPhotos().getPhotoList(),
                response.getPhotos().getPage() - 1,
                response.getPhotos().getPage() + 1,
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }

    @Override
    public @Nullable Integer getRefreshKey(@NotNull PagingState<Integer, FlickrImage> pagingState) {

        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null)
            return null;

        LoadResult.Page<Integer, FlickrImage> anchorPage = pagingState.closestPageToPosition(anchorPosition);
        if (anchorPage == null)
            return null;

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null)
            return prevKey + 1;

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null)
            return nextKey - 1;

        return null;
    }
}

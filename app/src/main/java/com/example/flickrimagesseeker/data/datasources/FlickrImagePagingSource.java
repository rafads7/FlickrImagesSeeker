package com.example.flickrimagesseeker.data.datasources;

import androidx.annotation.NonNull;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.flickrimagesseeker.api.FlickrApi;
import com.example.flickrimagesseeker.data.entities.ListImage;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.example.flickrimagesseeker.api.FlickrApi.USER_API_KEY;

public class FlickrImagePagingSource extends RxPagingSource<Integer, ListImage> {

    private static final int FIRST_PAGE_INDEX = 1;
    private final String FORMAT = "json", NJC = "1";

    @NonNull
    private final FlickrApi mApi;
    @NonNull
    private final String mQuery;

    public FlickrImagePagingSource(@NonNull FlickrApi api, @NonNull String query) {
        mApi = api;
        mQuery = query;
    }

    @Override
    public @NotNull Single<LoadResult<Integer, ListImage>> loadSingle(@NotNull LoadParams<Integer> loadParams) {

        int perPage = loadParams.getLoadSize();
        int pageIndex = loadParams.getKey() == null ? FIRST_PAGE_INDEX : loadParams.getKey();

        return mApi.searchPhotos(USER_API_KEY, mQuery, pageIndex, perPage, FORMAT, NJC)
                .concatMap(searchResult -> Flowable.fromIterable(searchResult.getPhotos().getPhotoList()))
                .flatMap(flickrImage -> mApi.getPhotoInfo(USER_API_KEY, flickrImage.getId(), FORMAT, NJC),
                        (flickrImage, infoSearchResult) -> new ListImage(pageIndex, infoSearchResult.getPhotoInfo()))
                .toList()
                .map(response -> toLoadResult(pageIndex, response))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> new LoadResult.Error<>(throwable));
    }

    private LoadResult<Integer, ListImage> toLoadResult(int pageIndex, @NonNull List<ListImage> response) {

        return new LoadResult.Page<>(
                response,
                null,
                pageIndex + 1,
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }

    @Override
    public @Nullable Integer getRefreshKey(@NotNull PagingState<Integer, ListImage> pagingState) {

        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null)
            return null;

        LoadResult.Page<Integer, ListImage> anchorPage = pagingState.closestPageToPosition(anchorPosition);
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


/*
   @Override
    public @NotNull Single<LoadResult<Integer, FlickrImage>> loadSingle(@NotNull LoadParams<Integer> loadParams) {

        int perPage = loadParams.getLoadSize();
        int pageIndex = loadParams.getKey() == null ? FIRST_PAGE_INDEX : loadParams.getKey();

        //TODO ApiKEY
        return mApi.searchPhotos(USER_API_KEY, mQuery, pageIndex, perPage, "json", "1")
                .subscribeOn(Schedulers.io())
                .map(response -> toLoadResult(response))
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer, FlickrImage> toLoadResult(@NonNull FlickrImageSearchResult response) {

        return new LoadResult.Page<>(
                response.getPhotos().getPhotoList(),
                null,
                response.getPhotos().getPage() + 1,
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }
 */

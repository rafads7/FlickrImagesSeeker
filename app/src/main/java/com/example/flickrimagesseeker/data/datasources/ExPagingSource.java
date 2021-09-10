package com.example.flickrimagesseeker.data.datasources;

import androidx.paging.PagingSource;
import androidx.paging.PagingState;

import com.example.flickrimagesseeker.data.entities.FlickrImage;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.coroutines.Continuation;

public class ExPagingSource extends PagingSource<Integer, FlickrImage> {

    @Override
    public @Nullable Integer getRefreshKey(@NotNull PagingState<Integer, FlickrImage> pagingState) {
        return null;
    }

    @Override
    public @Nullable Object load(@NotNull LoadParams<Integer> loadParams, @NotNull Continuation<? super LoadResult<Integer, FlickrImage>> continuation) {
        return null;
    }
}

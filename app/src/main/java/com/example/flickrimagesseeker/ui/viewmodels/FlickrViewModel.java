package com.example.flickrimagesseeker.ui.viewmodels;

import com.example.flickrimagesseeker.api.entities.photos_search.FlickrImage;
import com.example.flickrimagesseeker.data.entities.ListImage;
import com.example.flickrimagesseeker.data.repositories.FlickrRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FlickrViewModel extends ViewModel {

    private FlickrRepository mRepository;
    private MutableLiveData<String> mQuery = new MutableLiveData<>();

    private @NotNull LiveData<PagingData<ListImage>> mImages = new MutableLiveData<>();

    @Inject
    public FlickrViewModel(FlickrRepository repository) {
        mRepository = repository;
    }

    public MutableLiveData<String> getQuery() {
        return mQuery;
    }

    public void setQuery(String query) {
        mQuery.setValue(query);
    }

    public @NotNull LiveData<PagingData<ListImage>> getImages() {
        //Flowable<PagingData<FlickrImage>> searchResult = mRepository.getSearchResult("cats");
        //PagingRx.cachedIn(searchResult, ViewModelKt.getViewModelScope(this));
        //return searchResult;
        ;

        mImages = PagingLiveData.cachedIn(mRepository.getSearchResult("cat"),
                ViewModelKt.getViewModelScope(this));
        return mImages;
    }

}

package com.example.flickrimagesseeker.ui.viewmodels;

import com.example.flickrimagesseeker.data.entities.FlickrImage;
import com.example.flickrimagesseeker.data.repositories.FlickrRepository;
import com.example.flickrimagesseeker.data.repositories.Repository;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import io.reactivex.rxjava3.core.Flowable;


public class FlickrViewModel extends ViewModel {

    private FlickrRepository mRepository;
    private MutableLiveData<String> mQuery = new MutableLiveData<>();

    @ViewModelInject
    public FlickrViewModel(FlickrRepository repository) {
        mRepository = repository;
    }

    public MutableLiveData<String> getQuery() {
        return mQuery;
    }

    public void setQuery(String query) {
        mQuery.setValue(query);
    }

    public Flowable<PagingData<FlickrImage>> getImages() {
        Flowable<PagingData<FlickrImage>> searchResult = mRepository.getSearchResult(getQuery().getValue());
        PagingRx.cachedIn(searchResult, ViewModelKt.getViewModelScope(this));
        return searchResult;
    }

}

package com.example.flickrimagesseeker.ui.viewmodels;

import androidx.hilt.Assisted;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import com.example.flickrimagesseeker.data.entities.ListImage;
import com.example.flickrimagesseeker.data.repositories.FlickrRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FlickrViewModel extends ViewModel {

    private final static String CURRENT_QUERY = "CURRENT_QUERY";
    private final FlickrRepository mRepository;
    private final SavedStateHandle mSavedStateHandle;

    private final LiveData<PagingData<ListImage>> mSearch;

    @Inject
    public FlickrViewModel(FlickrRepository repository, @Assisted SavedStateHandle state) {
        mRepository = repository;
        mSavedStateHandle = state;

        LiveData<String> queryLiveData = state.getLiveData(CURRENT_QUERY);
        mSearch = Transformations.switchMap(queryLiveData,
                query -> PagingLiveData.cachedIn(mRepository.getSearchResult(query),
                        ViewModelKt.getViewModelScope(FlickrViewModel.this)));
    }

    public LiveData<PagingData<ListImage>> getSearch() {
        return mSearch;
    }

    public void setQuery(String query) {
        mSavedStateHandle.set(CURRENT_QUERY, query);
    }
}

/*
With Flowable instead of ViewModel:
        Flowable<PagingData<FlickrImage>> searchResult = mRepository.getSearchResult("cats");
        PagingRx.cachedIn(searchResult, ViewModelKt.getViewModelScope(this));
*/


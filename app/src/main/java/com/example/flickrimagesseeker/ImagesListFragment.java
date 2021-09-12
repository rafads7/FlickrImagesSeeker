package com.example.flickrimagesseeker;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.flickrimagesseeker.databinding.FragmentImagesListBinding;
import com.example.flickrimagesseeker.ui.adapters.ImagesListAdapter;
import com.example.flickrimagesseeker.ui.viewmodels.FlickrViewModel;
import com.example.flickrimagesseeker.utils.DialogUtils;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * A fragment representing a list of Items. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a {@link ImageDetailFragment} representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */

@AndroidEntryPoint
public class ImagesListFragment extends Fragment {

    @Inject
    CompositeDisposable compositeDisposable;

    @Inject
    ImagesListAdapter mAdapter;

    private FlickrViewModel mViewModel;
    private FragmentImagesListBinding mDataBinding;
    protected SearchView mSearchView;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(FlickrViewModel.class);

        mDataBinding = FragmentImagesListBinding.inflate(inflater, container, false);
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDataBinding.itemList.setAdapter(mAdapter);

        /*
        compositeDisposable.add(mViewModel.getImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flickrImagePagingData -> mAdapter.submitData(getViewLifecycleOwner().getLifecycle(), flickrImagePagingData)
                        , t -> DialogUtils.showOneButtonDialog(requireActivity(), t.getMessage(), null)));

         */

        mViewModel.getImages().observe(getViewLifecycleOwner(), images -> mAdapter.submitData(getViewLifecycleOwner().getLifecycle(), images));

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.search);
        //searchMenuItem.setOnActionExpandListener(getMenuExpandListener(menu));
        SearchManager searchManager = (SearchManager) requireActivity().getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) searchMenuItem.getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(getOnQueryTextListener());
        //mSearchView.setSuggestionsAdapter(getSuggestionsAdapter());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDataBinding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    protected SearchView.OnQueryTextListener getOnQueryTextListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mViewModel.setQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        };
    }
}
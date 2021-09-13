package com.example.flickrimagesseeker.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.paging.LoadState;

import com.example.flickrimagesseeker.R;
import com.example.flickrimagesseeker.databinding.FragmentImagesListBinding;
import com.example.flickrimagesseeker.ui.adapters.ImagesListAdapter;
import com.example.flickrimagesseeker.ui.adapters.LoadStateAdapter;
import com.example.flickrimagesseeker.ui.viewmodels.FlickrViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import kotlin.Unit;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


@AndroidEntryPoint
public class ImagesListFragment extends Fragment {

    @Inject
    CompositeDisposable compositeDisposable;

    @Inject
    ImagesListAdapter mAdapter;

    private FlickrViewModel mViewModel;
    private FragmentImagesListBinding mDataBinding;
    private NavController mNavController;
    protected SearchView mSearchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        mViewModel = new ViewModelProvider(this).get(FlickrViewModel.class);
        mDataBinding = FragmentImagesListBinding.inflate(inflater, container, false);
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = NavHostFragment.findNavController(this);

        mAdapter.setListener(image -> mNavController.navigate(ImagesListFragmentDirections.showImageDetail(image)));
        mAdapter.addLoadStateListener(cls -> {
            LoadState ls = cls.getSource().getRefresh();
            mDataBinding.progressLayout.progress.setVisibility(ls instanceof LoadState.Loading ? VISIBLE : GONE);
            mDataBinding.itemList.setVisibility(ls instanceof LoadState.NotLoading ? VISIBLE : GONE);
            mDataBinding.progressLayout.errorText.setVisibility(ls instanceof LoadState.Error ? VISIBLE : GONE);
            mDataBinding.progressLayout.retry.setVisibility(ls instanceof LoadState.Error ? VISIBLE : GONE);

            if (ls instanceof LoadState.NotLoading && cls.getAppend().getEndOfPaginationReached() && mAdapter.getItemCount() < 1) {
                mDataBinding.itemList.setVisibility(GONE);
                mDataBinding.progressLayout.noData.setVisibility(VISIBLE);
            } else
                mDataBinding.progressLayout.noData.setVisibility(GONE);

            return Unit.INSTANCE;
        });

        LoadStateAdapter loadStateAdapter = new LoadStateAdapter((v) -> mAdapter.retry());
        mAdapter.withLoadStateFooter(loadStateAdapter);

        mDataBinding.itemList.setAdapter(mAdapter);
        mDataBinding.progressLayout.retry.setOnClickListener((v) -> mAdapter.retry());

        mViewModel.getSearch().observe(getViewLifecycleOwner(), images -> mAdapter.submitData(getViewLifecycleOwner().getLifecycle(), images));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) requireActivity().getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) searchMenuItem.getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(getOnQueryTextListener());
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
                if (query != null && !query.trim().isEmpty()) {
                    mDataBinding.itemList.scrollToPosition(0);
                    mViewModel.setQuery(query);
                    mSearchView.clearFocus();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        };
    }
}

/*
Whether working with Flowable instead of ViewModel:
        compositeDisposable.add(mViewModel.getImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flickrImagePagingData -> mAdapter.submitData(getViewLifecycleOwner().getLifecycle(), flickrImagePagingData)
                        , t -> DialogUtils.showOneButtonDialog(requireActivity(), t.getMessage(), null)));



*/
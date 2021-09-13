package com.example.flickrimagesseeker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.flickrimagesseeker.R;
import com.example.flickrimagesseeker.databinding.ZoomLayoutBinding;
import com.ortiz.touchview.TouchImageView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ZoomImageFragment extends Fragment {

    private ZoomLayoutBinding mDataBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.zoom_layout, container, false);
        mDataBinding.setLifecycleOwner(getViewLifecycleOwner());

        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String url = ZoomImageFragmentArgs.fromBundle(getArguments()).getUrl();
        Glide.with(this)
                .load(url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_error)
                .into(mDataBinding.image);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDataBinding = null;
    }
}
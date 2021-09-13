package com.example.flickrimagesseeker.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.flickrimagesseeker.R;
import com.example.flickrimagesseeker.data.entities.ListImage;
import com.example.flickrimagesseeker.databinding.FragmentImageDetailBinding;

import org.jetbrains.annotations.NotNull;

public class ImageDetailFragment extends Fragment {

    private FragmentImageDetailBinding mDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mDataBinding = FragmentImageDetailBinding.inflate(inflater, container, false);
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListImage image = ImageDetailFragmentArgs.fromBundle(getArguments()).getImage();
        mDataBinding.setImage(image);

        Glide.with(this)
                .load(image.getPhotoInfo().getUrl())
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
package com.example.flickrimagesseeker.presentation.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flickrimagesseeker.placeholder.PlaceholderContent;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImagesListAdapter extends RecyclerView.Adapter<ImagesListAdapter.ViewHolder> {

    private final List<PlaceholderContent.PlaceholderItem> mValues;
    private final View.OnClickListener mOnClickListener;
    private final View.OnContextClickListener mOnContextClickListener;

    public ImagesListAdapter(List<PlaceholderContent.PlaceholderItem> items,
                             View.OnClickListener onClickListener,
                             View.OnContextClickListener onContextClickListener) {
        mValues = items;
        mOnClickListener = onClickListener;
        mOnContextClickListener = onContextClickListener;
    }

    @Override
    public ImagesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(final ImagesListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
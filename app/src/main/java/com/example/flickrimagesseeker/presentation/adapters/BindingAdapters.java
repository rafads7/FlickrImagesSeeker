package com.example.flickrimagesseeker.presentation.adapters;

import android.widget.LinearLayout;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

public class BindingAdapters {

    @BindingAdapter("android:recycler_adapter")
    public static void setRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("android:itemDivider")
    public static void setRecyclerViewItemDivider(RecyclerView recyclerView, boolean addDivider){
        if (addDivider) {
            DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayout.VERTICAL);
            recyclerView.addItemDecoration(mDividerItemDecoration);
        }
    }
}

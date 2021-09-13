package com.example.flickrimagesseeker.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flickrimagesseeker.R;
import com.example.flickrimagesseeker.databinding.ListHeaderFooterLoadStateBinding;

import org.jetbrains.annotations.NotNull;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LoadStateAdapter extends androidx.paging.LoadStateAdapter<LoadStateAdapter.LoadStateViewHolder> {

    private final View.OnClickListener mListener;

    public LoadStateAdapter(@NotNull View.OnClickListener listener) {
        mListener = listener;
    }

    @Override
    public @NotNull LoadStateAdapter.LoadStateViewHolder onCreateViewHolder(@NotNull ViewGroup parent, @NotNull LoadState loadState) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ListHeaderFooterLoadStateBinding itemBinding = DataBindingUtil.inflate(inflater, R.layout.list_header_footer_load_state, parent, false);
        return new LoadStateAdapter.LoadStateViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NotNull LoadStateViewHolder loadStateViewHolder, @NotNull LoadState loadState) {
        loadStateViewHolder.bind(loadState);
    }

    public class LoadStateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ListHeaderFooterLoadStateBinding mBinding;

        public LoadStateViewHolder(@NonNull @NotNull ListHeaderFooterLoadStateBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.retry.setOnClickListener(this);
        }

        public void bind(LoadState loadState) {
            mBinding.progress.setVisibility(loadState instanceof LoadState.Loading ? VISIBLE : GONE);
            mBinding.retry.setVisibility(loadState instanceof LoadState.Loading ? GONE : VISIBLE);
            mBinding.error.setVisibility(loadState instanceof LoadState.Loading ? GONE : VISIBLE);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }
    }
}

package com.example.flickrimagesseeker.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.flickrimagesseeker.R;
import com.example.flickrimagesseeker.data.entities.FlickrImage;
import com.example.flickrimagesseeker.databinding.FragmentImagesListItemBinding;

import org.jetbrains.annotations.NotNull;

public class ImagesListAdapter extends PagingDataAdapter<FlickrImage, ImagesListAdapter.ViewHolder> {

    public ImagesListAdapter() {
        super(COMPARATOR);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FragmentImagesListItemBinding itemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_images_list_item, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ImagesListAdapter.ViewHolder holder, int position) {
        FlickrImage item = getItem(position);
        if (item != null)
            holder.bind(item);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private FragmentImagesListItemBinding itemBinding;

        public ViewHolder(FragmentImagesListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            itemView.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString();
        }

        public void bind(FlickrImage image) {
            Glide.with(itemView)
                    .load(image.getUrl()).centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(itemBinding.thumbnail);

            itemBinding.setImage(image);
        }

        @Override
        public void onClick(View view) {

            //mListener.onSelect(mantenList.get(getAdapterPosition()));
        }
    }

    final static DiffUtil.ItemCallback COMPARATOR = new DiffUtil.ItemCallback<FlickrImage>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull FlickrImage oldItem, @NonNull @NotNull FlickrImage newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull FlickrImage oldItem, @NonNull @NotNull FlickrImage newItem) {
            return oldItem.equals(newItem);
        }
    };


}
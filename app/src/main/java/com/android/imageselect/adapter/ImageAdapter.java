package com.android.imageselect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.imageselect.R;
import com.android.imageselect.utils.ImageLoadUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> imagePaths;
    private String parentFile;
    private Context mContext;

    public ImageAdapter (String parentFile, List<String> imagePaths) {
        this.imagePaths = imagePaths;
        this.parentFile = parentFile;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ImageLoadUtils.imageLoad(mContext, parentFile+"/"+imagePaths.get(position), viewHolder.mImageView);

    }

    @Override
    public int getItemCount() {
        return imagePaths.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}

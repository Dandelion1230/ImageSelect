package com.android.imageselect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.android.imageselect.R;
import com.android.imageselect.utils.ImageLoadUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */

public class SelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> mSelectPaths;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_select, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ImageLoadUtils.imageLoad(mContext, mSelectPaths.get(position), viewHolder.mImageView);
    }

    public void addItem(int position, List<String> mSelectPaths) {
        this.mSelectPaths = mSelectPaths;
        notifyItemInserted(position);

    }

    public void removedItem(int position, List<String> mSelectPaths) {
        this.mSelectPaths = mSelectPaths;
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mSelectPaths == null ? 0 : mSelectPaths.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private ImageView mDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.select_image);
//            mDelete = (ImageView) itemView.findViewById(R.id.image_translucence);
        }
    }

}

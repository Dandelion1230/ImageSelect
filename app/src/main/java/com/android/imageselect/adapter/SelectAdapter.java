package com.android.imageselect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.imageselect.R;
import com.android.imageselect.listener.OnDeleteClickListener;
import com.android.imageselect.listener.OnImageClickListener;
import com.android.imageselect.utils.ImageLoadUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */

public class SelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> mSelectPaths;
    private OnImageClickListener mOnImageClickListener;
    private OnDeleteClickListener mOnDeleteClickListener;
//    private int delPosition;

    public void setOnImageClickListener(OnImageClickListener mOnImageClickListener) {
        this.mOnImageClickListener = mOnImageClickListener;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener mOnDeleteClickListener) {
        this.mOnDeleteClickListener = mOnDeleteClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_select, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        this.delPosition = position;
        ViewHolder viewHolder = (ViewHolder) holder;
        ImageLoadUtils.imageLoad(mContext, mSelectPaths.get(position), viewHolder.mImageView);
        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnImageClickListener.onImageClick(mSelectPaths, position);
            }
        });
        viewHolder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnDeleteClickListener.onDelete(position, mSelectPaths.get(position));
//                mSelectPaths.remove(position);
//                notifyItemRemoved(position);
            }
        });
    }

    public void addItem(int position, List<String> mSelectPaths) {
        this.mSelectPaths = mSelectPaths;
        notifyItemInserted(position);

    }

    public void removedItem(int position, List<String> mSelectPaths) {
        this.mSelectPaths = mSelectPaths;
//        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void removedItem(int position) {
        mSelectPaths.remove(position);
//        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mSelectPaths == null ? 0 : mSelectPaths.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.select_image);
            mDelete = (TextView) itemView.findViewById(R.id.tv_delete);
        }
    }

}

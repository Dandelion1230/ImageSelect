package com.android.imageselect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.imageselect.ImageSelectActivity;
import com.android.imageselect.R;
import com.android.imageselect.utils.ImageBean;
import com.android.imageselect.utils.ImageLoadUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ImageBean> imagePaths;
    private Context mContext;
    private List<String> imageSelects = new ArrayList<>();
    private OnSelectClickListener mOnSelectClickListener;



    public ImageAdapter(List<ImageBean> imagePaths) {
        this.imagePaths = imagePaths;

    }

    public void setOnSelectClickListener(OnSelectClickListener mOnSelectClickListener) {
        this.mOnSelectClickListener = mOnSelectClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        ImageLoadUtils.imageLoad(mContext, imagePaths.get(position).getImagePath(), viewHolder.mImageView);
        viewHolder.mCbSelect.setOnCheckedChangeListener(null);
        viewHolder.mCbSelect.setChecked(imagePaths.get(position).isSelect());
        if (imagePaths.get(position).isSelect()) {
            viewHolder.mTranslucence.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mTranslucence.setVisibility(View.GONE);
        }
        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "图片点击", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.mCbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (imageSelects.size() >= 9  && isChecked) {
                    buttonView.setChecked(false);
                    Toast.makeText(mContext, "最多只能选9张图片哦", Toast.LENGTH_SHORT).show();
                } else {
                    imagePaths.get(position).setSelect(isChecked);
                    if (isChecked) {
                        viewHolder.mTranslucence.setVisibility(View.VISIBLE);
                        imageSelects.add(imagePaths.get(position).getImagePath());
                        mOnSelectClickListener.onAddItem(imageSelects.size()-1, imageSelects);
                    } else {
                        viewHolder.mTranslucence.setVisibility(View.GONE);
                        int removedPosition = imageSelects.indexOf(imagePaths.get(position).getImagePath());
                        imageSelects.remove(imagePaths.get(position).getImagePath());
                        mOnSelectClickListener.onRemoved(removedPosition, imageSelects);
                    }
                }

//                if (mOnSelectClickListener != null) {
//
//                }
//                    mOnSelectClickListener.onSelectClick(imageSelects);

//                Toast.makeText(mContext, "isChecked"+isChecked, Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return imagePaths.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private CheckBox mCbSelect;
        private ImageView mTranslucence;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image);
            mTranslucence = (ImageView) itemView.findViewById(R.id.image_translucence);
            mCbSelect = (CheckBox) itemView.findViewById(R.id.cb_select);
        }
    }

    public interface OnSelectClickListener {
//        void onSelectClick(List<String> selectImages);
        void onAddItem(int position, List<String> selectImages);
        void onRemoved(int position, List<String> selectImages);
    }
}

package com.android.imageselect.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ImageLoadUtils {
    public static void imageLoad(Context mContext, String imagePath, ImageView mImageView) {
        Glide.with(mContext).load(imagePath).fitCenter().into(mImageView);
    }
}

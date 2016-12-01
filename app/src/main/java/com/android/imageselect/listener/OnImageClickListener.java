package com.android.imageselect.listener;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */

public interface OnImageClickListener<T> {
    void onImageClick(List<T> mImageDatas, int position);
}

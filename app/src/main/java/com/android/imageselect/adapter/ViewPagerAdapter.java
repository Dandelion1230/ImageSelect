package com.android.imageselect.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.imageselect.utils.ImageLoadUtils;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2016/12/1.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<String> mListData;

    public ViewPagerAdapter (List<String> mListData) {
        this.mListData = mListData;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView mPhotoView = new PhotoView(container.getContext());
        mPhotoView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoadUtils.imageLoad(container.getContext(), mListData.get(position), mPhotoView);
        container.addView(mPhotoView);
        return mPhotoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}

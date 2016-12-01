package com.android.imageselect.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * Created by Administrator on 2016/12/1.
 */

public class CusterViewPager extends ViewPager {

    public CusterViewPager(Context context) {
        super(context);
    }

    public CusterViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return false;
    }
}


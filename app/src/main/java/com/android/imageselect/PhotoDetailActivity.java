package com.android.imageselect;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.imageselect.adapter.ViewPagerAdapter;
import com.android.imageselect.utils.CusterViewPager;
import com.android.imageselect.utils.ImageBean;

import java.io.Serializable;
import java.util.List;

public class PhotoDetailActivity extends AppCompatActivity {

    private CusterViewPager mViewPager;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        mViewPager = (CusterViewPager) findViewById(R.id.view_pager);
        int position = getIntent().getIntExtra("position", -1);
        List<String> imageDatas = (List<String>) getIntent().getSerializableExtra("ImageData");
        mAdapter = new ViewPagerAdapter(imageDatas);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(position);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

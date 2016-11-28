package com.android.imageselect;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import com.android.imageselect.adapter.ImageAdapter;
import com.android.imageselect.utils.ImageScanningUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageSelectActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    /**
     * 所有的图片
     */
    private List<String> imagePahts;

    private ImageAdapter mAdapter;
    private String parentFile;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x110) {
                parentFile = utils.mImgDir.toString();
                imagePahts = Arrays.asList(utils.mImgDir.list());
//                StringBuffer buffer = new StringBuffer();
//                for (String imagePath:mImgs) {
//                    buffer.append(imagePath);
//                }
//                imagePaths.setText(buffer);
                initView();
            }
        }
    };



    private ImageScanningUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_select);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        initData();
    }

    private void initData() {
        utils = new ImageScanningUtils();
        utils.getImages(this, handler);

    }

    private void initView() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new ImageAdapter(parentFile, imagePahts);
        mRecyclerView.setAdapter(mAdapter);
    }
}

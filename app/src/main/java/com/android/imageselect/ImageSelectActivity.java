package com.android.imageselect;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.imageselect.adapter.ImageAdapter;
import com.android.imageselect.adapter.SelectAdapter;
import com.android.imageselect.listener.OnDeleteClickListener;
import com.android.imageselect.listener.OnImageClickListener;
import com.android.imageselect.utils.DividerGridItemDecoration;
import com.android.imageselect.utils.ImageBean;
import com.android.imageselect.utils.ImageScanningUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageSelectActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView mSelectRecyclerView;
    private Button mConfrim;
    /**
     * 所有的图片
     */
    private List<String> imagePahts;
    private List<ImageBean> imageBeans;

    private ImageAdapter mAdapter;
    private SelectAdapter mSelectAdapter;
    private String parentFile;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x110) {
                if (utils.mImgDir == null) {
                    Toast.makeText(ImageSelectActivity.this, "一张图片都没有哦", Toast.LENGTH_SHORT).show();
                    return;
                }
                parentFile = utils.mImgDir.toString();
                imagePahts = Arrays.asList(utils.mImgDir.list());
                imageBeans = new ArrayList<>();
                for (String imagePath:imagePahts) {
                    ImageBean bean = new ImageBean();
                    bean.setImagePath(parentFile+"/"+imagePath);
                    bean.setSelect(false);
                    imageBeans.add(bean);
                }
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
        mSelectRecyclerView = (RecyclerView) findViewById(R.id.select_recycler);
        mConfrim = (Button) findViewById(R.id.btn_confirm);
        mSelectAdapter = new SelectAdapter();
        initData();
    }

    private void initData() {
        utils = new ImageScanningUtils();
        utils.getImages(this, handler);

    }

    private void initView() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(ImageSelectActivity.this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ImageAdapter(imageBeans);
        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager1 = new LinearLayoutManager(ImageSelectActivity.this);
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mSelectRecyclerView.setLayoutManager(manager1);
        mSelectRecyclerView.setHasFixedSize(true);
        // 设置item动画
        mSelectRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSelectRecyclerView.setAdapter(mSelectAdapter);
        setListener();

    }

    private void setListener() {
        mAdapter.setOnSelectClickListener(new ImageAdapter.OnSelectClickListener() {
            @Override
            public void onAddItem(int position, List<String> selectImages) {
                mSelectAdapter.addItem(position, selectImages);
                mSelectRecyclerView.smoothScrollToPosition(mSelectAdapter.getItemCount());
                mConfrim.setText("确认"+selectImages.size()+"/9");
            }

            @Override
            public void onRemoved(int position, List<String> selectImages) {
                mSelectAdapter.removedItem(position, selectImages);
                mConfrim.setText("确认"+selectImages.size()+"/9");
            }
        });
        mAdapter.setOnImageClickListener(new OnImageClickListener<ImageBean>() {
            @Override
            public void onImageClick(List<ImageBean> mImageDatas, int position) {
                Intent intent = new Intent(ImageSelectActivity.this, PhotoDetailActivity.class);
                List<String> imageDatas = new ArrayList<>();
                for (int i = 0; i < mImageDatas.size(); i++) {
                    imageDatas.add(mImageDatas.get(i).getImagePath());
                }
                intent.putStringArrayListExtra("ImageData", (ArrayList<String>) imageDatas);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        mSelectAdapter.setOnImageClickListener(new OnImageClickListener<String>() {
            @Override
            public void onImageClick(List<String> mImageDatas, int position) {
                Intent intent = new Intent(ImageSelectActivity.this, PhotoDetailActivity.class);
                intent.putStringArrayListExtra("ImageData", (ArrayList<String>)mImageDatas);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        mSelectAdapter.setOnDeleteClickListener(new OnDeleteClickListener() {
            @Override
            public void onDelete(int position, String selectPath) {
                mSelectAdapter.removedItem(position);
                ImageBean bean = new ImageBean();
                bean.setSelect(true);
                bean.setImagePath(selectPath);
                int index = imageBeans.indexOf(bean);
                imageBeans.get(index).setSelect(false);
                mAdapter.notifyItemChanged(index);
                mConfrim.setText("确认"+mSelectAdapter.getItemCount()+"/9");
            }
        });
    }
}

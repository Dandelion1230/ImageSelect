package com.android.imageselect.utils;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ImageFloder {
    private String fileName; // 文件夹名
    private String firstImagePath; // 第一张图片路径
    private String filePath; // 文件夹路劲
    private int imageCount; // 图片数量

    public String getFileName() {
        return fileName;
    }

    public String getFirstImagePath() {
        return firstImagePath;
    }

    public void setFirstImagePath(String firstImagePath) {
        this.firstImagePath = firstImagePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        int lastIndexOf = this.filePath.lastIndexOf("/"); // 根据文件夹路径获取文件夹名
        this.fileName = this.filePath.substring(lastIndexOf);
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }
}

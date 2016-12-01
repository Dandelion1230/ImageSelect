package com.android.imageselect.utils;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/29.
 */

public class ImageBean implements Serializable {
    private String imagePath;
    private boolean isSelect;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageBean bean = (ImageBean) o;

        if (isSelect != bean.isSelect) return false;
        return imagePath != null ? imagePath.equals(bean.imagePath) : bean.imagePath == null;

    }

    @Override
    public int hashCode() {
        int result = imagePath != null ? imagePath.hashCode() : 0;
        result = 31 * result + (isSelect ? 1 : 0);
        return result;
    }
}

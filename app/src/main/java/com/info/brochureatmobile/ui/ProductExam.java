package com.info.brochureatmobile.ui;

public class ProductExam {

    int images;
    String tv_name;

    public ProductExam(int images, String tv_name) {
        this.images = images;
        this.tv_name = tv_name;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getTv_name() {
        return tv_name;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }
}

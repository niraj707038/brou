package com.info.brochureatmobile.Model;

import java.util.ArrayList;

public class ProductModel {

    public String message;
    public String statu;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public ArrayList<Data> data;

    public static class Data {
        public String id;
        public String productname;
        public String productImage;
        public String url;
        public String feature_image;
        public String feature_description;
        public String image_Url;
        public String productDetailUrl;
    }
}

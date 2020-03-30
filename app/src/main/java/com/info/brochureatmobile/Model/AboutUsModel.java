package com.info.brochureatmobile.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class AboutUsModel {

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

    public static class Data implements Serializable {
        public String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBannerImg() {
            return bannerImg;
        }

        public void setBannerImg(String bannerImg) {
            this.bannerImg = bannerImg;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFeature_image() {
            return feature_image;
        }

        public void setFeature_image(String feature_image) {
            this.feature_image = feature_image;
        }

        public String parent_id;
        public String title;
        public String bannerImg;
        public String content;
        public String feature_image;


    }
}

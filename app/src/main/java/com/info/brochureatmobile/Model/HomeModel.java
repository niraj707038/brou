package com.info.brochureatmobile.Model;

import java.util.ArrayList;

public class HomeModel {

    public String message;
    public int statu;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    public ArrayList<Details> details;

    public static class Details {
        public String typeid;
        public String title;
        public String status;
        public String id;
        public String banner_image;
        public boolean check;
        public String favcolor;
    }
}

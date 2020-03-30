package com.info.brochureatmobile.Model;

import java.util.ArrayList;

public class CertificationModel {

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

    public ArrayList<Data> data;

    public static class Data{

        String id;
        String type_id;
        public String name;
        String description;
        public String image;
        String create_date;
        String is_active;
    }
}

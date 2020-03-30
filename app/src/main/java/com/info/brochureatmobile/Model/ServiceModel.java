package com.info.brochureatmobile.Model;

import java.util.ArrayList;

public class ServiceModel {

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

    public static class Data {

        public String id;
        public String type_id;
        public String name;
        public String description;
        public String image;
        public String create_date;
        public String is_active;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;

        }
    }
}

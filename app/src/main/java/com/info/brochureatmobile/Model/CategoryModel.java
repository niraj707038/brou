package com.info.brochureatmobile.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryModel implements Serializable {

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
        public String feature_image;
        public String categoryname;
        public ArrayList<Subcategory> subcategoey_data;

        public static class Subcategory implements Serializable {
            public String subcategory_id;
            public String sub_categoryname;
            public String feature_image;
            public String feature_description;
        }

    }
}

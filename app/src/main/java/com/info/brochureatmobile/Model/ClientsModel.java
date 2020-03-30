package com.info.brochureatmobile.Model;

import java.util.ArrayList;

public class ClientsModel {

    String message;
    String statu;

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

    public static class Data{

       public String name;
       public String clientcategoryname;
       public String logo;
       public String description;
    }
}

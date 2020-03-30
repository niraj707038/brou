package com.info.brochureatmobile.Model;

import java.util.ArrayList;

public class RepresentativeModel {
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

    public RepresentativeModel data;

    public ArrayList<OurRepresentative> ourrepresentative;

    public static class OurRepresentative {

        public String name;
        public String company_name;
        public String email;
        public String address;
        public String office_phone1;
        public String office_phone2;
        public String mobile;
        public String territory;
        public String fax;
        public String lat;
        public String lng;
        public String create_date;
    }
}

package com.info.brochureatmobile.Model;

import java.util.ArrayList;

public class NewsModel {

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

    public Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {

        public ArrayList<News> news_data;


        public static class News {
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            private String name;
            public String image;
            private String from_date;


            public String getFrom_date() {
                return from_date;
            }

            public void setFrom_date(String from_date) {
                this.from_date = from_date;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;

            }

            private String description;

        }

        public ArrayList<Event> event_data;


        public static class Event {
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            private String name;
            private String image;
            private String from_date;
            private String to_date;


            public String getFrom_date() {
                return from_date;
            }

            public void setFrom_date(String from_date) {
                this.from_date = from_date;
            }

            public String getTo_date() {
                return to_date;
            }

            public void setTo_date(String to_date) {
                this.to_date = to_date;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;

            }

            private String description;
        }
    }
}

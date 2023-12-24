package com.example.fbasevedio;

import java.util.List;

public class ThingSpeakData {
    private List<Feed> feeds;

    public List<Feed> getFeeds(){
        return  feeds;

    }
    public  class Feed{
        private String create_at;
        private  int entry_id;
        private String field1;
        private String field2;

        public String getCreate_at() {
            return create_at;
        }

        public int getEntry_id() {
            return entry_id;
        }

        public String getField1() {
            return field1;
        }

        public String getField2() {
            return field2;
        }
    }
}

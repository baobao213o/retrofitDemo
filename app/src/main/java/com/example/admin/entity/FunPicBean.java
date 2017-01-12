package com.example.admin.entity;

import java.util.List;

/**
 * Created by Admin on 2016/12/9.
 */

public class FunPicBean {

    private List<Data> data;

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return this.data;
    }


    public class Data {
        private String content;

        private String hashId;

        private int unixtime;

        private String updatetime;

        private String url;

        public void setContent(String content){
            this.content = content;
        }
        public String getContent(){
            return this.content;
        }
        public void setHashId(String hashId){
            this.hashId = hashId;
        }
        public String getHashId(){
            return this.hashId;
        }
        public void setUnixtime(int unixtime){
            this.unixtime = unixtime;
        }
        public int getUnixtime(){
            return this.unixtime;
        }
        public void setUpdatetime(String updatetime){
            this.updatetime = updatetime;
        }
        public String getUpdatetime(){
            return this.updatetime;
        }
        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
    }
}

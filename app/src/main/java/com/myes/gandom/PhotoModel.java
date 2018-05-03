package com.myes.gandom;

public class PhotoModel {

    private String mTitle;
    private String url;

    PhotoModel(String title, String url) {
        mTitle = title;
        this.url = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return url;
    }
}

package com.myes.gandom;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model {
    @SerializedName("description")
    private String description;

    @SerializedName("images")
    private List<String> photoList;

    public String getDescription() {
        return description;
    }

    public List<String> getPhotoList() {
        return photoList;
    }
}

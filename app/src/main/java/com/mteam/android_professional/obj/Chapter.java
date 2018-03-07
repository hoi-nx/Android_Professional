package com.mteam.android_professional.obj;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Stealer Of Souls on 2/19/2018.
 */

public class Chapter implements Serializable {
    @SerializedName("idChapter")
    private int idChapter;
    @SerializedName("name_title")
    private String nameChapter;
    @SerializedName("description")
    private String discription;
    @SerializedName("link_img")
    private String imgChapter;
    @SerializedName("motivation")
    private String motivation;

    public Chapter(int idChapter, String nameChapter, String discription, String imgChapter, String motivation) {
        this.idChapter = idChapter;
        this.nameChapter = nameChapter;
        this.discription = discription;
        this.imgChapter = imgChapter;
        this.motivation = motivation;
    }

    public Chapter() {

    }

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }

    public String getNameChapter() {
        return nameChapter;
    }

    public void setNameChapter(String nameChapter) {
        this.nameChapter = nameChapter;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getImgChapter() {
        return imgChapter;
    }

    public void setImgChapter(String imgChapter) {
        this.imgChapter = imgChapter;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Chapter.class);
    }
}

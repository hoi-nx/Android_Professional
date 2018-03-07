package com.mteam.android_professional.obj;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Stealer Of Souls on 2/20/2018.
 */

public class Lesson implements Serializable {

    /**
     * idChapter : 1
     * idLesson : 1
     * nameLesson : Bài 1: Tổng Quan Về Android
     * description : Tổng quát về Android,lịch sử phát triển,kiến trúc android.....
     * key : true
     */
    @SerializedName("idChapter")
    private int idChapter;
    @SerializedName("idLesson")
    private int idLesson;
    @SerializedName("nameLesson")
    private String nameLesson;
    @SerializedName("description")
    private String description;
    @SerializedName("key")
    private boolean key;

    public Lesson(int idChapter, int idLesson, String nameLesson, String description, boolean key) {
        this.idChapter = idChapter;
        this.idLesson = idLesson;
        this.nameLesson = nameLesson;
        this.description = description;
        this.key = key;
    }

    public Lesson() {
    }

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }

    public int getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public String getNameLesson() {
        return nameLesson;
    }

    public void setNameLesson(String nameLesson) {
        this.nameLesson = nameLesson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }
}

package com.mteam.android_professional;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mteam.android_professional.obj.Chapter;
import com.mteam.android_professional.obj.Lesson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stealer Of Souls on 2/19/2018.
 */

public class Utils {
    public static String readFileFromAssets(String fileName, Context context) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets().open(fileName, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }

    public static List<Lesson> getLessonList(Chapter chapter,Context context) {

        if (chapter != null) {
            int idChapter = chapter.getIdChapter();
            String jsonString = Utils.readFileFromAssets("data/lesson_android_c" + idChapter + ".txt", context);
            if (!jsonString.isEmpty() && jsonString != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<Lesson>>() {
                }.getType();
                return gson.fromJson(jsonString, type);
            }

        }
        return null;
    }
}

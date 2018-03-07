package com.mteam.android_professional.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.mteam.android_professional.Contants;
import com.mteam.android_professional.obj.Chapter;
import com.mteam.android_professional.obj.Lesson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stealer Of Souls on 2/4/2018.
 */

public class ManagerDataSQLite extends SQLiteOpenHelper {
    private Context context;
    private static ManagerDataSQLite mInstance =null;
    private static final String DATA_NAME = "BachKhoaToanThu.sqlite";
    private SQLiteDatabase managerSql;
    static final int DATABASE_VERSION = 3;

    public ManagerDataSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

//    public ManagerDataSQLite(Context context) {
//        super(context, DATA_NAME, null, DATABASE_VERSION);
//        this.context=context;
//    }


    public static synchronized ManagerDataSQLite getIntance(Context context) {
        if (mInstance == null) {
            mInstance = new ManagerDataSQLite(context,DATA_NAME,null,DATABASE_VERSION);

        }
        return mInstance;
    }




    public String getPathRoot(Context context) {
        return Environment.getDataDirectory() +
                File.separator + "data" + File.separator + context.getPackageName() + File.separator + "database" + File.separator + DATA_NAME;
    }


    private void openDatabase(Context context) {
        if (managerSql == null || !managerSql.isOpen()) {
            managerSql = SQLiteDatabase.openDatabase(getPathRoot(context), null, SQLiteDatabase.OPEN_READWRITE);
        }

    }

    private void closeDatabse() {
        if (managerSql != null && managerSql.isOpen()) {
            managerSql.close();
        }
    }

    public void inits(Context context) {
        File file = new File(getPathRoot(context));
        if (file.exists()) {
            return;
        }
        file.getParentFile().mkdir();
        try {
            InputStream in = context.getAssets().open(DATA_NAME);
            OutputStream out = new FileOutputStream(file);
            byte b[] = new byte[1024];
            int le = in.read(b);
            while (le >= 0) {
                out.write(b, 0, le);
                le = in.read(b);
            }
            Toast.makeText(context, "Copy thành công", Toast.LENGTH_LONG).show();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Chapter> getListChapter(Context context) {
        openDatabase(context);
        List<Chapter> list = new ArrayList<>();

        String sql = "SELECT * FROM Chapter";
        String[] sle = new String[]{
                "level", "id", "ra"
        };
        Cursor cursor = managerSql.rawQuery(sql, null);
        if (cursor == null) {
            return null;
        }
        while (cursor.moveToNext()) {
            int idChapter = cursor.getInt(0);
            String nameChapter = cursor.getString(1);
            String discription = cursor.getString(2);
            byte[] img = cursor.getBlob(3);
            String motivation = cursor.getString(4);
          //  list.add(new Chapter(idChapter, nameChapter, discription, img, motivation));


        }

        closeDatabse();
        return list;
    }

    public List<Lesson> getListLesson(int idChapter, Context context) {
        openDatabase(context);
        List<Lesson> lessonList = new ArrayList<>();
        String sql = "SELECT * FROM Lesson WHERE idChapter=" + idChapter;

        Cursor cursor = managerSql.rawQuery(sql, null);
        if (cursor == null) {
            return null;
        }
        while (cursor.moveToNext()) {
            int idLesson = cursor.getInt(0);
            int iDChapter = cursor.getInt(1);
            String nameLesson = cursor.getString(2);
            //lessonList.add(new Lesson2(idLesson, iDChapter, nameLesson));



        }
        closeDatabse();
        return lessonList;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        inits(context);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        Log.d("", "onUpgrade: "+oldVersion+"====="+newVersion);
//        if(newVersion!=oldVersion){
//            context.deleteDatabase(Contants.DATA_NAME);
//            File file=context.getDatabasePath(Contants.DATA_NAME);
//            file.delete();
//            inits(context);
//            db.needUpgrade(2);
//            close();
//
//        }

    }

}

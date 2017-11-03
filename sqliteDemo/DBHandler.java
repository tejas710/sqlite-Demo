package com.example.dell.sqllightdemo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dell.sqllightdemo.Activity.Appinfo;

import java.util.ArrayList;

/**
 * Created by Tej710 on 06-01-2017.
 */

public class DBHandler extends SQLiteOpenHelper{
    private static final String TABLE_NAME = "application";  // tablename
    private static final String APP_NAME = "appname";  // column name
    private static final String APP_ID = "ID";  // auto generated ID column
    private static final String DATABASE_NAME = "APPINFO"; // Dtabasename
    private static final int VERSION_CODE = 1; //versioncode of the database

    public  DBHandler(Context context)
    {
        super(context,DATABASE_NAME,null,VERSION_CODE);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "";
        query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " + APP_ID + " INTEGER PRIMARY KEY, " + APP_NAME + " TEXT ) ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryy = "";
        queryy = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(queryy);
        onCreate(db);
    }
    
    //getallApplication
    public ArrayList<Appinfo> getAllApplication()
    {
        ArrayList<Appinfo> appList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("select * from " + TABLE_NAME,null);
        cursor.moveToFirst();


        while (cursor.isAfterLast()==false)
        {
            Appinfo appinfo = new Appinfo(Integer.parseInt(cursor.getString(0)),cursor.getString(cursor.getColumnIndex(APP_NAME)));
            Log.i("id1",cursor.getString(0));
            appList.add(appinfo);

            cursor.moveToNext();
        }
        return appList;
    }


    //Add Application in database
    public void addApp(Appinfo appinfo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(APP_ID, appinfo.getID());
        contentValues.put(APP_NAME, appinfo.getAPP_NAME());

        db.insert(TABLE_NAME , null , contentValues);

        db.close();
    }

    //Update Database
    public void updateApp(Appinfo appinfo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(APP_NAME, appinfo.getAPP_NAME());
        db.update(TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(appinfo.getID()) } );


    }
    public void deleteApp (Appinfo appinfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(appinfo.getID()) });
    }

}

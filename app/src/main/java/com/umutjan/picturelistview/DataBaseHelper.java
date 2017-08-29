package com.umutjan.picturelistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by umutj_000 on 2017-08-25.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DataBaseHelper";

    private static final String TABLE_NAME = "products";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "rating";
    private static final String COL4 = "image";


    public DataBaseHelper(Context context) {

        super(context, TABLE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " + COL3 + " INT, " + COL4 + " BLOB)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, int rating, byte[] image) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //ContentValues addRating = new ContentValues();

        contentValues.put(COL2, item);
        contentValues.put(COL3, rating);
        contentValues.put(COL4, image);

        logMessage("the item we inserted is " + item.toString());

        //contentValues.put(COL3, rating);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);
        //long ratingResult = db.insert(TABLE_NAME, null, addRating);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    /*
    public boolean addDataWithoutImage(String item, int rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, item);
        contentValues.put(COL3, rating);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    */

    public String getTableName() {
        return TABLE_NAME;
    }

    public void deleteAll() {
        logMessage("We're at least here");

        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        db.execSQL("DELETE FROM " + TABLE_NAME);

    }

    public Cursor getData() {

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;

    }

    public int getDataBaseCount() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        int count = data.getColumnCount();
        return count;
    }

    public Cursor getItemID(String name) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;

    }

    public void updateName(String newName, int id, String oldName) {

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);

    }

    public void deleteName(int id, String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL1 +
                " = '" + id + "'" + " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database");
        db.execSQL(query);

    }

    public void logMessage(String message) {

        Log.i("InDataBase", message);

    }

}

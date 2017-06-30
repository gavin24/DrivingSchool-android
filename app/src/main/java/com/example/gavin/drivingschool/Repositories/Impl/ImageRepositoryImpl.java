package com.example.gavin.drivingschool.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gavin.drivingschool.Config.DbConstants.DatabaseHelper;
import com.example.gavin.drivingschool.Config.DbConstants.DatabaseManager;

import com.example.gavin.drivingschool.Domain.Image;
import com.example.gavin.drivingschool.Repositories.ImageRepository;
import com.example.gavin.drivingschool.Repositories.ImageRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public class ImageRepositoryImpl implements ImageRepository {
    public static final String TABLE_IMAGE = "image";

    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_IMAGE_ID = "id";
    public static final String COLUMN_IMAGE_IMAGE = "image";
    public static final String COLUMN_IMAGE_NAME = "name";
    public static final String COLUMN_IMAGE_USERID = "userid";






    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_IMAGE + "("
            + COLUMN_IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_IMAGE_NAME + " TEXT NULL , "
            + COLUMN_IMAGE_IMAGE + " BLOB NULL , "
            + COLUMN_IMAGE_USERID + " INTEGER  NULL );";



   /* public ImageRepositoryImpl(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    public ImageRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }*/
  /*  public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }
*/
    @Override
    public Image findById(Long id) {

         SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_IMAGE,
                new String[]{
                        COLUMN_IMAGE_ID,
                        COLUMN_IMAGE_NAME,
                        COLUMN_IMAGE_IMAGE,
                        COLUMN_IMAGE_USERID},

                COLUMN_IMAGE_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Image animal = new Image.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_IMAGE_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_NAME)))
                    .image(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE_IMAGE)))
                    .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_IMAGE_USERID)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }



    @Override
    public Image save(Image entity) {
        SQLiteDatabase db =     DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
   //     values.put(COLUMN_IMAGE_ID, entity.getId());
        values.put(COLUMN_IMAGE_NAME, entity.getName());
        values.put(COLUMN_IMAGE_IMAGE, entity.getImage());
        values.put(COLUMN_IMAGE_USERID, entity.getuserId());
        long id = db.insertOrThrow(TABLE_IMAGE, null, values);

        Image insertedEntity = new Image.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public Image update(Image entity) {
        SQLiteDatabase db =    DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE_ID, entity.getId());
        values.put(COLUMN_IMAGE_NAME, entity.getName());
        values.put(COLUMN_IMAGE_IMAGE, entity.getImage());
        values.put(COLUMN_IMAGE_USERID, entity.getuserId());
        db.update(
                TABLE_IMAGE,
                values,
                COLUMN_IMAGE_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        DatabaseManager.getInstance().closeDatabase();
        return entity;
    }

    @Override
    public Image delete(Image entity) {
        SQLiteDatabase db =     DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_IMAGE,
                COLUMN_IMAGE_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        DatabaseManager.getInstance().closeDatabase();
        return entity;
    }

    @Override
    public ArrayList<Image> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Image> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_IMAGE, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final Image animals = new Image.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_IMAGE_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_NAME)))
                        .image(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE_IMAGE)))
                        .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_IMAGE_USERID)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        DatabaseManager.getInstance().closeDatabase();
        return animal;
    }

    @Override
    public int deleteAll() {
        SQLiteDatabase db =   DatabaseManager.getInstance().openDatabase();
        int rowsDeleted = db.delete(TABLE_IMAGE,null,null);
        DatabaseManager.getInstance().closeDatabase();
        return rowsDeleted;
    }

 /*   @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE);
        onCreate(db);

    } */
}

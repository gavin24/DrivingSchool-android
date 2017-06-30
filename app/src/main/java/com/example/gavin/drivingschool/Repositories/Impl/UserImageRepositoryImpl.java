package com.example.gavin.drivingschool.Repositories.Impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gavin.drivingschool.Config.DbConstants.DatabaseHelper;
import com.example.gavin.drivingschool.Config.DbConstants.DatabaseManager;
import com.example.gavin.drivingschool.Domain.Image;
import com.example.gavin.drivingschool.Domain.UserImage;
import com.example.gavin.drivingschool.Repositories.ImageRepository;
import com.example.gavin.drivingschool.Repositories.UserImageRepository;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class UserImageRepositoryImpl implements UserImageRepository {
    public static final String TABLE_USERIMAGE = "userimage";

    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_USERIMAGE_ID = "id";
    public static final String COLUMN_USERIMAGE_IMAGE = "image";
    public static final String COLUMN_USERIMAGE_NAME = "name";
    public static final String COLUMN_USERIMAGE_USERID = "userid";






    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_USERIMAGE + "("
            + COLUMN_USERIMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERIMAGE_NAME + " TEXT NULL , "
            + COLUMN_USERIMAGE_IMAGE + " BLOB NULL , "
            + COLUMN_USERIMAGE_USERID + " INTEGER  NULL );";



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
    public UserImage findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERIMAGE,
                new String[]{
                        COLUMN_USERIMAGE_ID,
                        COLUMN_USERIMAGE_NAME,
                        COLUMN_USERIMAGE_IMAGE,
                        COLUMN_USERIMAGE_USERID},

                COLUMN_USERIMAGE_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final UserImage animal = new UserImage.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_USERIMAGE_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_USERIMAGE_NAME)))
                    .image(cursor.getBlob(cursor.getColumnIndex(COLUMN_USERIMAGE_IMAGE)))
                    .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERIMAGE_USERID)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }

    @Override
    public UserImage findByUserId(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERIMAGE,
                new String[]{
                        COLUMN_USERIMAGE_ID,
                        COLUMN_USERIMAGE_NAME,
                        COLUMN_USERIMAGE_IMAGE,
                        COLUMN_USERIMAGE_USERID},

                COLUMN_USERIMAGE_USERID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final UserImage animal = new UserImage.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_USERIMAGE_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_USERIMAGE_NAME)))
                    .image(cursor.getBlob(cursor.getColumnIndex(COLUMN_USERIMAGE_IMAGE)))
                    .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERIMAGE_USERID)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }


    @Override
    public UserImage save(UserImage entity) {
        SQLiteDatabase db =     DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //     values.put(COLUMN_IMAGE_ID, entity.getId());
        values.put(COLUMN_USERIMAGE_NAME, entity.getName());
        values.put(COLUMN_USERIMAGE_IMAGE, entity.getImage());
        values.put(COLUMN_USERIMAGE_USERID, entity.getuserId());
        long id = db.insertOrThrow(TABLE_USERIMAGE, null, values);

        UserImage insertedEntity = new UserImage.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public UserImage update(UserImage entity) {
        SQLiteDatabase db =    DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERIMAGE_ID, entity.getId());
        values.put(COLUMN_USERIMAGE_NAME, entity.getName());
        values.put(COLUMN_USERIMAGE_IMAGE, entity.getImage());
        values.put(COLUMN_USERIMAGE_USERID, entity.getuserId());
        db.update(
                TABLE_USERIMAGE,
                values,
                COLUMN_USERIMAGE_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        DatabaseManager.getInstance().closeDatabase();
        return entity;
    }

    @Override
    public UserImage delete(UserImage entity) {
        SQLiteDatabase db =     DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_USERIMAGE,
                COLUMN_USERIMAGE_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        DatabaseManager.getInstance().closeDatabase();
        return entity;
    }

    @Override
    public ArrayList<UserImage> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<UserImage> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_USERIMAGE, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final UserImage animals = new UserImage.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_USERIMAGE_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_USERIMAGE_NAME)))
                        .image(cursor.getBlob(cursor.getColumnIndex(COLUMN_USERIMAGE_IMAGE)))
                        .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERIMAGE_USERID)))
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
        int rowsDeleted = db.delete(TABLE_USERIMAGE,null,null);
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

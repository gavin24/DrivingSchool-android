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

import com.example.gavin.drivingschool.Domain.User;
import com.example.gavin.drivingschool.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-06-21.
 */
public class UserRepositoryImpl implements UserRepository {
    public static final String TABLE_USER = "user";
    //private SQLiteDatabase db;
    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_SURNAME = "surname";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";





    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_USER_NAME + " TEXT  NOT NULL , "
            + COLUMN_USER_SURNAME + " INTEGER NOT NULL ,"
            + COLUMN_USER_EMAIL + " TEXT UNIQUE  NOT NULL , "
            + COLUMN_USER_PASSWORD + " TEXT  NULL );";



  /*  public UserRepositoryImpl(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    public UserRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }
*/
    @Override
    public User findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USER,
                new String[]{
                        COLUMN_USER_ID,
                        COLUMN_USER_NAME,
                        COLUMN_USER_SURNAME,
                        COLUMN_USER_EMAIL,
                        COLUMN_USER_PASSWORD},

                COLUMN_USER_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final User animal = new User.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_USER_SURNAME)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)))
                    .password(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }



    public User findByEmail(String email) {
     //   final User animal ;
     //   long id = 0;
      //  String name = "",surname = "",emails = "",password = "w";
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                        TABLE_USER,
                        new String[]{
                                COLUMN_USER_ID,
                                COLUMN_USER_NAME,
                                COLUMN_USER_SURNAME,
                                COLUMN_USER_EMAIL,
                                COLUMN_USER_PASSWORD},

                        COLUMN_USER_EMAIL + " =? ",
                new String[]{email},
                null,
                null,
                null,
                null);
      //  if (cursor.moveToFirst()) {
       if (cursor.moveToFirst()) {
            final User animal = new User.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_USER_SURNAME)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)))
                    .password(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)))
                    .build();
           return animal;
        }

else
       {

           return null;
      }

    }
    @Override
    public User save(User entity) {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
      //  values.put(COLUMN_USER_ID, entity.getid());

        values.put(COLUMN_USER_NAME, entity.getName());
        values.put(COLUMN_USER_SURNAME, entity.getsurname());
        values.put(COLUMN_USER_EMAIL, entity.getEmail());
        values.put(COLUMN_USER_PASSWORD, entity.getpassword());
     long id = db.insertOrThrow(TABLE_USER, null, values);
        User insertedEntity = new User.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public User update(User entity) {
        SQLiteDatabase db =   DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, entity.getid());

        values.put(COLUMN_USER_NAME, entity.getName());
        values.put(COLUMN_USER_SURNAME, entity.getsurname());
        values.put(COLUMN_USER_EMAIL, entity.getEmail());
        values.put(COLUMN_USER_PASSWORD, entity.getpassword());
        db.update(
                TABLE_USER,
                values,
                COLUMN_USER_ID + " =? ",
                new String[]{String.valueOf(entity.getid())}
        );
        return entity;
    }

    @Override
    public User delete(User entity) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_USER,
                COLUMN_USER_ID + " =? ",
                new String[]{String.valueOf(entity.getid())});
        return entity;
    }

    @Override
    public ArrayList<User> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<User> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_USER, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final User animals = new User.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_USER_SURNAME)))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)))
                        .password(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        return animal;
    }

    @Override
    public int deleteAll() {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        int rowsDeleted = db.delete(TABLE_USER,null,null);
        DatabaseManager.getInstance().closeDatabase();
        return rowsDeleted;
    }

/*    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);

    }*/
}

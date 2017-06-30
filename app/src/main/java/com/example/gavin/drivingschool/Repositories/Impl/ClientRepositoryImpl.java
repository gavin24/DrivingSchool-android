package com.example.gavin.drivingschool.Repositories.Impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gavin.drivingschool.Config.DbConstants.DatabaseHelper;
import com.example.gavin.drivingschool.Config.DbConstants.DatabaseManager;
import com.example.gavin.drivingschool.Domain.Client;
import com.example.gavin.drivingschool.Repositories.ClientRepository;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public class ClientRepositoryImpl implements ClientRepository {
    public static final String TABLE_CLIENT = "client";
    //private SQLiteDatabase db;
    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_CLIENT_ID = "id";
    public static final String COLUMN_CLIENT_EMAIL = "Email";
    public static final String COLUMN_CLIENT_PASSWORD = "Password";
    public static final String COLUMN_CLIENT_PHONENUMBER = "PhoneNumber";
    public static final String COLUMN_CLIENT_NAME = "Name";
    public static final String COLUMN_CLIENT_SURNAME = "Surname";
    public static final String COLUMN_CLIENT_USERID= "ClientUserId";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_CLIENT + "("
            + COLUMN_CLIENT_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_CLIENT_EMAIL + " TEXT NULL , "
            + COLUMN_CLIENT_PASSWORD + " TEXT NOT NULL ,"
            + COLUMN_CLIENT_PHONENUMBER + " INTEGER  NOT NULL , "
            + COLUMN_CLIENT_NAME + " INTEGER NOT NULL ,"
            + COLUMN_CLIENT_SURNAME + " TEXT  NOT NULL , "
            + COLUMN_CLIENT_USERID + " INTEGER  NULL );";



    /*  public ClientRepositoryImpl(Context context) {
          super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
      }

      public ClientRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
    public Client findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_CLIENT,
                new String[]{
                        COLUMN_CLIENT_ID,
                        COLUMN_CLIENT_NAME,
                        COLUMN_CLIENT_SURNAME,
                        COLUMN_CLIENT_EMAIL,
                        COLUMN_CLIENT_PASSWORD,
                        COLUMN_CLIENT_PHONENUMBER,
                        COLUMN_CLIENT_USERID},

                COLUMN_CLIENT_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Client animal = new Client.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_CLIENT_ID)))
                    .Name(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_NAME)))
                    .Surname(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_SURNAME)))
                    .Email(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_EMAIL)))
                    .Password(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_PASSWORD)))
                    .PhoneNumber(cursor.getInt(cursor.getColumnIndex(COLUMN_CLIENT_PHONENUMBER)))
                    .ClientAddressId(cursor.getLong(cursor.getColumnIndex(COLUMN_CLIENT_USERID)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }



    @Override
    public Client save(Client entity) {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //  values.put(COLUMN_CLIENT_ID, entity.getid());

        values.put(COLUMN_CLIENT_NAME, entity.getName());
        values.put(COLUMN_CLIENT_SURNAME, entity.getSurname());
        values.put(COLUMN_CLIENT_EMAIL, entity.getEmail());
        values.put(COLUMN_CLIENT_PASSWORD, entity.getPassword());
        values.put(COLUMN_CLIENT_PHONENUMBER, entity.getPhoneNumber());
        values.put(COLUMN_CLIENT_USERID, entity.getClientAddressId());
        long id = db.insertOrThrow(TABLE_CLIENT, null, values);
        Client insertedEntity = new Client.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public Client update(Client entity) {
        SQLiteDatabase db =   DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLIENT_ID, entity.getId());

        values.put(COLUMN_CLIENT_NAME, entity.getName());
        values.put(COLUMN_CLIENT_SURNAME, entity.getSurname());
        values.put(COLUMN_CLIENT_EMAIL, entity.getEmail());
        values.put(COLUMN_CLIENT_PASSWORD, entity.getPassword());
        values.put(COLUMN_CLIENT_PHONENUMBER, entity.getPhoneNumber());
        values.put(COLUMN_CLIENT_USERID, entity.getClientAddressId());
        db.update(
                TABLE_CLIENT,
                values,
                COLUMN_CLIENT_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Client delete(Client entity) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_CLIENT,
                COLUMN_CLIENT_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public ArrayList<Client> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Client> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_CLIENT, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final Client animals = new Client.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_CLIENT_ID)))
                        .Name(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_NAME)))
                        .Surname(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_SURNAME)))
                        .Email(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_EMAIL)))
                        .Password(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_PASSWORD)))
                        .PhoneNumber(cursor.getInt(cursor.getColumnIndex(COLUMN_CLIENT_PHONENUMBER)))
                        .ClientAddressId(cursor.getLong(cursor.getColumnIndex(COLUMN_CLIENT_USERID)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        return animal;
    }

    @Override
    public int deleteAll() {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        int rowsDeleted = db.delete(TABLE_CLIENT,null,null);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);
        onCreate(db);

    }*/
}

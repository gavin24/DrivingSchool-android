package com.example.gavin.drivingschool.Repositories.Impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gavin.drivingschool.Config.DbConstants.DatabaseHelper;
import com.example.gavin.drivingschool.Config.DbConstants.DatabaseManager;
import com.example.gavin.drivingschool.Domain.Address;
import com.example.gavin.drivingschool.Domain.User;
import com.example.gavin.drivingschool.Repositories.AddressRepository;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public class AddressRepositoryImpl implements AddressRepository {
    public static final String TABLE_ADDRESS = "address";
    //private SQLiteDatabase db;
    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_ADDRESS_ID = "id";
    public static final String COLUMN_ADDRESS_CLIENT_ID = "ClientId";
    public static final String COLUMN_ADDRESS_LINE1 = "Line1";
    public static final String COLUMN_ADDRESS_LINE2 = "Line2";
    public static final String COLUMN_ADDRESS_LINE3 = "Line3";
    public static final String COLUMN_ADDRESS_LINE4 = "Line4";
    public static final String COLUMN_ADDRESS_LINE5 = "Line5";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_ADDRESS + "("
            + COLUMN_ADDRESS_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_ADDRESS_CLIENT_ID + " INTEGER NULL , "
            + COLUMN_ADDRESS_LINE1 + " TEXT NOT NULL ,"
            + COLUMN_ADDRESS_LINE2 + " TEXT UNIQUE  NOT NULL , "
            + COLUMN_ADDRESS_LINE3 + " TEXT NOT NULL ,"
            + COLUMN_ADDRESS_LINE4 + " TEXT  NULL , "
            + COLUMN_ADDRESS_LINE5 + " TEXT  NULL );";



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
    public Address findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_ADDRESS,
                new String[]{
                        COLUMN_ADDRESS_ID,
                        COLUMN_ADDRESS_CLIENT_ID,
                        COLUMN_ADDRESS_LINE1,
                        COLUMN_ADDRESS_LINE2,
                        COLUMN_ADDRESS_LINE3,
                        COLUMN_ADDRESS_LINE4,
                        COLUMN_ADDRESS_LINE5},

                COLUMN_ADDRESS_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Address animal = new Address.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ADDRESS_ID)))
                    .ClientId(cursor.getLong(cursor.getColumnIndex(COLUMN_ADDRESS_CLIENT_ID)))
                    .Line1(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_LINE1)))
                    .Line2(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_LINE2)))
                    .Line3(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_LINE3)))
                    .Line4(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_LINE4)))
                    .Line5(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_LINE5)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }




    @Override
    public Address save(Address entity) {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //  values.put(COLUMN_USER_ID, entity.getid());

        values.put(COLUMN_ADDRESS_CLIENT_ID, entity.getClientId());
        values.put(COLUMN_ADDRESS_LINE1, entity.getLine1());
        values.put(COLUMN_ADDRESS_LINE2, entity.getLine2());
        values.put(COLUMN_ADDRESS_LINE3, entity.getLine3());
        values.put(COLUMN_ADDRESS_LINE4, entity.getLine4());
        values.put(COLUMN_ADDRESS_LINE5, entity.getLine5());

        long id = db.insertOrThrow(TABLE_ADDRESS, null, values);
        Address insertedEntity = new Address.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public Address update(Address entity) {
        SQLiteDatabase db =   DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ADDRESS_ID, entity.getId());
        values.put(COLUMN_ADDRESS_CLIENT_ID, entity.getClientId());
        values.put(COLUMN_ADDRESS_LINE1, entity.getLine1());
        values.put(COLUMN_ADDRESS_LINE2, entity.getLine2());
        values.put(COLUMN_ADDRESS_LINE3, entity.getLine3());
        values.put(COLUMN_ADDRESS_LINE4, entity.getLine4());
        values.put(COLUMN_ADDRESS_LINE5, entity.getLine5());
        db.update(
                TABLE_ADDRESS,
                values,
                COLUMN_ADDRESS_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Address delete(Address entity) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_ADDRESS,
                COLUMN_ADDRESS_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public ArrayList<Address> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Address> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_ADDRESS, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final Address animals = new Address.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ADDRESS_ID)))
                        .ClientId(cursor.getLong(cursor.getColumnIndex(COLUMN_ADDRESS_CLIENT_ID)))
                        .Line1(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_LINE1)))
                        .Line2(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_LINE2)))
                        .Line3(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_LINE3)))
                        .Line4(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_LINE4)))
                        .Line5(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_LINE5)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        return animal;
    }

    @Override
    public int deleteAll() {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        int rowsDeleted = db.delete(TABLE_ADDRESS,null,null);
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

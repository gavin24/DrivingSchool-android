package com.example.gavin.drivingschool.Repositories.Impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gavin.drivingschool.Config.DbConstants.DatabaseHelper;
import com.example.gavin.drivingschool.Config.DbConstants.DatabaseManager;
import com.example.gavin.drivingschool.Domain.ContactInfo;
import com.example.gavin.drivingschool.Repositories.ContactInfoRepository;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public class ContactInfoRepositoryImpl implements ContactInfoRepository {
    public static final String TABLE_CONTACTINFO = "contactinfo";
    //private SQLiteDatabase db;
    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_CONTACTINFO_ID = "id";
    public static final String COLUMN_CONTACTINFO_USERID = "ContactInfoId";
    public static final String COLUMN_CONTACTINFO_PHONENUMBER = "PhoneNumber";
    public static final String COLUMN_CONTACTINFO_EMAIL = "Email";
    public static final String COLUMN_CONTACTINFO_SECONDARYEMAIL = "SecondaryEmail";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_CONTACTINFO + "("
            + COLUMN_CONTACTINFO_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_CONTACTINFO_USERID + " INTEGER  NULL , "
            + COLUMN_CONTACTINFO_PHONENUMBER + " INTEGER NOT NULL ,"
            + COLUMN_CONTACTINFO_EMAIL + " TEXT  NOT NULL , "
            + COLUMN_CONTACTINFO_SECONDARYEMAIL + " TEXT  NULL );";



    /*  public ContactInfoRepositoryImpl(Context context) {
          super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
      }

      public ContactInfoRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
    public ContactInfo findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_CONTACTINFO,
                new String[]{
                        COLUMN_CONTACTINFO_ID,
                        COLUMN_CONTACTINFO_USERID,
                        COLUMN_CONTACTINFO_PHONENUMBER,
                        COLUMN_CONTACTINFO_EMAIL,
                        COLUMN_CONTACTINFO_SECONDARYEMAIL},

                COLUMN_CONTACTINFO_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final ContactInfo animal = new ContactInfo.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_CONTACTINFO_ID)))
                    .UserId(cursor.getLong(cursor.getColumnIndex(COLUMN_CONTACTINFO_USERID)))
                    .PhoneNumber(cursor.getInt(cursor.getColumnIndex(COLUMN_CONTACTINFO_PHONENUMBER)))
                    .Email(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACTINFO_EMAIL)))
                    .SecondaryEmail(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACTINFO_SECONDARYEMAIL)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }



    @Override
    public ContactInfo save(ContactInfo entity) {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //  values.put(COLUMN_CONTACTINFO_ID, entity.getid());

        values.put(COLUMN_CONTACTINFO_USERID, entity.getUserId());
        values.put(COLUMN_CONTACTINFO_PHONENUMBER, entity.getPhoneNumber());
        values.put(COLUMN_CONTACTINFO_EMAIL, entity.getEmail());
        values.put(COLUMN_CONTACTINFO_SECONDARYEMAIL, entity.getSecondaryEmail());
        long id = db.insertOrThrow(TABLE_CONTACTINFO, null, values);
        ContactInfo insertedEntity = new ContactInfo.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public ContactInfo update(ContactInfo entity) {
        SQLiteDatabase db =   DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTACTINFO_ID, entity.getId());

        values.put(COLUMN_CONTACTINFO_USERID, entity.getUserId());
        values.put(COLUMN_CONTACTINFO_PHONENUMBER, entity.getPhoneNumber());
        values.put(COLUMN_CONTACTINFO_EMAIL, entity.getEmail());
        values.put(COLUMN_CONTACTINFO_SECONDARYEMAIL, entity.getSecondaryEmail());
        db.update(
                TABLE_CONTACTINFO,
                values,
                COLUMN_CONTACTINFO_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public ContactInfo delete(ContactInfo entity) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_CONTACTINFO,
                COLUMN_CONTACTINFO_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public ArrayList<ContactInfo> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<ContactInfo> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_CONTACTINFO, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final ContactInfo animals = new ContactInfo.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_CONTACTINFO_ID)))
                        .UserId(cursor.getLong(cursor.getColumnIndex(COLUMN_CONTACTINFO_USERID)))
                        .PhoneNumber(cursor.getInt(cursor.getColumnIndex(COLUMN_CONTACTINFO_PHONENUMBER)))
                        .Email(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACTINFO_EMAIL)))
                        .SecondaryEmail(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACTINFO_SECONDARYEMAIL)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        return animal;
    }

    @Override
    public int deleteAll() {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        int rowsDeleted = db.delete(TABLE_CONTACTINFO,null,null);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTINFO);
        onCreate(db);

    }*/
}

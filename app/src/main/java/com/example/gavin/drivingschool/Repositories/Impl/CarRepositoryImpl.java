package com.example.gavin.drivingschool.Repositories.Impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gavin.drivingschool.Config.DbConstants.DatabaseHelper;
import com.example.gavin.drivingschool.Config.DbConstants.DatabaseManager;
import com.example.gavin.drivingschool.Domain.Car;
import com.example.gavin.drivingschool.Repositories.CarRepository;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public class CarRepositoryImpl implements CarRepository {
    public static final String TABLE_CAR = "car";
    //private SQLiteDatabase db;
    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_CAR_ID = "id";
    public static final String COLUMN_CAR_CARID = "CarId";
    public static final String COLUMN_CAR_TYPE = "Type";
    public static final String COLUMN_CAR_LICENSE = "License";
    public static final String COLUMN_CAR_YEAR = "Year";
    public static final String COLUMN_CAR_MILEAGE = "Mileage";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_CAR + "("
            + COLUMN_CAR_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_CAR_CARID + " INTEGER  NOT NULL , "
            + COLUMN_CAR_TYPE + " TEXT NOT NULL ,"
            + COLUMN_CAR_LICENSE + " TEXT NOT NULL , "
            + COLUMN_CAR_YEAR + " INTEGER NOT NULL , "
            + COLUMN_CAR_MILEAGE + " INTEGER  NULL );";



    /*  public CarRepositoryImpl(Context context) {
          super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
      }

      public CarRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
    public Car findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_CAR,
                new String[]{
                        COLUMN_CAR_ID,
                        COLUMN_CAR_CARID,
                        COLUMN_CAR_TYPE,
                        COLUMN_CAR_LICENSE,
                        COLUMN_CAR_YEAR,
                        COLUMN_CAR_MILEAGE},

                COLUMN_CAR_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Car animal = new Car.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_CAR_ID)))
                    .UserId(cursor.getLong(cursor.getColumnIndex(COLUMN_CAR_CARID)))
                    .Type(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_TYPE)))
                    .License(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_LICENSE)))
                    .Year(cursor.getInt(cursor.getColumnIndex(COLUMN_CAR_YEAR)))
                    .Mileage(cursor.getInt(cursor.getColumnIndex(COLUMN_CAR_MILEAGE)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }



    @Override
    public Car save(Car entity) {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //  values.put(COLUMN_CAR_ID, entity.getid());
        values.put(COLUMN_CAR_CARID, entity.getUserId());
        values.put(COLUMN_CAR_TYPE, entity.getType());
        values.put(COLUMN_CAR_LICENSE, entity.getLicense());
        values.put(COLUMN_CAR_YEAR, entity.getYear());
        values.put(COLUMN_CAR_MILEAGE, entity.getMileage());
        long id = db.insertOrThrow(TABLE_CAR, null, values);
        Car insertedEntity = new Car.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public Car update(Car entity) {
        SQLiteDatabase db =   DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CAR_ID, entity.getId());

        values.put(COLUMN_CAR_CARID, entity.getUserId());
        values.put(COLUMN_CAR_TYPE, entity.getType());
        values.put(COLUMN_CAR_LICENSE, entity.getLicense());
        values.put(COLUMN_CAR_YEAR, entity.getYear());
        values.put(COLUMN_CAR_MILEAGE, entity.getMileage());
        db.update(
                TABLE_CAR,
                values,
                COLUMN_CAR_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Car delete(Car entity) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_CAR,
                COLUMN_CAR_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public ArrayList<Car> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Car> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_CAR, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final Car animals = new Car.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_CAR_ID)))
                        .UserId(cursor.getLong(cursor.getColumnIndex(COLUMN_CAR_CARID)))
                        .Type(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_TYPE)))
                        .License(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_LICENSE)))
                        .Year(cursor.getInt(cursor.getColumnIndex(COLUMN_CAR_YEAR)))
                        .Mileage(cursor.getInt(cursor.getColumnIndex(COLUMN_CAR_MILEAGE)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        return animal;
    }

    @Override
    public int deleteAll() {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        int rowsDeleted = db.delete(TABLE_CAR,null,null);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
        onCreate(db);

    }*/
}

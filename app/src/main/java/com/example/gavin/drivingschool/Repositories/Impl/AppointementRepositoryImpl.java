package com.example.gavin.drivingschool.Repositories.Impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gavin.drivingschool.Config.DbConstants.DatabaseHelper;
import com.example.gavin.drivingschool.Config.DbConstants.DatabaseManager;
import com.example.gavin.drivingschool.Domain.Appointment;
import com.example.gavin.drivingschool.Repositories.AppointmentRepository;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by gavin on 2017/06/27.
 */
public class AppointementRepositoryImpl implements AppointmentRepository {
    public static final String TABLE_APPOINTMENT = "appointment";
    //private SQLiteDatabase db;
    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_APPOINTMENT_ID = "id";
    public static final String COLUMN_APPOINTMENT_CLIENTID = "ClientId";
    public static final String COLUMN_APPOINTMENT_DATE = "AppointmentDate";
    public static final String COLUMN_APPOINTMENT_STARTTIME = "StartTime";
    public static final String COLUMN_APPOINTMENT_ENDTIME = "EndTime";
    public static final String COLUMN_APPOINTMENT_USERID = "AppointmentUserId";
    public static final String COLUMN_APPOINTMENT_NOTES = "Notes";
    public long id ;
    public long ClientId ;
    public Date AppointmentDate ;
    public String StartTime ;
    public String EndTime ;
    public long AppointmentUserId ;
    public String Notes ;



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_APPOINTMENT + "("
            + COLUMN_APPOINTMENT_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_APPOINTMENT_CLIENTID + " INTEGER  NULL , "
            + COLUMN_APPOINTMENT_DATE + " TEXT NOT NULL ,"
            + COLUMN_APPOINTMENT_STARTTIME + " TEXT UNIQUE  NOT NULL , "
            + COLUMN_APPOINTMENT_ENDTIME + " TEXT NOT NULL ,"
            + COLUMN_APPOINTMENT_USERID + " INTEGER  NOT NULL , "
            + COLUMN_APPOINTMENT_NOTES + " TEXT  NULL );";



    /*  public AppointmentRepositoryImpl(Context context) {
          super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
      }

      public AppointmentRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
    public Appointment findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_APPOINTMENT,
                new String[]{
                        COLUMN_APPOINTMENT_ID,
                        COLUMN_APPOINTMENT_CLIENTID,
                        COLUMN_APPOINTMENT_DATE,
                        COLUMN_APPOINTMENT_STARTTIME,
                        COLUMN_APPOINTMENT_ENDTIME,
                        COLUMN_APPOINTMENT_USERID,
                        COLUMN_APPOINTMENT_NOTES},

                COLUMN_APPOINTMENT_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Appointment animal = new Appointment.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_APPOINTMENT_ID)))
                    .ClientId(cursor.getLong(cursor.getColumnIndex(COLUMN_APPOINTMENT_CLIENTID)))
                    .AppointmentDate(cursor.getString(cursor.getColumnIndex(COLUMN_APPOINTMENT_DATE)))
                    .StartTime(cursor.getString(cursor.getColumnIndex(COLUMN_APPOINTMENT_STARTTIME)))
                    .EndTime(cursor.getString(cursor.getColumnIndex(COLUMN_APPOINTMENT_ENDTIME)))
                    .AppointmentUserId(cursor.getLong(cursor.getColumnIndex(COLUMN_APPOINTMENT_USERID)))
                    .Notes(cursor.getString(cursor.getColumnIndex(COLUMN_APPOINTMENT_NOTES)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }




    @Override
    public Appointment save(Appointment entity) {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //  values.put(COLUMN_USER_ID, entity.getid());

        values.put(COLUMN_APPOINTMENT_CLIENTID, entity.getClientId());
        values.put(COLUMN_APPOINTMENT_DATE, entity.getAppointmentDate());
        values.put(COLUMN_APPOINTMENT_STARTTIME, entity.getStartTime());
        values.put(COLUMN_APPOINTMENT_ENDTIME, entity.getEndTime());
        values.put(COLUMN_APPOINTMENT_USERID, entity.getAppointmentUserId());
        values.put(COLUMN_APPOINTMENT_NOTES, entity.getNotes());
        long id = db.insertOrThrow(TABLE_APPOINTMENT, null, values);
        Appointment insertedEntity = new Appointment.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public Appointment update(Appointment entity) {
        SQLiteDatabase db =   DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_APPOINTMENT_ID, entity.getId());
        values.put(COLUMN_APPOINTMENT_CLIENTID, entity.getClientId());
        values.put(COLUMN_APPOINTMENT_DATE, entity.getAppointmentDate());
        values.put(COLUMN_APPOINTMENT_STARTTIME, entity.getStartTime());
        values.put(COLUMN_APPOINTMENT_ENDTIME, entity.getEndTime());
        values.put(COLUMN_APPOINTMENT_USERID, entity.getAppointmentUserId());
        values.put(COLUMN_APPOINTMENT_NOTES, entity.getNotes());
        db.update(
                TABLE_APPOINTMENT,
                values,
                COLUMN_APPOINTMENT_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Appointment delete(Appointment entity) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_APPOINTMENT,
                COLUMN_APPOINTMENT_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public ArrayList<Appointment> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Appointment> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_APPOINTMENT, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final Appointment animals = new Appointment.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_APPOINTMENT_ID)))
                        .ClientId(cursor.getLong(cursor.getColumnIndex(COLUMN_APPOINTMENT_CLIENTID)))
                        .AppointmentDate(cursor.getString(cursor.getColumnIndex(COLUMN_APPOINTMENT_DATE)))
                        .StartTime(cursor.getString(cursor.getColumnIndex(COLUMN_APPOINTMENT_STARTTIME)))
                        .EndTime(cursor.getString(cursor.getColumnIndex(COLUMN_APPOINTMENT_ENDTIME)))
                        .AppointmentUserId(cursor.getLong(cursor.getColumnIndex(COLUMN_APPOINTMENT_USERID)))
                        .Notes(cursor.getString(cursor.getColumnIndex(COLUMN_APPOINTMENT_NOTES)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        return animal;
    }

    @Override
    public int deleteAll() {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        int rowsDeleted = db.delete(TABLE_APPOINTMENT,null,null);
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

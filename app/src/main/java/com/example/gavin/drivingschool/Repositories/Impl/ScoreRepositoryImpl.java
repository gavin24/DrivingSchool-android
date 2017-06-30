package com.example.gavin.drivingschool.Repositories.Impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gavin.drivingschool.Config.DbConstants.DatabaseHelper;
import com.example.gavin.drivingschool.Config.DbConstants.DatabaseManager;
import com.example.gavin.drivingschool.Domain.Score;
import com.example.gavin.drivingschool.Repositories.ScoreRepository;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public class ScoreRepositoryImpl implements ScoreRepository {
    public static final String TABLE_SCORE = "score";
    //private SQLiteDatabase db;
    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_SCORE_ID = "id";
    public static final String COLUMN_SCORE_CLIENTID= "ClientId";
    public static final String COLUMN_SCORE_USERID = "ScoreId";
    public static final String COLUMN_SCORE_ALLEYRIGHT = "AlleyDockingRight";
    public static final String COLUMN_SCORE_ALLEYLEFT = "AlleyDockingLeft";
    public static final String COLUMN_SCORE_PARRIGHT = "ParallelParkingRight";
    public static final String COLUMN_SCORE_PARLEFT = "ParallelParkingLeft";
    public static final String COLUMN_SCORE_THREE = "ThreePointTurn";
    public static final String COLUMN_SCORE_DRIVING = "Driving";
    public static final String COLUMN_SCORE_LESSONNUMBER= "LessonNumber";




    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_SCORE + "("
            + COLUMN_SCORE_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_SCORE_CLIENTID + " INTEGER NULL , "
            + COLUMN_SCORE_USERID + " INTEGER NULL ,"
            + COLUMN_SCORE_ALLEYRIGHT + " INTEGER NULL , "
            + COLUMN_SCORE_ALLEYLEFT + " INTEGER NULL , "
            + COLUMN_SCORE_PARRIGHT + " INTEGER NULL ,"
            + COLUMN_SCORE_PARLEFT + " INTEGER NULL , "
            + COLUMN_SCORE_THREE + " INTEGERNULL ,"
            + COLUMN_SCORE_DRIVING + " INTEGER NULL , "
            + COLUMN_SCORE_LESSONNUMBER + " INTEGER NULL );";



    /*  public ScoreRepositoryImpl(Context context) {
          super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
      }

      public ScoreRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
    public Score findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_SCORE,
                new String[]{
                        COLUMN_SCORE_ID,
                        COLUMN_SCORE_CLIENTID,
                        COLUMN_SCORE_USERID,
                        COLUMN_SCORE_ALLEYRIGHT,
                        COLUMN_SCORE_ALLEYLEFT,
                        COLUMN_SCORE_PARRIGHT,
                        COLUMN_SCORE_PARLEFT,
                        COLUMN_SCORE_THREE,
                        COLUMN_SCORE_DRIVING,
                        COLUMN_SCORE_LESSONNUMBER},

                COLUMN_SCORE_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Score animal = new Score.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_SCORE_ID)))
                    .ClientId(cursor.getLong(cursor.getColumnIndex(COLUMN_SCORE_CLIENTID)))
                    .UserId(cursor.getLong(cursor.getColumnIndex(COLUMN_SCORE_USERID)))
                    .AlleyDockingRight(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_ALLEYRIGHT)))
                    .AlleyDockingLeft(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_ALLEYLEFT)))
                    .ParallelParkingRight(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_PARRIGHT)))
                    .ParallelParkingLeft(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_PARLEFT)))
                    .ThreePointTurn(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_THREE)))
                    .Driving(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_DRIVING)))
                    .LessonNumber(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_LESSONNUMBER)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }



    @Override
    public Score save(Score entity) {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //  values.put(COLUMN_SCORE_ID, entity.getid());
        values.put(COLUMN_SCORE_CLIENTID, entity.getClientId());
        values.put(COLUMN_SCORE_USERID, entity.getUserId());
        values.put(COLUMN_SCORE_ALLEYRIGHT, entity.getAlleyDockingRight());
        values.put(COLUMN_SCORE_ALLEYLEFT, entity.getAlleyDockingLeft());
        values.put(COLUMN_SCORE_PARRIGHT, entity.getParallelParkingRight());
        values.put(COLUMN_SCORE_PARLEFT, entity.getParallelParkingLeft());
        values.put(COLUMN_SCORE_THREE, entity.getThreePointTurn());
        values.put(COLUMN_SCORE_DRIVING, entity.getDriving());
        values.put(COLUMN_SCORE_LESSONNUMBER, entity.getLessonNumber());
        long id = db.insertOrThrow(TABLE_SCORE, null, values);
        Score insertedEntity = new Score.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public Score update(Score entity) {
        SQLiteDatabase db =   DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE_ID, entity.getId());

        values.put(COLUMN_SCORE_CLIENTID, entity.getClientId());
        values.put(COLUMN_SCORE_USERID, entity.getUserId());
        values.put(COLUMN_SCORE_ALLEYRIGHT, entity.getAlleyDockingRight());
        values.put(COLUMN_SCORE_ALLEYLEFT, entity.getAlleyDockingLeft());
        values.put(COLUMN_SCORE_PARRIGHT, entity.getParallelParkingRight());
        values.put(COLUMN_SCORE_PARLEFT, entity.getParallelParkingLeft());
        values.put(COLUMN_SCORE_THREE, entity.getThreePointTurn());
        values.put(COLUMN_SCORE_DRIVING, entity.getDriving());
        values.put(COLUMN_SCORE_LESSONNUMBER, entity.getLessonNumber());
        db.update(
                TABLE_SCORE,
                values,
                COLUMN_SCORE_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Score delete(Score entity) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_SCORE,
                COLUMN_SCORE_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public ArrayList<Score> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Score> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_SCORE, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final Score animals = new Score.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_SCORE_ID)))
                        .ClientId(cursor.getLong(cursor.getColumnIndex(COLUMN_SCORE_CLIENTID)))
                        .UserId(cursor.getLong(cursor.getColumnIndex(COLUMN_SCORE_USERID)))
                        .AlleyDockingRight(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_ALLEYRIGHT)))
                        .AlleyDockingLeft(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_ALLEYLEFT)))
                        .ParallelParkingRight(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_PARRIGHT)))
                        .ParallelParkingLeft(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_PARLEFT)))
                        .ThreePointTurn(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_THREE)))
                        .Driving(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_DRIVING)))
                        .LessonNumber(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_LESSONNUMBER)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        return animal;
    }

    @Override
    public int deleteAll() {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        int rowsDeleted = db.delete(TABLE_SCORE,null,null);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        onCreate(db);

    }*/
}

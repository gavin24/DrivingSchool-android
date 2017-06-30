package com.example.gavin.drivingschool.Config.DbConstants;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gavin.drivingschool.Config.Util.App;

/**
 * Created by gavin.ackerman on 2016-04-23.
 */
public class DatabaseHelper  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="DriveDB.db";
    public static final int DATABASE_VERSION=25;




    // Table Names

    public static final String TABLE_IMAGE = "image";

    public static final String TABLE_USER = "user";

    public static final String TABLE_USERIMAGE = "userimage";

    public static final String TABLE_USERFAME = "userfame";

    public static final String TABLE_ADDRESS = "address";
    public static final String TABLE_APPOINTMENT = "appointment";
    public static final String TABLE_CAR = "car";
    public static final String TABLE_CLIENT = "client";
    public static final String TABLE_CONTACTINFO = "contactinfo";
    public static final String TABLE_SCORE = "score";







    public static final String COLUMN_USERIMAGE_ID = "id";
    public static final String COLUMN_USERIMAGE_IMAGE = "image";
    public static final String COLUMN_USERIMAGE_NAME = "name";
    public static final String COLUMN_USERIMAGE_USERID = "userid";


    public static final String COLUMN_IMAGE_ID = "id";
    public static final String COLUMN_IMAGE_IMAGE = "image";
    public static final String COLUMN_IMAGE_NAME = "name";
    public static final String COLUMN_IMAGE_USERID = "userid";


    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_SURNAME = "surname";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";


    public static final String COLUMN_ADDRESS_ID = "id";
    public static final String COLUMN_ADDRESS_CLIENT_ID = "ClientId";
    public static final String COLUMN_ADDRESS_LINE1 = "Line1";
    public static final String COLUMN_ADDRESS_LINE2 = "Line2";
    public static final String COLUMN_ADDRESS_LINE3 = "Line3";
    public static final String COLUMN_ADDRESS_LINE4 = "Line4";
    public static final String COLUMN_ADDRESS_LINE5 = "Line5";



    public static final String COLUMN_APPOINTMENT_ID = "id";
    public static final String COLUMN_APPOINTMENT_CLIENTID = "ClientId";
    public static final String COLUMN_APPOINTMENT_DATE = "AppointmentDate";
    public static final String COLUMN_APPOINTMENT_STARTTIME = "StartTime";
    public static final String COLUMN_APPOINTMENT_ENDTIME = "EndTime";
    public static final String COLUMN_APPOINTMENT_USERID = "AppointmentUserId";
    public static final String COLUMN_APPOINTMENT_NOTES = "Notes";


    public static final String COLUMN_CAR_ID = "id";
    public static final String COLUMN_CAR_CARID = "CarId";
    public static final String COLUMN_CAR_TYPE = "Type";
    public static final String COLUMN_CAR_LICENSE = "License";
    public static final String COLUMN_CAR_YEAR = "Year";
    public static final String COLUMN_CAR_MILEAGE = "Mileage";

    public static final String COLUMN_CLIENT_ID = "id";
    public static final String COLUMN_CLIENT_EMAIL = "Email";
    public static final String COLUMN_CLIENT_PASSWORD = "Password";
    public static final String COLUMN_CLIENT_PHONENUMBER = "PhoneNumber";
    public static final String COLUMN_CLIENT_NAME = "Name";
    public static final String COLUMN_CLIENT_SURNAME = "Surname";
    public static final String COLUMN_CLIENT_USERID= "ClientUserId";


    public static final String COLUMN_CONTACTINFO_ID = "id";
    public static final String COLUMN_CONTACTINFO_USERID = "ContactInfoId";
    public static final String COLUMN_CONTACTINFO_PHONENUMBER = "PhoneNumber";
    public static final String COLUMN_CONTACTINFO_EMAIL = "Email";
    public static final String COLUMN_CONTACTINFO_SECONDARYEMAIL = "SecondaryEmail";


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
    // Table Create Statements


    private static final String CREATE_TABLE_SCORE = " CREATE TABLE "
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

    private static final String CREATE_TABLE_CONTACTINFO = " CREATE TABLE "
            + TABLE_CONTACTINFO + "("
            + COLUMN_CONTACTINFO_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_CONTACTINFO_USERID + " INTEGER  NULL , "
            + COLUMN_CONTACTINFO_PHONENUMBER + " INTEGER NOT NULL ,"
            + COLUMN_CONTACTINFO_EMAIL + " TEXT  NOT NULL , "
            + COLUMN_CONTACTINFO_SECONDARYEMAIL + " TEXT  NULL );";

    private static final String CREATE_TABLE_CLIENT= " CREATE TABLE "
            + TABLE_CLIENT + "("
            + COLUMN_CLIENT_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_CLIENT_EMAIL + " TEXT NULL , "
            + COLUMN_CLIENT_PASSWORD + " TEXT NOT NULL ,"
            + COLUMN_CLIENT_PHONENUMBER + " INTEGER  NOT NULL , "
            + COLUMN_CLIENT_NAME + " INTEGER NOT NULL ,"
            + COLUMN_CLIENT_SURNAME + " TEXT  NOT NULL , "
            + COLUMN_CLIENT_USERID + " INTEGER  NULL );";


    private static final String CREATE_TABLE_CAR = " CREATE TABLE "
            + TABLE_CAR + "("
            + COLUMN_CAR_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_CAR_CARID + " INTEGER  NOT NULL , "
            + COLUMN_CAR_TYPE + " TEXT NOT NULL ,"
            + COLUMN_CAR_LICENSE + " TEXT NOT NULL , "
            + COLUMN_CAR_YEAR + " INTEGER NOT NULL , "
            + COLUMN_CAR_MILEAGE + " INTEGER  NULL );";



    private static final String CREATE_TABLE_APPOINTMENT = " CREATE TABLE "
            + TABLE_APPOINTMENT + "("
            + COLUMN_APPOINTMENT_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_APPOINTMENT_CLIENTID + " INTEGER  NULL , "
            + COLUMN_APPOINTMENT_DATE + " TEXT NOT NULL ,"
            + COLUMN_APPOINTMENT_STARTTIME + " TEXT UNIQUE  NOT NULL , "
            + COLUMN_APPOINTMENT_ENDTIME + " TEXT NOT NULL ,"
            + COLUMN_APPOINTMENT_USERID + " INTEGER  NOT NULL , "
            + COLUMN_APPOINTMENT_NOTES + " TEXT  NULL );";




    // Todo table create statement
    private static final String CREATE_TABLE_ADDRESS = " CREATE TABLE "
            + TABLE_ADDRESS + "("
            + COLUMN_ADDRESS_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_ADDRESS_CLIENT_ID + " INTEGER NULL , "
            + COLUMN_ADDRESS_LINE1 + " TEXT NOT NULL ,"
            + COLUMN_ADDRESS_LINE2 + " TEXT UNIQUE  NOT NULL , "
            + COLUMN_ADDRESS_LINE3 + " TEXT NOT NULL ,"
            + COLUMN_ADDRESS_LINE4 + " TEXT  NULL , "
            + COLUMN_ADDRESS_LINE5 + " TEXT  NULL );";








    private static final String CREATE_TABLE_USERIMAGE = " CREATE TABLE "
            + TABLE_USERIMAGE + "("
            + COLUMN_USERIMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERIMAGE_NAME + " TEXT NULL , "
            + COLUMN_USERIMAGE_IMAGE + " BLOB NULL , "
            + COLUMN_USERIMAGE_USERID + " INTEGER  NULL );";

    // Tag table create statement
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + TABLE_IMAGE
            + "("   + COLUMN_IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_IMAGE_NAME + " TEXT NULL , "
            + COLUMN_IMAGE_IMAGE + " BLOB NOT NULL , "
            + COLUMN_IMAGE_USERID + " INTEGER  NULL );";

    // todo_tag table create statement


    // todo_tag table create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "("  + COLUMN_USER_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_USER_NAME + " TEXT  NOT NULL , "
            + COLUMN_USER_SURNAME + " INTEGER NOT NULL ,"
            + COLUMN_USER_EMAIL + " TEXT UNIQUE  NOT NULL , "
            + COLUMN_USER_PASSWORD + " TEXT  NULL );";

    // todo_tag table create statement

   /* public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }*/
    public DatabaseHelper( ) {
        super(App.getAppContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables

        db.execSQL(CREATE_TABLE_IMAGE);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_USERIMAGE);
        db.execSQL(CREATE_TABLE_ADDRESS);
        db.execSQL(CREATE_TABLE_APPOINTMENT);
        db.execSQL(CREATE_TABLE_CAR);
        db.execSQL(CREATE_TABLE_CLIENT);
        db.execSQL(CREATE_TABLE_CONTACTINFO);
        db.execSQL(CREATE_TABLE_SCORE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERIMAGE);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERFAME);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTINFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        // create new tables
        onCreate(db);
    }
    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}

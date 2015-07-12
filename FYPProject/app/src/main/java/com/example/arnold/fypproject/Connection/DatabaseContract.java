package com.example.arnold.fypproject.Connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Arnold on 6/28/2015.
 */
public final class DatabaseContract {

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "fyp_mobile.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String COMMA_SEP          = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private DatabaseContract() {}

    public static abstract class TABLE_USER implements BaseColumns {
        public static final String TABLE_NAME       = "user";
        public static final String COLUMN_NAME_COL1 = "username";
        public static final String COLUMN_NAME_COL2 = "user_name";
        public static final String COLUMN_NAME_COL3 = "user_pw";
        public static final String COLUMN_NAME_COL4 = "age";
        public static final String COLUMN_NAME_COL5 = "date_created";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                1 + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_COL1 + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_COL2 + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_COL3 + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_COL4 + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_COL5 + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    /**
     * Created by Arnold on 6/28/2015.
     */
    public static class DatabaseHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "fyp_mobile.db";

        public DatabaseHelper(Context context){
            super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db){
            db.execSQL(TABLE_USER.CREATE_TABLE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL(TABLE_USER.DELETE_TABLE);
            onCreate(db);
        }
    }
}

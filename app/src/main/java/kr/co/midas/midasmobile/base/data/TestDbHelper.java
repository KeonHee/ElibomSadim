package kr.co.midas.midasmobile.base.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static kr.co.midas.midasmobile.base.data.TestContract.*;

/**
 * Created by user on 2017-05-26.
 */

public class TestDbHelper extends SQLiteOpenHelper {

    // The database name
    private static final String DATABASE_NAME = "test.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public TestDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " + TestEntry.TABLE_NAME + " (" +
                TestEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TestEntry.COLUMN_USER_NAME + " TEXT NOT NULL, " +
                TestEntry.COLUMN_AGE + " INTEGER NOT NULL, " +
                TestEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        db.execSQL(SQL_CREATE_WAITLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 버전별 DB Schema 변경 사항 기재
        db.execSQL("DROP TABLE IF EXISTS " + TestEntry.TABLE_NAME);
        onCreate(db);
    }
}

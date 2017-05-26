package kr.co.midas.midasmobile.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import kr.co.midas.midasmobile.base.data.TestDbHelper;

import static kr.co.midas.midasmobile.base.data.TestContract.TestEntry.COLUMN_AGE;
import static kr.co.midas.midasmobile.base.data.TestContract.TestEntry.COLUMN_USER_NAME;
import static kr.co.midas.midasmobile.base.data.TestContract.TestEntry.TABLE_NAME;
import static kr.co.midas.midasmobile.base.define.Define.INDEX_COLUMN_AGE;
import static kr.co.midas.midasmobile.base.define.Define.INDEX_COLUMN_USER_NAME;
import static kr.co.midas.midasmobile.base.define.Define.TEST_PROJECTION;

@RunWith(AndroidJUnit4.class)
public class TestDatabaseTest {

    private TestDbHelper mTestDbHelper;

    private SQLiteDatabase readDb;

    private SQLiteDatabase writeDb;

    @Before
    public void setUP(){
        Context appContext = InstrumentationRegistry.getTargetContext();

        mTestDbHelper = new TestDbHelper(appContext.getApplicationContext());
        readDb = mTestDbHelper.getReadableDatabase();
        writeDb = mTestDbHelper.getWritableDatabase();

        writeDb.delete(TABLE_NAME,null,null);
    }

    @Test
    public void DatabaseCRUDTest(){
        for(int i=0; i < 10; i++){
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_USER_NAME,"건희"+i);
            cv.put(COLUMN_AGE,27);
            writeDb.insert(TABLE_NAME,null,cv);
        }

        Cursor cursor = readDb.query(
                TABLE_NAME,         // Table Name
                TEST_PROJECTION,    // Table Columns
                null,               // Where 절
                null,               // Where 절 인자
                null,               // Group By
                null,               // Having
                null                // OrderBy
        );
        for(int i=0; i < 10; i++){
            cursor.moveToNext();
            Assert.assertEquals(
                    cursor.getString(INDEX_COLUMN_USER_NAME),
                    "건희"+i);
            Assert.assertEquals(
                    cursor.getInt(INDEX_COLUMN_AGE),
                    27);

            Log.d("test", cursor.getString(INDEX_COLUMN_USER_NAME));
            Log.d("test", String.valueOf(cursor.getInt(INDEX_COLUMN_AGE)));
        }



    }
}

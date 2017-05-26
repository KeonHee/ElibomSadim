package kr.co.midas.midasmobile.base.data;

import android.provider.BaseColumns;

/**
 * Created by user on 2017-05-26.
 */

public class TestContract {

    public static final class TestEntry implements BaseColumns {
        public static final String TABLE_NAME = "test";
        public static final String COLUMN_USER_NAME = "userName";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}

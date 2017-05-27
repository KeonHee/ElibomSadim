package kr.co.midas.midasmobile.base.define;

import static kr.co.midas.midasmobile.base.data.TestContract.TestEntry.COLUMN_AGE;
import static kr.co.midas.midasmobile.base.data.TestContract.TestEntry.COLUMN_TIMESTAMP;
import static kr.co.midas.midasmobile.base.data.TestContract.TestEntry.COLUMN_USER_NAME;

public class Define {

    public static final String[] TEST_PROJECTION = {
            COLUMN_USER_NAME,
            COLUMN_AGE,
            COLUMN_TIMESTAMP
    };

    public static final int INDEX_COLUMN_USER_NAME = 0;
    public static final int INDEX_COLUMN_AGE = 1;
    public static final int INDEX_COLUMN_TIMESTAMP = 2;

    public final static String HOST_URL = "https://api.github.com/";

}

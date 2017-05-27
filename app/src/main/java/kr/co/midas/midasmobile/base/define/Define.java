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

    public final static String HOST_URL = "http://kyuhwan.com:8080/";

    public static final int OK = 200;
    public static final int NOT_FOUND = 404;

    public final static String SHR_PREF_USER_ID_KEY="user_id_key";

    public final static String SHR_PREF_SESSION_KEY="session";
    public final static String SHR_PREF_EMAIL_KEY="email";
    public final static String SHR_PREF_PW_KEY="pw";
    public final static String SHR_PREF_CUR_POINT_KEY="cur-point";

}

package kr.co.midas.midasmobile;

import android.app.Application;

/**
 * Created by user on 2017-05-26.
 */

public class GlobalApp extends Application {


    private final static String TAG = "GlobalApp";

    private static GlobalApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static GlobalApp getGlobalApplicationContext(){
        if(mInstance==null){
            throw new IllegalStateException("this application does not inherit GlobalApplication");
        }
        return mInstance;
    }
}

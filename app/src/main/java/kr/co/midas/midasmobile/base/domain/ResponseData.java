package kr.co.midas.midasmobile.base.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2017-05-27.
 */

public class ResponseData<T> {

    @SerializedName("result")
    private T result;

    @SerializedName("code")
    private int code;

    public ResponseData() {
    }

    public ResponseData(T result, int code) {
        this.result = result;
        this.code = code;
    }

    public boolean isSuccessfull(){
        return code==200;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

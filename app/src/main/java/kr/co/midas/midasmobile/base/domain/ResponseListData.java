package kr.co.midas.midasmobile.base.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2017-05-28.
 */

public class ResponseListData<T> {

    @SerializedName("result")
    private List<T> result;

    @SerializedName("code")
    private int code;

    public ResponseListData(){}

    public ResponseListData(List<T> result, int code) {
        this.result = result;
        this.code = code;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

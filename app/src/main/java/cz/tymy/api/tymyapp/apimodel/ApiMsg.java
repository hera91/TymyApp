package cz.tymy.api.tymyapp.apimodel;

import java.lang.Object;

/**
 * Created by ph on 9/24/14.
 */
public abstract class ApiMsg {
    public static final String K_STATUS = "status";
    public static final String K_DATA = "data";
    public static final String K_POSTS = "posts";
    public static final String V_OK = "OK";
    public static final String K_DISCUSSION = "discussion";

    private String status = "";
    private Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

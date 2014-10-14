package cz.tymy.api.tymyapp.apimodel;

import org.json.JSONObject;

import java.lang.Object;

/**
 * Created by ph on 9/24/14.
 */
public abstract class ApiMsg {
    public static final String K_STATUS = "status";
    public static final String K_STATUS_MESSAGE = "statusMessage";
    public static final String K_DATA = "data";
    public static final String K_POSTS = "posts";
    public static final String V_OK = "OK";
    public static final String V_ERROR = "ERROR";
    public static final String K_DISCUSSION = "discussion";
    public static final String V_NOT_LOGGED_IN_1 = "Not loggged in";
    public static final String V_NOT_LOGGED_IN_2 = "Not logged in";

    private String status = "";
    private Object data;

    private String statusMessage;

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

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}

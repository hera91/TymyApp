package cz.tymy.api.tymyapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.net.URLEncoder;

public class ApiLoader extends AsyncTaskLoader<String> {
    public static final String CMD = "cmd";
    public static final String SEND_STATUS = "send_status";
    public static final String LOAD_LIST = "load_list";
    public static final String NAME = "name";
    public static final String STATUS = "status";
    public static final String COMMENT = "comment";
    public static final String LOGIN = "login";
    private static final String BASE_URL = "http://pd.tymy.cz/api/";
    private static final String USER = "hera";
    private static final String PASSWORD = "bistromat";
    public static final String DS_LIST = "ds_list";
    public static final String POST_LIST = "post_list";
    public static final String DS_ID = "ds_id";
    private Bundle args;
    private String result;

    public ApiLoader(Context context, Bundle args) {
        super(context);
        this.args = args;
    }

    @Override
    public String loadInBackground() {
        HttpClient hc = new HttpClient(BASE_URL);
        Log.v(DiscussionListActivity.TAG, "loadInBackground");
        try {
            if (SEND_STATUS.equals(this.args.getString(CMD))) {
                hc.postStatus(this.args.getString(NAME), this.args.getString(STATUS),
                        this.args.getString(COMMENT));
                result = hc.getJson();
            }
            else if (DS_LIST.equals(this.args.getString(CMD)))
                result = hc.sendGet(BASE_URL + "discussions/accessible/withNew/?"
                        + "login=" + URLEncoder.encode(USER, "UTF-8") + "&"
                        + "password=" + URLEncoder.encode(PASSWORD, "UTF-8"));
            else if (POST_LIST.equals(this.args.getString(CMD)))
                result = hc.sendGet(BASE_URL
                        + "discussion/" + this.args.getString(DS_ID) + "/html/1?"
                        + "login=" + URLEncoder.encode(USER, "UTF-8") + "&"
                        + "password=" + URLEncoder.encode(PASSWORD, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void deliverResult(String data) {
        if (isReset()) {
            return;
        }

        result = data;

        if (isStarted()) {
            super.deliverResult(data);
        }
        result = null;
    }

    @Override
    protected void onStartLoading() {
        if (result != null) {
            deliverResult(result);
        }

        if (takeContentChanged() || result == null) {
            forceLoad();
        }
    }
}

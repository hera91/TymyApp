package cz.tymy.api.tymyapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cz.tymy.api.tymyapp.apimodel.Api;

public class ApiLoader extends AsyncTaskLoader<String> {
    public static final String CMD = "cmd";
    public static final String DS_LIST = "ds_list";
    public static final String POST_LIST = "post_list";
    public static final String DS_ID = "ds_id";
    public static final String USER = "user";
    public static final String PASS = "pass";
    public static final String BASE_URL = "base_url";
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
            if (DS_LIST.equals(this.args.getString(CMD)))
                result = hc.sendGet(getDsListUrl());
            else if (POST_LIST.equals(this.args.getString(CMD)))
                result = hc.sendGet(getPostListUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //TODO could be moved into Application class
    // Collect discussion request url
    private String getDsListUrl() throws UnsupportedEncodingException {
        String url = args.getString(BASE_URL)
                + Api.DISCUSSIONS_ACCESSIBLE_WITH_NEW
                + getAuth();
        return url;
    }

    //TODO could be moved into Application class
    // Collect discussion posts list request url
    private String getPostListUrl() throws UnsupportedEncodingException {
        String url =  args.getString(BASE_URL)
                + Api.DISCUSSION + args.getString(DS_ID) + Api.HTML + "1?"
                + getAuth();
        return url;
    }

    //TODO could be moved into Application class
    // Collect authentication attributes of url request
    private String getAuth() throws UnsupportedEncodingException {
        String auth = "login=" + URLEncoder.encode(args.getString(USER), "UTF-8") + "&"
                + "password=" + URLEncoder.encode(args.getString(PASS), "UTF-8");
        return auth;
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

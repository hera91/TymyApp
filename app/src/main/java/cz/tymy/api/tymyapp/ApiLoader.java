package cz.tymy.api.tymyapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import cz.tymy.api.tymyapp.apimodel.Api;

public class ApiLoader extends AsyncTaskLoader<String> {
    public static final String CMD = "cmd";
    public static final String DS_LIST = "ds_list";
    public static final String POST_LIST = "post_list";
    public static final String DS_ID = "ds_id";
    public static final String USER = "user";
    public static final String PASS = "pass";
    public static final String SITE_URL = "site_url";
    private Bundle args;
    private String result;

    public ApiLoader(Context context, Bundle args) {
        super(context);
        this.args = args;
    }

    @Override
    public String loadInBackground() {
        HttpClient hc = new HttpClient(SITE_URL);
        String request;
        Log.v(DiscussionListActivity.TAG, "loadInBackground");
        try {
            // Discussion List
            if (DS_LIST.equals(this.args.getString(CMD))) {
                request = Api.getDsListUrl(args.getString(SITE_URL),
                        args.getString(USER), args.getString(PASS));
                result = hc.sendGet(request);
            // Posts List
            } else if (POST_LIST.equals(this.args.getString(CMD))) {
                request = Api.getPostListUrl(args.getString(SITE_URL), args.getString(DS_ID), "1",
                        args.getString(USER), args.getString(PASS));
                result = hc.sendGet(request);
            }
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

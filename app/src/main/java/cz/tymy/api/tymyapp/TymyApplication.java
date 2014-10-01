package cz.tymy.api.tymyapp;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import cz.tymy.api.tymyapp.apimodel.Api;

/**
 * Application singleton for sharing application state through runtime configuration changes
 */
public class TymyApplication extends Application {
    private static TymyApplication ourInstance;

    public static TymyApplication getInstance() {
        return ourInstance;
    }

    // Private constructor
//    private TymyApplication() {
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }

    // Current user, password and url
    private String user = "hera";
    private String pass = "bistromat";
    private String url = "http://pd.tymy.cz/" + Api.API;

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}

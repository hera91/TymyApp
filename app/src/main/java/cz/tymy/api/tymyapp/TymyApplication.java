package cz.tymy.api.tymyapp;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Application singleton for sharing application state through runtime configuration changes
 */
public class TymyApplication extends Application {
    private static TymyApplication ourInstance;
    // Local vars
    private Context ctx;
    // Current user, password and url
    private String name;
    private String user;
    private String pass;
    private String url;
    private long id;
    private String dsName; // Name of current discussion


    public static TymyApplication getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        ctx = getApplicationContext();
    }

    // Database Site info
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
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
    // Run-time info
    public String getDsName() {
        return dsName;
    }
    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public void toggleSite(long id) {
        this.id = id;
        Sites sites = new Sites(ctx);
        Cursor site = sites.getSite(id);
        int nameIndex = site.getColumnIndex(Sites.COLUMN_NAME);
        int urlIndex = site.getColumnIndex(Sites.COLUMN_URL);
        int userIndex = site.getColumnIndex(Sites.COLUMN_USER);
        int passIndex = site.getColumnIndex(Sites.COLUMN_PASS);
        if (site.getCount() < 1) {
//			title.setText(R.string.error);
//			title.setError("");
        } else {
            site.moveToNext();
            this.name = site.getString(nameIndex);
            this.url = site.getString(urlIndex);
            this.user = site.getString(userIndex);
            this.pass = site.getString(passIndex);
        }

        site.close();
        sites.close();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}

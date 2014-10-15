package cz.tymy.api.tymyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class AddSiteActivity extends FragmentActivity
        implements AddSiteFragment.OnAddSiteListener {
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_USER = "user";
    public static final String EXTRA_PASS = "pass";

    /**
     * if id < 0  than update existing Site
     */
    private long id = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        id = intent.getLongExtra(EXTRA_ID, -1);
        setContentView(R.layout.add_site_container);
    }

    @Override
    public long getSiteId() {
        return id;
    }

    public void onAddSite(String name, String url, String user, String pass) {
        Intent result = new Intent();
        result.putExtra(EXTRA_NAME, name);
        result.putExtra(EXTRA_URL, url);
        result.putExtra(EXTRA_USER, user);
        result.putExtra(EXTRA_PASS, pass);

        setResult(RESULT_OK, result);
        finish();
    }
}

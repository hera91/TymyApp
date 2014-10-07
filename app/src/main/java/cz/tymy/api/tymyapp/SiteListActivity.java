package cz.tymy.api.tymyapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class SiteListActivity extends FragmentActivity
        implements SiteListFragment.OnSiteClickedListener, AddSiteFragment.OnAddSiteListener {

    private TymyApplication appState;
    private static final int REQUEST_ADD_SITE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_list);
        appState = (TymyApplication) getApplication();
    }

    // Button Add Site clicked
    public void onAddSiteClicked(View v){
        Intent i = new Intent(this, AddSiteActivity.class);
        startActivityForResult(i, REQUEST_ADD_SITE);
    }

    // Get AddSiteActivity results and call
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_ADD_SITE){
            if(resultCode != RESULT_OK)
                return;

            String name = data.getStringExtra(AddSiteActivity.EXTRA_NAME);
            String url = data.getStringExtra(AddSiteActivity.EXTRA_URL);
            String user = data.getStringExtra(AddSiteActivity.EXTRA_USER);
            String pass = data.getStringExtra(AddSiteActivity.EXTRA_PASS);

            onAddSite(name, url, user, pass);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    // Process AddSiteActivity results
    public void onAddSite(String name, String url, String user, String pass) {
        Sites notes = new Sites(this);
        long id = notes.insertSite(name, url, user, pass);

        if(id > -1){
            ((SiteListFragment) getSupportFragmentManager().findFragmentById(
                    R.id.site_list)).updateList();
        } else{
            Toast.makeText(this, R.string.site_not_added, Toast.LENGTH_LONG).show();
        }
    }

    //General Option menu TODO not used yet
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.site_list, menu);
        return true;
    }

    // General option menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // Site row clicked
    @Override
    public void onSiteClicked(long id) {
        appState.toggleSite(id);
        showSite();
    }

    // Show discussion list for given Site
    private void showSite(){
        Intent i = new Intent(this, DiscussionListActivity.class);
        startActivity(i);
    }
}

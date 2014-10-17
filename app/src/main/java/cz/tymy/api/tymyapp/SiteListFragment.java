package cz.tymy.api.tymyapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * List Sites from database.
 */
public class SiteListFragment extends ListFragment {
    private static final int MENU_EDIT_ID = 0;
    private static final int MENU_DELETE_ID = 1;
    OnSiteClickedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_site_list, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerForContextMenu(getListView());
        updateList();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        listener.onSiteClicked(id);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnSiteClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSiteClickedListener");
        }
    }

    public void updateList() {
        Context ctx = getActivity();
        Sites sites = new Sites(ctx);

        String[] from = { Sites.COLUMN_NAME };
        int[] to = { android.R.id.text1 };

        ListAdapter adapter = new SimpleCursorAdapter(ctx,
                android.R.layout.simple_list_item_1, sites.getSites(), from,
                to, 0);

        setListAdapter(adapter);

        sites.close();
    }


    // Create Context menu -> Long Click on list item
    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, MENU_EDIT_ID, 0, R.string.edit);
        menu.add(0, MENU_DELETE_ID, 0, R.string.delete);
    }

    // Process Context menu selected
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case MENU_DELETE_ID:
                deleteSite(info.id);
                return true;
            case MENU_EDIT_ID:
                listener.onSiteEdit(info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /**
     * Delete Site from Database
     * @param id db _id
     */
    private void deleteSite(long id){
        Context ctx = getActivity();
        Sites notes = new Sites(ctx);

        if(notes.deleteSite(id)){
            Toast.makeText(ctx, R.string.site_deleted, Toast.LENGTH_SHORT).show();
            updateList();
        } else{
            Toast.makeText(ctx, R.string.site_not_deleted, Toast.LENGTH_SHORT).show();
        }
    }

    // Interface for list Item click, implemented in parent Activity
    public static interface OnSiteClickedListener {
        public void onSiteClicked(long id);
        public void onSiteEdit(long id);
    }
}

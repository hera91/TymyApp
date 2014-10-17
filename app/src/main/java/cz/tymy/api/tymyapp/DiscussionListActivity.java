package cz.tymy.api.tymyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


/**
 * An activity representing a list of Discussions. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DiscussionDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link DiscussionListFragment} and the item details
 * (if present) is a {@link DiscussionDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link DiscussionListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class DiscussionListActivity extends FragmentActivity
        implements DiscussionListFragment.Callbacks {

    public static final String TAG = "TymyApp";

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_list);

        // Application state singleton.
        TymyApplication appState = (TymyApplication) this.getApplication();

        if (findViewById(R.id.discussion_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            if (appState.isOnline()) {
                ((DiscussionListFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.discussion_list))
                        .setActivateOnItemClick(true);
            }
        }
        setTitle(appState.getName());
    }

    /**
     * Callback method from {@link DiscussionListFragment.Callbacks}
     * indicating that the item with the given K_ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(DiscussionDetailFragment.ARG_ITEM_ID, id);
            DiscussionDetailFragment fragment = new DiscussionDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.discussion_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item K_ID.
            Intent detailIntent = new Intent(this, DiscussionDetailActivity.class);
            detailIntent.putExtra(DiscussionDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}

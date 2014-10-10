package cz.tymy.api.tymyapp;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import java.io.IOException;

import cz.tymy.api.tymyapp.apireaders.ApiReader;

/**
 * A fragment representing a single Discussion detail screen.
 * This fragment is either contained in a {@link DiscussionListActivity}
 * in two-pane mode (on tablets) or a {@link DiscussionDetailActivity}
 * on handsets.
 */
public class DiscussionDetailFragment extends ListFragment
        implements LoaderManager.LoaderCallbacks<String>{

    /**
     * The fragment argument representing the item K_ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private String mDsId;

    /**
     * Adapter for PostList view
     */
    private PostAdapter mPostAdapter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DiscussionDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Application state singleton.
        TymyApplication appState = (TymyApplication) this.getActivity().getApplication();
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mDsId = getArguments().getString(ARG_ITEM_ID);
            Bundle args = new Bundle();
            args.putString(ApiLoader.CMD, ApiLoader.CMD_POST_LIST);
            args.putString(ApiLoader.DS_ID, mDsId);
            args.putString(ApiLoader.USER, appState.getUser());
            args.putString(ApiLoader.PASS, appState.getPass());
            args.putString(ApiLoader.SITE_URL, appState.getUrl());
            getLoaderManager().initLoader(Integer.parseInt(mDsId), args, this);
        }

        mPostAdapter = new PostAdapter(getActivity(), R.layout.row_post_layout);
        setListAdapter(mPostAdapter);
    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle args) {
        Log.v(DiscussionListActivity.TAG, "onCreateLoader");
        return new ApiLoader(getActivity(), args);
    }

    @Override
    public void onLoadFinished(Loader<String> stringLoader, String results) {
        Log.v(DiscussionListActivity.TAG, results);
        ApiReader ar = new ApiReader();
        try {
            mPostAdapter.clear();
            mPostAdapter.addAll(ar.readApiDsPostList(results));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> stringLoader) {
    }
}

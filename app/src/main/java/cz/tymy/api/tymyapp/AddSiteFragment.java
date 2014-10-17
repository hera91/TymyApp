package cz.tymy.api.tymyapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Add New Site fragment filled AddSiteActivity
 */
public class AddSiteFragment extends Fragment {
	OnAddSiteListener listener;
    private TymyApplication appState;
    private long id;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			listener = (OnAddSiteListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnAddSiteListener");
		}
        id = listener.getSiteId();
        appState = (TymyApplication) activity.getApplication();
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.add_site, container);
        Button submit = (Button)view.findViewById(R.id.add_site_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSubmitClicked();
            }
        });
		if (id != -1) {
            prefillFields(view);
            submit.setText(getResources().getString(R.string.add_site_update));
        }

		return view;
	}
	
	private void onSubmitClicked(){
		View root = getView();

        assert root != null;
        String name = ((EditText)root.findViewById(R.id.add_site_name)).getText().toString();
		String url = ((EditText)root.findViewById(R.id.add_site_url)).getText().toString();
        String user = ((EditText)root.findViewById(R.id.add_site_user)).getText().toString();
        String pass = ((EditText)root.findViewById(R.id.add_site_pass)).getText().toString();

		listener.onAddSite(name, url, user, pass);
	}

    private void prefillFields(View view){
        ((EditText)view.findViewById(R.id.add_site_name)).setText(appState.getName());
        ((EditText)view.findViewById(R.id.add_site_url)).setText(appState.getUrl());
        ((EditText)view.findViewById(R.id.add_site_user)).setText(appState.getUser());
        ((EditText)view.findViewById(R.id.add_site_pass)).setText(appState.getPass());
    }

	public static interface OnAddSiteListener {
		public long getSiteId();
        public void onAddSite(String name, String url, String user, String pass);
	}
}

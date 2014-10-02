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
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			listener = (OnAddSiteListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnAddSiteListener");
		}
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
		
		return view;
	}
	
	public void onSubmitClicked(){
		View root = getView();
		
		String name = ((EditText)root.findViewById(R.id.add_site_name)).getText().toString();
		String url = ((EditText)root.findViewById(R.id.add_site_url)).getText().toString();
        String user = ((EditText)root.findViewById(R.id.add_site_user)).getText().toString();
        String pass = ((EditText)root.findViewById(R.id.add_site_pass)).getText().toString();

		listener.onAddSite(name, url, user, pass);
	}

	public static interface OnAddSiteListener {
		public void onAddSite(String name, String url, String user, String pass);
	}
}

package cz.tymy.api.tymyapp;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cz.tymy.api.tymyapp.apimodel.ApiDs;
import cz.tymy.api.tymyapp.apimodel.DsDetail;

/**
 * Created by ph on 9/30/14.
 */
public class DiscussionAdapter extends ArrayAdapter<ApiDs> {
    private final Context context;

    public DiscussionAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    /**
     * Row layout model, class for access to row fields
     */
    private static class ViewHolder{
        TextView caption;
        TextView description;
        TextView newPosts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder;
        if (null == rowView) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.row_ds_layout, parent, false);
            viewHolder.caption = (TextView) rowView.findViewById(R.id.ds_caption);
            viewHolder.description = (TextView) rowView.findViewById(R.id.ds_description);
            viewHolder.newPosts = (TextView) rowView.findViewById(R.id.ds_new_posts);
            rowView.setTag(viewHolder);
        } else {
            // Using of ViewHolder pattern
            viewHolder = (ViewHolder) rowView.getTag();
        }

        viewHolder.caption.setText(getItem(position).getCaption());
        viewHolder.description.setText(getItem(position).getDescription());
        if (getItem(position).getNewPosts() > 0) {
            viewHolder.newPosts.setText(context.getResources().getString(R.string.ds_new_posts) +
                    Integer.toString(getItem(position).getNewPosts()));
        } else {
            viewHolder.newPosts.setText("");
//            viewHolder.newPosts.setVisibility(View.GONE);
        }
        return rowView;
    }
}

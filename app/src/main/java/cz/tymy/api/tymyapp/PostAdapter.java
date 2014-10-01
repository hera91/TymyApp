package cz.tymy.api.tymyapp;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import cz.tymy.api.tymyapp.apimodel.ApiDsPost;
import cz.tymy.api.tymyapp.apimodel.DsDetail;

/**
 * Created by ph on 9/30/14.
 */
public class PostAdapter extends ArrayAdapter<ApiDsPost> {
    private final Context context;
    private DsDetail mDsDetail;

    public PostAdapter(Context context, int resource, DsDetail dsDetail) {
        super(context, resource, dsDetail.getPosts());
        this.context = context;
        this.mDsDetail = dsDetail;
    }

    public PostAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.mDsDetail = new DsDetail();
    }

    /**
     * Row layout model, class for access to row fileds
     */
    private static class ViewHolder{
        TextView postText;
        TextView createdBy;
        TextView createdAt;
        TextView statusFlag;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ViewHolder viewHolder;
        if (null == rowView) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.row_post_layout, parent, false);
            viewHolder.postText = (TextView) rowView.findViewById(R.id.post_text);
            viewHolder.createdBy = (TextView) rowView.findViewById(R.id.post_createdby);
            viewHolder.createdAt = (TextView) rowView.findViewById(R.id.post_createdat);
            viewHolder.statusFlag = (TextView) rowView.findViewById(R.id.status_flag);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        viewHolder.postText.setText(Html.fromHtml(mDsDetail.getPosts().get(position).getPost()));
        viewHolder.createdBy.setText(Html.fromHtml((mDsDetail.getPosts().get(position).getCreatedByCallName())));
        viewHolder.createdAt.setText(Html.fromHtml(mDsDetail.getPosts().get(position).getCreatedAtStr()));
//        if (mDsDetail.getDs().getNewPosts() >= position)
//           viewHolder.statusFlag.setBackgroundColor(Color.rgb(255,255,255));
        return rowView;
    }

    public void addAll(DsDetail dsDetail){
        super.addAll(dsDetail.getPosts());
        this.mDsDetail = dsDetail;
    }
}

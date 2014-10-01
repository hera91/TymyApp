package cz.tymy.api.tymyapp.apimodel;

import android.text.Html;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ph on 9/29/14.
 */
public class ApiDsPost {
    /* API Msg description */
    /* K_ for "KEY" */
    public static final String K_ID = "id";
    public static final String K_DISCUSSIONID = "discussionId";
    public static final String K_POST = "post";
    public static final String K_CREATEDBYID = "createdById";
    public static final String K_CREATEDAT = "createdAt";
    public static final String K_UPDATEDBYID = "updatedById";
    public static final String K_STICKY = "sticky";
    public static final String K_CREATEDATSTR = "createdAtStr";
    public static final String K_CREATEDBY = "createdBy";

    // internal values
    private int id;
    private int discussionId;
    private String post;
    private int createdById;
    private String createdAt;
    private int updatedById;
    private boolean sticky;
    private String createdAtStr;
    private CreatedBy createdBy;

    public ApiDsPost(JSONObject jsonObject) {
        try {
            setId(jsonObject.getInt(K_ID));
            setDiscussionId(jsonObject.getInt(K_DISCUSSIONID));
            setPost(jsonObject.getString(K_POST));
            setCreatedById(jsonObject.optInt(K_CREATEDBYID));
            setCreatedAt(jsonObject.optString(K_CREATEDAT));
            setUpdatedById(jsonObject.optInt(K_UPDATEDBYID));
            setSticky(jsonObject.optBoolean(K_STICKY));
            setCreatedAtStr(jsonObject.optString(K_CREATEDATSTR));
            setCreatedBy(new CreatedBy(jsonObject.optJSONObject(K_CREATEDBY)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(int discussionId) {
        this.discussionId = discussionId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(int updatedById) {
        this.updatedById = updatedById;
    }

    public boolean isSticky() {
        return sticky;
    }

    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }

    public String getCreatedAtStr() {
        return createdAtStr;
    }

    public void setCreatedAtStr(String createdAtStr) {
        this.createdAtStr = createdAtStr;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByCallName() {
        return createdBy.getCallName();
    }

    public String toString(){
        return Html.fromHtml(getCreatedBy().getCallName() + ">>><br>" + getPost()).toString();
    }

    private class CreatedBy {
        public static final String K_CREATEDBY_ID = "id";
        public static final String K_CREATEDBY_LOGIN = "login";
        public static final String K_CREATEDBY_CALLNAME = "callName";
        public static final String K_CREATEDBY_PICTUREURL = "pictureUrl";

        private int id;
        private String login;
        private String callName;
        private String pictureUrl;

        public CreatedBy (JSONObject jsonObject) {
            try {
                setId(jsonObject.getInt(K_CREATEDBY_ID));
                setLogin(jsonObject.getString(K_CREATEDBY_LOGIN));
                setCallName(jsonObject.getString(K_CREATEDBY_CALLNAME));
                setPictureUrl(jsonObject.getString(K_CREATEDBY_PICTUREURL));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getCallName() {
            return callName;
        }

        public void setCallName(String callName) {
            this.callName = callName;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }
    }
}
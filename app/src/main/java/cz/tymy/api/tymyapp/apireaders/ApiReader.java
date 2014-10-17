package cz.tymy.api.tymyapp.apireaders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.tymy.api.tymyapp.apimodel.ApiDs;
import cz.tymy.api.tymyapp.apimodel.ApiDsPost;
import cz.tymy.api.tymyapp.apimodel.ApiException;
import cz.tymy.api.tymyapp.apimodel.ApiMsg;
import cz.tymy.api.tymyapp.apimodel.DsDetail;

/**
 * Created by ph on 9/24/14.
 */
public class ApiReader {

    /**
     * Read ApiMessage with list of discussions
     * @param api_msg
     * @return Lst of ApiDs
     * @throws IOException
     */
    public List<ApiDs> readApiDSesList(String api_msg) throws IOException, ApiException {
        List<ApiDs> apiDSes = new ArrayList<ApiDs>();

        ApiMsg msg = readApiMsg(api_msg);
        if (msg.getStatus().equals(ApiMsg.V_OK)){
            apiDSes = readApiDSesArray((JSONArray) msg.getData());
        }
        else if (msg.getStatus().equals(ApiMsg.V_ERROR)) {
            handleError(msg);
        }

        return apiDSes;
    }

    /**
     * Read api_msg contains Disscussion Posts List, also read additional info about Disscusion
     * @param api_msg
     * @return
     * @throws IOException
     */
    public DsDetail readApiDsPostList(String api_msg) throws IOException, ApiException {
        DsDetail dsDetail = new DsDetail();

        ApiMsg msg = readApiMsg(api_msg);
        if (msg.getStatus().equals(ApiMsg.V_OK)){
            try {
                // get 'data' from api_msg
                JSONObject msgData = (JSONObject) msg.getData();
                // get 'discussion' from api_msg
                ApiDs ds = new ApiDs(msgData.getJSONObject(ApiMsg.K_DISCUSSION));
                dsDetail.setDs(ds);
                // get 'posts' array from api_msg
                List<ApiDsPost> apiDsPosts = readApiDsPostArray(
                        msgData.getJSONArray(ApiMsg.K_POSTS),
                        dsDetail.getDs().getNewPosts());
                dsDetail.setPosts(apiDsPosts);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (msg.getStatus().equals(ApiMsg.V_ERROR)) {
            // get 'statusMessage' from api_msg
            handleError(msg);
        }

        return dsDetail;
    }

    /**
     * Read general API message
     * @param api_json
     * @return
     * @throws IOException
     */
    private ApiMsg readApiMsg(String api_json) {
        ApiMsg msg = new ApiDs();

        try {
            JSONObject jo = (JSONObject) new JSONTokener(api_json).nextValue();
            msg.setStatus(jo.getString(ApiMsg.K_STATUS));
            msg.setData(jo.opt(ApiMsg.K_DATA));
            msg.setStatusMessage(jo.optString(ApiMsg.K_STATUS_MESSAGE));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return msg;
    }

    /**
     * Parse JSONArray into List of ApiDs's
     * @param array
     * @return List<ApiDs>
     * @throws IOException
     */
    private List<ApiDs> readApiDSesArray(JSONArray array) {
        List<ApiDs> DSes = new ArrayList<ApiDs>();

        for (int i = 0; i < array.length(); i++){
            try {
                ApiDs mDs = new ApiDs(array.getJSONObject(i));
                DSes.add(mDs);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return DSes;
    }

    /**
     * Read API Json Posts array
     * @param array  API msg Json Posts array
     * @param newPosts  Number of New Posts
     * @return  List of ApiDsPost
     */
    private List<ApiDsPost> readApiDsPostArray(JSONArray array, int newPosts) {
        List<ApiDsPost> apiDsPosts = new ArrayList<ApiDsPost>();

        for (int i = 0; i < array.length(); i++){
            try {
                ApiDsPost mPost = new ApiDsPost(array.getJSONObject(i));
                if (mPost.isSticky()) {
                    newPosts++; //
                } else if (i < newPosts) {
                    //Post is not sticky and should be market as isNew
                    mPost.setNew(true);
                }
                apiDsPosts.add(mPost);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return apiDsPosts;
    }

    private void handleError(ApiMsg msg) throws ApiException {
        // get 'statusMessage' from api_msg
        String msgStatusMessage = msg.getStatusMessage();
        throw new ApiException(msgStatusMessage);
    }
}

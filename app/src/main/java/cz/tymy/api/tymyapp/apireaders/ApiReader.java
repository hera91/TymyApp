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
    public List<ApiDs> readApiDSesList(String api_msg) throws IOException {
        List<ApiDs> apiDSes = new ArrayList<ApiDs>();

        ApiMsg msg = readApiMsg(api_msg);
        if (msg.getStatus().equals(ApiMsg.V_OK)){
            apiDSes = readApiDSesArray((JSONArray) msg.getData());
        }
        return apiDSes;
    }

    /**
     * Read api_msg contains Disscussion Posts List, also read additional info about Disscusion
     * @param api_msg
     * @return
     * @throws IOException
     */
    public DsDetail readApiDsPostList(String api_msg) throws IOException {
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
                        (JSONArray) msgData.getJSONArray(ApiMsg.K_POSTS));
                dsDetail.setPosts(apiDsPosts);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return dsDetail;
    }

    private ApiMsg readApiMsg(String api_json) throws IOException {
        ApiMsg msg = new ApiDs();

        try {
            JSONObject jo = (JSONObject) new JSONTokener(api_json).nextValue();
            msg.setStatus(jo.getString(ApiMsg.K_STATUS));
            msg.setData(jo.get(ApiMsg.K_DATA));
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
    private List<ApiDs> readApiDSesArray(JSONArray array) throws IOException {
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

    private List<ApiDsPost> readApiDsPostArray(JSONArray array) {
        List<ApiDsPost> apiDsPosts = new ArrayList<ApiDsPost>();
        int newPosts = 3;

        for (int i = 0; i < array.length(); i++){
            try {
                ApiDsPost mPost = new ApiDsPost(array.getJSONObject(i));
                apiDsPosts.add(mPost);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return apiDsPosts;
    }
}

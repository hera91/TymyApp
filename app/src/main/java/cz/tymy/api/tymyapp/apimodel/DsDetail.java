package cz.tymy.api.tymyapp.apimodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ph on 9/30/14.
 */
public class DsDetail {
    // Details about Disscusion
    private ApiDs mDs;
    // List of Posts
    private List<ApiDsPost> mPosts = new ArrayList<ApiDsPost>();

    /**
     * Constructors
     */
    public DsDetail() {
    }
    public DsDetail(ApiDs ds) {
        this.mDs = ds;
    }
    public DsDetail(ApiDs ds, List<ApiDsPost> posts) {
        this.mDs = ds;
        this.mPosts = posts;
    }

    /**
     * Getters and Setters
     */
    public ApiDs getDs() {
        return mDs;
    }
    public void setDs(ApiDs ds) {
        this.mDs = ds;
    }
    public List<ApiDsPost> getPosts() {
        return mPosts;
    }
    public void setPosts(List<ApiDsPost> mPosts) {
        this.mPosts = mPosts;
    }

    /**
     * Add single ApiDsPost
     * @param post ApiDsPost
     */
    public void addPost(ApiDsPost post){
        mPosts.add(post);
    }

    /**
     * Add List of ApiDsPost's
     * @param posts List of ApiDsPost
     */
    public void addAllPosts(List<ApiDsPost> posts){
        mPosts.addAll(posts);
    }
}

package cz.tymy.api.tymyapp.apimodel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ph on 9/24/14.
 */
public class ApiDs extends ApiMsg {
    /* K_ for "KEY" */
    public static final String K_ID = "id";
    public static final String K_CAPTION = "caption";
    public static final String K_DESCRIPTION = "description";
    public static final String K_READRIGHTNAME = "readRightName";
    public static final String K_WRITERIGHTNAME = "writeRightName";
    public static final String K_DELETERIGHTNAME = "deleteRightName";
    public static final String K_STICKYRIGHTNAME = "stickyRightName";
    public static final String K_PUBLICREAD = "publicRead";
    public static final String K_STATUS = "status";
    public static final String K_EDITABLEPOSTS = "editablePosts";
    public static final String K_ORDER = "order";
    public static final String K_CANREAD = "canRead";
    public static final String K_CANWRITE = "canWrite";
    public static final String K_CANDELETE = "canDelete";
    public static final String K_CANSTICK = "canStick";
    public static final String K_NEWPOSTS = "newPosts";
    public static final String K_NUMBEROFPOSTS = "numberOfPosts";

    private int id;
    private String caption;
    private String description;
    private String readRightName;
    private String writeRightName;
    private String deleteRightName;
    private String stickyRightName;
    private boolean publicRead;
    private String status;
    private boolean editablePosts;
    private int order;
    private boolean canRead;
    private boolean canWrite;
    private boolean canDelete;
    private boolean canStick;
    private int newPosts;
    private int numberOfPosts;

    public ApiDs() {}
    public ApiDs(JSONObject jsonObject) {
        try {
            setId(jsonObject.getInt(K_ID));
            setCaption(jsonObject.getString(K_CAPTION));
            setDescription(jsonObject.getString(K_DESCRIPTION));
            setReadRightName(jsonObject.optString(K_READRIGHTNAME));
            setWriteRightName(jsonObject.optString(K_WRITERIGHTNAME));
            setDeleteRightName(jsonObject.optString(K_DELETERIGHTNAME));
            setStickyRightName(jsonObject.optString(K_STICKYRIGHTNAME));
            setPublicRead(jsonObject.optBoolean(K_PUBLICREAD));
            setStatus(jsonObject.optString(K_STATUS));
            setEditablePosts(jsonObject.optBoolean(K_EDITABLEPOSTS));
            setOrder(jsonObject.optInt(K_ORDER));
            setCanRead(jsonObject.optBoolean(K_CANREAD));
            setCanWrite(jsonObject.optBoolean(K_CANWRITE));
            setCanDelete(jsonObject.optBoolean(K_CANDELETE));
            setCanStick(jsonObject.optBoolean(K_CANSTICK));
            setNewPosts(jsonObject.optInt(K_NEWPOSTS));
            setNumberOfPosts(jsonObject.optInt(K_NUMBEROFPOSTS));
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReadRightName() {
        return readRightName;
    }

    public void setReadRightName(String readRightName) {
        this.readRightName = readRightName;
    }

    public String getWriteRightName() {
        return writeRightName;
    }

    public void setWriteRightName(String writeRightName) {
        this.writeRightName = writeRightName;
    }

    public String getDeleteRightName() {
        return deleteRightName;
    }

    public void setDeleteRightName(String deleteRightName) {
        this.deleteRightName = deleteRightName;
    }

    public String getStickyRightName() {
        return stickyRightName;
    }

    public void setStickyRightName(String stickyRightName) {
        this.stickyRightName = stickyRightName;
    }

    public boolean isPublicRead() {
        return publicRead;
    }

    public void setPublicRead(boolean publicRead) {
        this.publicRead = publicRead;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEditablePosts() {
        return editablePosts;
    }

    public void setEditablePosts(boolean editablePosts) {
        this.editablePosts = editablePosts;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isCanRead() {
        return canRead;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    public boolean isCanWrite() {
        return canWrite;
    }

    public void setCanWrite(boolean canWrite) {
        this.canWrite = canWrite;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public boolean isCanStick() {
        return canStick;
    }

    public void setCanStick(boolean canStick) {
        this.canStick = canStick;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    public int getNewPosts() {
        return newPosts;
    }

    public void setNewPosts(int newPosts) {
        this.newPosts = newPosts;
    }

    public void setNumberOfPosts(int numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public String toString(){
        return getId() + ": " + getCaption() + " [" + getNewPosts() + "]";
    }
}

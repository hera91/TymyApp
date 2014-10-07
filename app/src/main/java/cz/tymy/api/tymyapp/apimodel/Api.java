package cz.tymy.api.tymyapp.apimodel;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Provides description of API Tymy elements and basic method to handle API Requests
 */
public class Api {
    private static final String API = "/api/";
    private static final String DISCUSSIONS_ACCESSIBLE_WITH_NEW = "/discussions/accessible/withNew/?";
    private static final String DISCUSSION = "/discussion/";
    private static final String HTML = "/html/";

    // Collect authentication attributes of url request
    private static String getAuth(String user, String pass) throws UnsupportedEncodingException {
        String auth = "login=" + URLEncoder.encode(user, "UTF-8") + "&"
                + "password=" + URLEncoder.encode(pass, "UTF-8");
        return auth;
    }

    /**
     * Assort Discussions List API request URL
     * @param siteUrl  site URL site URL (e.g. 'http://dev/tymy.cz/')
     * @param user  Username
     * @param pass  Password
     * @return  API request URL
     * @throws UnsupportedEncodingException
     */
    public static String getDsListUrl(String siteUrl, String user, String pass) throws UnsupportedEncodingException {
        String url = siteUrl + API
                + DISCUSSIONS_ACCESSIBLE_WITH_NEW
                + getAuth(user, pass);
        return url;
    }

    /**
     * Assort Posts List API request URL
     * @param siteUrl  site URL (e.g. 'http://dev/tymy.cz/')
     * @param dsId  Discussion Id
     * @param page  Page to be displayed
     * @param user  Username
     * @param pass  Password
     * @return  API request URL
     * @throws UnsupportedEncodingException
     */
    public static String getPostListUrl(String siteUrl, String dsId, String page, String user, String pass) throws UnsupportedEncodingException {
        String url =  siteUrl + API
                + DISCUSSION + dsId + HTML + page + "?"
                + getAuth(user, pass);
        return url;
    }
}

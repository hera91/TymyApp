package cz.tymy.api.tymyapp;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.Assert;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testHttpClient() {
        HttpClient hc = new HttpClient();
        try {
            Assert.assertNotNull(hc.getJson("http://dev.tymy.cz/api/discussion/1/html/1?login=robot&password=f7cb3054af5084f5b0d84703c867349b"));
            Assert.assertNull(hc.getJson("http://nic.tymy.cz/api/discussion/1/html/1?login=robot&password=f7cb3054af5084f5b0d84703c867349b"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
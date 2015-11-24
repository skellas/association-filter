package com.ixxus.associationFilter.api.webscripts.json;

import com.ixxus.tests.BaseTest;
import com.tradeshift.test.remote.Remote;
import com.tradeshift.test.remote.RemoteTestRunner;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Ixxus on 11/18/2015.
 */
@Ignore // These sorts of tests don't work...
@RunWith(RemoteTestRunner.class)
@Remote(runnerClass = SpringJUnit4ClassRunner.class)
public class PostAssociationsByFilterGroupTest extends BaseTest {

    private static final String FILTER_GROUP_WEBSCRIPT_URL          = "http://localhost:8080/alfresco/service/ixxus/af/v1/abfg";
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void makeWebscriptCall() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost(FILTER_GROUP_WEBSCRIPT_URL);
            StringEntity params = new StringEntity("");
            request.addHeader("content-type", ContentType.APPLICATION_JSON.getMimeType());
            request.setEntity(params);

            HttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();


            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
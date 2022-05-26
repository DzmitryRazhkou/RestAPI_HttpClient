package com.qa.initial_tests;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Initial_Test extends TestBase {
    TestBase testBase;
    String url;
    String api_url;
    String URL;
    RestClient restClient;

    @BeforeMethod
    public void setUp() {
        testBase = new TestBase();
        url = prop.getProperty("URL");
        api_url = prop.getProperty("serviceURL");
        URL = url + api_url;

    }

    @Test
    public void getAPITest() throws IOException {
        restClient = new RestClient();
        restClient.get(URL);

    }
}

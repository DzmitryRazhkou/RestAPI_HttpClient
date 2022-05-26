package com.qa.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PostAPITest extends TestBase {

    TestBase testBase;
    String url;
    String api_url;
    String URL;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() {
        testBase = new TestBase();
        url = prop.getProperty("URL");
        api_url = prop.getProperty("serviceURL");
        URL = url + api_url;
    }

    @Test
    public void postAPITest() throws IOException {
        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

//      Jackson API:
        ObjectMapper mapper = new ObjectMapper();
        Users users = new Users("morpheus", "leader");   // Expected Users Object

//        Object to JSON
        mapper.writeValue(new File("/Users/dzmitryrazhkou/Documents" +
                "/Automation/API_Rest/src/main/java/com/qa/data/users.json"), users);

//        Java Object to JSON in String
        String usersJsonString = mapper.writeValueAsString(users);
        System.out.println(usersJsonString);

        closeableHttpResponse = restClient.post(URL, usersJsonString, headerMap);   // Call the API

//        StatusCode:
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201);

//        JSONString:
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJSON = new JSONObject(responseString);
        System.out.println("The response from API is: " + responseJSON);

//        JSON to Java Object:
        Users userResultObject = mapper.readValue(responseString, Users.class);   // Actual users objects
        System.out.println(userResultObject);

        System.out.println(users.getName().equals(userResultObject.getName()));
        System.out.println(users.getJob().equals(userResultObject.getJob()));

//        System.out.println(userResultObject.getId());
//        System.out.println(userResultObject.getCreatedAt());



    }
}

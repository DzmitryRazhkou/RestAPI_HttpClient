package com.qa.tests;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class GetAPITest extends TestBase {
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
    public void getAPITestWithoutHeaders() throws IOException {
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(URL);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code ---> " + statusCode);
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response JSON from API ---> " + responseJson);

//        Single Value assertion:
//        Per Pages:
        String perPageValue = TestUtil.getValueByJPath(responseJson, "per_page");
        System.out.println("Value of per page is ---> " + perPageValue);
        Assert.assertEquals(perPageValue,"6");

//        Total Pages:
        String totalPageValue = TestUtil.getValueByJPath(responseJson, "total");
        System.out.println("Value of total page is ---> " + totalPageValue);
        Assert.assertEquals(totalPageValue,"12");

//        Get the value from JSON Array:
        String id = TestUtil.getValueByJPath(responseJson, "data[0]/id");
        String email = TestUtil.getValueByJPath(responseJson, "data[0]/email");
        String firstName = TestUtil.getValueByJPath(responseJson, "data[0]/first_name");
        String lastName = TestUtil.getValueByJPath(responseJson, "data[0]/last_name");

        System.out.println("\nId: "+id+ "\nEmail: " +email+ "\nFirstname: "+firstName+ "\nLastname: " +lastName);
        Assert.assertEquals(id, "1");
        Assert.assertEquals(email, "george.bluth@reqres.in");
        Assert.assertEquals(firstName, "George");
        Assert.assertEquals(lastName, "Bluth");

//     3. All Headers:

        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<>();
        for (Header header : headersArray) {
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Headers Array ---> " + allHeaders);
    }


    @Test
    public void getAPITestWithHeaders() throws IOException {
        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("username", "test182");
        headerMap.put("password", "359");
        headerMap.put("Auth Token", "test182");
        closeableHttpResponse = restClient.get(URL, headerMap);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code ---> " + statusCode);
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response JSON from API ---> " + responseJson);



//        Single Value assertion:
//        Per Pages:
        String perPageValue = TestUtil.getValueByJPath(responseJson, "per_page");
        System.out.println("Value of per page is ---> " + perPageValue);
        Assert.assertEquals(perPageValue,"6");

//        Total Pages:
        String totalPageValue = TestUtil.getValueByJPath(responseJson, "total");
        System.out.println("Value of total page is ---> " + totalPageValue);
        Assert.assertEquals(totalPageValue,"12");

//        Get the value from JSON Array:
        String id = TestUtil.getValueByJPath(responseJson, "data[0]/id");
        String email = TestUtil.getValueByJPath(responseJson, "data[0]/email");
        String firstName = TestUtil.getValueByJPath(responseJson, "data[0]/first_name");
        String lastName = TestUtil.getValueByJPath(responseJson, "data[0]/last_name");

        System.out.println("\nId: "+id+ "\nEmail: " +email+ "\nFirstname: "+firstName+ "\nLastname: " +lastName);
        Assert.assertEquals(id, "1");
        Assert.assertEquals(email, "george.bluth@reqres.in");
        Assert.assertEquals(firstName, "George");
        Assert.assertEquals(lastName, "Bluth");

//     3. All Headers:

        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<>();
        for (Header header : headersArray) {
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Headers Array ---> " + allHeaders);

    }
}

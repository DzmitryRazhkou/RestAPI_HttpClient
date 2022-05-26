package com.qa.initial_tests;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class Initial_RestClient {

    //    1. GET Method:

    public void get(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);   //https get request
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);   // hit the GET URL

//     2. Status Code:

        int status = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code ---> " + status);
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response JSON from API ---> " + responseJson);

//     3. All Headers:

        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<>();
        for (Header header : headersArray) {
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Headers Array ---> " + allHeaders);

    }
}


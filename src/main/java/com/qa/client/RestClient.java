package com.qa.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

//    1. GET Method:

    public CloseableHttpResponse get(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);   //https get request
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);   // hit the GET URL
        return closeableHttpResponse;
    }

    //    1. GET Method with Headers:

    public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);   //https get request

        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);   // hit the GET URL
        return closeableHttpResponse;
    }

    //    1. POST Method with Headers:

    public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);   //https post request
        httpPost.setEntity(new StringEntity(entityString));   // for payload

        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);   // hit the GET URL
        return closeableHttpResponse;
    }
}

package com.nika.web.java.rest;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class BlackBoxTest {

    @Test
    public void testJsonNoWord() throws IOException {
        JSONObject expected = new JSONObject();
        expected.put("original", "ANKARA");
        expected.put("reversed", "ARAKNA");
        // TODO propertize the hostname, port, and uri
        HttpUriRequest request = new HttpGet("http://localhost:8080/propmg/reverser/jsonbasedreverser");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        Assert.assertEquals(response.getFirstHeader("Content-Type").getValue(), "application/json");
        JSONObject actual = new JSONObject(EntityUtils.toString(response.getEntity()));
        // TODO actual.equals(expected) is not working but should be preferred
        Assert.assertEquals(actual.get("original"), expected.get("original"));
        Assert.assertEquals(actual.get("reversed"), expected.get("reversed"));
    }

    @Test
    public void testJsonWord() throws IOException {
        JSONObject expected = new JSONObject();
        expected.put("original", "foobar");
        expected.put("reversed", "raboof");
        HttpUriRequest request = new HttpGet("http://localhost:8080/propmg/reverser/jsonbasedreverser/foobar");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        Assert.assertEquals(response.getFirstHeader("Content-Type").getValue(), "application/json");
        JSONObject actual = new JSONObject(EntityUtils.toString(response.getEntity()));
        Assert.assertEquals(actual.get("original"), expected.get("original"));
        Assert.assertEquals(actual.get("reversed"), expected.get("reversed"));
    }
}

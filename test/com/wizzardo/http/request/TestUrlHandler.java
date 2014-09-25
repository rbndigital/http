package com.wizzardo.http.request;

import com.wizzardo.http.UrlHandler;
import com.wizzardo.http.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author: wizzardo
 * Date: 25.09.14
 */
public class TestUrlHandler extends ServerTest {

    @Test
    public void testMapping() throws IOException {
        handler = new UrlHandler()
                .append("/action1", request -> new Response().setBody("action1"))
                .append("/action2", request -> new Response().setBody("action2"))
                .append("/$action/1", request -> new Response().setBody("action5"))
                .append("/2/$action?", request -> new Response().setBody(request.paramWithDefault("action", "default")))
                .append("/3/$action?/${id}?", request ->
                        new Response().setBody(request.paramWithDefault("action", "action2") + "+" + request.paramWithDefault("id", "action3")))
        ;

        Assert.assertEquals("action1", makeRequest("/action1").get().asString());
        Assert.assertEquals("action2", makeRequest("/action2").get().asString());
        Assert.assertEquals("action5", makeRequest("/action5/1").get().asString());
        Assert.assertEquals("action6", makeRequest("/2/action6").get().asString());
        Assert.assertEquals("default", makeRequest("/2").get().asString());
        Assert.assertEquals("ololo+123", makeRequest("/3/ololo/123").get().asString());
        Assert.assertEquals("action2+action3", makeRequest("/3").get().asString());
    }
}

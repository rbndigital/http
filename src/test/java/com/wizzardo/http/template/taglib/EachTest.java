package com.wizzardo.http.template.taglib;

import com.wizzardo.http.template.*;
import com.wizzardo.http.template.taglib.g.Each;
import com.wizzardo.tools.xml.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

/**
 * Created by wizzardo on 26.04.15.
 */
public class EachTest {

    @Before
    public void setup(){
        TagLib.findTags(Collections.singletonList(Each.class));
    }

    @Test
    public void test_1() {
        Node n = Node.parse("<div><g:each in=\"[1,2,3]\" var=\"i\">$i<br/></g:each></div>", true);

        RenderableList l = new RenderableList();
        ViewRenderer.prepare(n.children(), l, "", "");

        RenderResult result = l.get(new Model());

        Assert.assertEquals("<div>\n" +
                "        1\n" +
                "        <br/>\n" +
                "        2\n" +
                "        <br/>\n" +
                "        3\n" +
                "        <br/>\n" +
                "</div>\n", result.toString());
    }
}
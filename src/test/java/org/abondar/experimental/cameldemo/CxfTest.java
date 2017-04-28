package org.abondar.experimental.cameldemo;


import org.abondar.experimental.cameldemo.cxf.CxfConfig;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class CxfTest extends CamelSpringTestSupport {
    @Override
    protected AbstractApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(CxfConfig.class);
        ctx.refresh();
        return ctx;
    }

    @Test
    public void testOk() throws Exception{
        List<Object> params = new ArrayList<>();
        params.add("Alex");
        params.add(1);


        String reply = template.requestBody("cxf:bean:demoEndpoint",params,String.class);
        assertEquals("OK",reply);
    }
}

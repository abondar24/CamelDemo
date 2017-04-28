package org.abondar.experimental.cameldemo;


import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.io.File;

public class TestOne extends CamelTestSupport{

    public void setUp() throws Exception{
        deleteDirectory("target/inbox");
        deleteDirectory("target/outbox");
        super.setUp();
    }


    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file://target/inbox")
                        .to("file://target/outbox");
            }
        };
    }



    @Test
    public void testMoveFile() throws Exception {
        template.sendBodyAndHeader("file://target/inbox","Hi",
                Exchange.FILE_NAME,"hello.txt");

        Thread.sleep(1000);

        File target = new File("target/outbox/hello.txt");

        String content = context.getTypeConverter().convertTo(String.class,target);
        assertEquals("Hi", content);
    }
}

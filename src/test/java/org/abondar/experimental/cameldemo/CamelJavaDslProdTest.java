package org.abondar.experimental.cameldemo;


import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.io.File;

public class CamelJavaDslProdTest extends CamelTestSupport {


    private String inboxDir;
    private String outboxDir;

    @EndpointInject(uri = "file:{{file.inbox}}")
    private ProducerTemplate inbox;

    @Override
    protected CamelContext createCamelContext() throws Exception {

        CamelContext context = super.createCamelContext();

        PropertiesComponent prop = context.getComponent("properties", PropertiesComponent.class);
        prop.setLocation("classpath:prod.properties");

        return context;
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:{{file.inbox}}")
                        .to("file:{{file.outbox}}");
            }
        };
    }


    public void setUp() throws Exception {
        super.setUp();
        inboxDir = context.resolvePropertyPlaceholders("{{file.inbox}}");
        outboxDir = context.resolvePropertyPlaceholders("{{file.outbox}}");

        deleteDirectory(inboxDir);
        deleteDirectory(outboxDir);
    }


    @Test
    public void testMoveFile() throws Exception {
        context.setTracing(true);

        inbox.sendBodyAndHeader("Hello World", Exchange.FILE_NAME, "hello.txt");

        Thread.sleep(1000);

        File target = new File(outboxDir + "/hello.txt");

        String content = context.getTypeConverter().convertTo(String.class, target);
        assertEquals("Hello World", content);
    }

}

package org.abondar.experimental.cameldemo;


import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;

import org.apache.camel.spi.PropertiesComponent;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CamelJavaDslProdTest extends CamelTestSupport {


    private String outboxDir;

    @EndpointInject(value = "file:{{file.inbox}}")
    private ProducerTemplate inbox;

    @Override
    protected CamelContext createCamelContext() throws Exception {

        CamelContext context = super.createCamelContext();

        PropertiesComponent prop = context.getPropertiesComponent();
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


    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();

    }


    @Test
    public void testMoveFile() throws Exception {
        MockEndpoint resultEndpoint = getMockEndpoint("mock:file:"+inbox);

        context.setTracing(true);
        inbox.sendBodyAndHeader("Hello World", Exchange.FILE_NAME, "hello.txt");
        resultEndpoint.assertIsSatisfied();

    }

}

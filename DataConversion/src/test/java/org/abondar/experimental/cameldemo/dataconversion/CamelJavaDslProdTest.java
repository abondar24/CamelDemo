package org.abondar.experimental.cameldemo.dataconversion;


import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;


import org.junit.jupiter.api.Test;


public class CamelJavaDslProdTest extends CamelTestSupport {

    @EndpointInject(value = "file:inbox")
    private ProducerTemplate inbox;

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:inbox")
                        .to("file:/target/outbox");
            }
        };
    }

    @Test
    public void testMoveFile() throws Exception {
        MockEndpoint resultEndpoint = getMockEndpoint("mock:file:" + inbox);

        context.setTracing(true);
        inbox.sendBodyAndHeader("Hello World", Exchange.FILE_NAME, "hello.txt");
        resultEndpoint.assertIsSatisfied();

    }

}

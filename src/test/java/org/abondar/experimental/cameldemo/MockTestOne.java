package org.abondar.experimental.cameldemo;


import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;


public class MockTestOne extends CamelTestSupport {


    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();
        camelContext.addComponent("jms", camelContext.getComponent("seda"));
        return camelContext;
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("jms:topic:quote").to("mock:quote");
            }
        };
    }


    @Test
    public void testQuote() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:quote", true);
        mock.expectedMessageCount(1);
        template.sendBody("jms:topic:quote", "Camel is good");

        mock.assertIsSatisfied();
    }


    @Test
    public void testQuotes() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:quote", true);
        mock.expectedBodiesReceived("Camel is good", "Camel is shit");

        template.sendBody("jms:topic:quote", "Camel is good");
        template.sendBody("jms:topic:quote", "Camel is shit");

        mock.assertIsSatisfied();
    }

    @Test
    public void testIsCamelMessage() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:quote", true);
        mock.expectedBodiesReceived("Camel is good", "Camel is shit");

        mock.message(0).body().contains("good");
        mock.message(1).body().contains("shit");

        template.sendBody("jms:topic:quote", "Camel is good");
        template.sendBody("jms:topic:quote", "Camel is shit");


        assertMockEndpointsSatisfied();

    }

    @Test
    public void testGap() throws Exception {
        final MockEndpoint mock = getMockEndpoint("mock:quote");
        mock.expectedMessageCount(3);
        mock.expects(() -> {
            int last = 0;
            for (Exchange exchange : mock.getExchanges()) {
                int current = exchange.getIn().getHeader("Counter", Integer.class);
                if (current <= last) {
                    fail("Counter is not greater than last counter");
                } else if (current - last != 1) {
                    fail("Gap detected: last: " + " current: " + current);
                }
                last = current;
            }
        });

        template.sendBodyAndHeader("jms:topic:quote","A","Counter",1);
        template.sendBodyAndHeader("jms:topic:quote","B","Counter",2);
        template.sendBodyAndHeader("jms:topic:quote","C","Counter",3);

        mock.assertIsSatisfied();
    }


}


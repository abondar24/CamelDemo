package org.abondar.experimental.cameldemo.concurency;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;


public class FutureTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FutureTest.class);

    @Test
    public void testFuture() throws Exception {
        DefaultCamelContext camelctx = new DefaultCamelContext();
        camelctx.addRoutes(createRouteBuilder());
        ProducerTemplate template=camelctx.createProducerTemplate();

        LOGGER.info("Submitting");
        camelctx.start();

        Future<String> future = template.asyncRequestBody("seda:quote", "Hello", String.class);
        LOGGER.info("Task submitted");


        String answer = future.get();
        LOGGER.info("Answer: " + answer);
       camelctx.stop();
    }

    @Test
    public void testFutureDone() throws Exception {
        DefaultCamelContext camelctx = new DefaultCamelContext();
        camelctx.addRoutes(createRouteBuilder());
        ProducerTemplate template=camelctx.createProducerTemplate();
        camelctx.start();

        LOGGER.info("Submitting");
        Future<String> future = template.asyncRequestBody("seda:quote", "Hello", String.class);
        LOGGER.info("Task submitted");

        boolean done = false;
        while (!done) {
            done = future.isDone();
            LOGGER.info("Done? " + done);
            if (!done) {
                Thread.sleep(5000);
            }
        }
        camelctx.stop();
        String answer = future.get();
        LOGGER.info("Answer: " + answer);
    }


    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("seda:quote")
                        .log("Starting route ${body}")
                        .delay(5000)
                        .transform().constant("Camel good")
                        .log("Done");
            }
        };
    }
}

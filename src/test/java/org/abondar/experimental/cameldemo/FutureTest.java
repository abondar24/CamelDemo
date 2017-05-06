package org.abondar.experimental.cameldemo;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.concurrent.Future;


public class FutureTest extends CamelTestSupport {


    @Test
    public void testFuture() throws Exception{
        log.info("Submitting");
        Future<String> future = template.asyncRequestBody("seda:quote","Hello",String.class);
        log.info("Task submitted");


        String answer = future.get();
        log.info("Answer: "+answer);
    }

    @Test
    public void testFutureDone() throws Exception{
        log.info("Submitting");
        Future<String> future = template.asyncRequestBody("seda:quote","Hello",String.class);
        log.info("Task submitted");

        boolean done = false;
        while (!done){
            done = future.isDone();
            log.info("Done? "+done);
            if (!done){
                Thread.sleep(2000);
            }
        }

        String answer = future.get();
        log.info("Answer: "+answer);
    }


    @Override
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

package org.abondar.experimental.cameldemo;

import org.apache.camel.Exchange;
import org.apache.camel.spi.Synchronization;
import org.apache.camel.support.SynchronizationAdapter;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class CallbackTest extends CamelTestSupport {

    @Test
    public void testCallback() throws Exception{
        final List<String> relates = new ArrayList<>();
        final CountDownLatch latch = new CountDownLatch(5);

        Synchronization callback = new SynchronizationAdapter(){
            @Override
            public void onComplete(Exchange exchange) {
                relates.add(exchange.getOut().getBody(String.class));
                latch.countDown();
            }

            @Override
            public void onFailure(Exchange exchange) {
                latch.countDown();
            }
        };

        String body = "bumper";
        for (int i=0;i<5;i++){
            template.asyncCallbackRequestBody("seda:partner:"+i,body,callback);
        }

        log.info("Send "+5+ " messages to partners.");
        boolean all = latch.await(1500, TimeUnit.MILLISECONDS);
        log.info("Got "+relates.size() + " replies, is all? "+all);
        relates.forEach(related ->log.info("Related item category is:" +related));
    }
}

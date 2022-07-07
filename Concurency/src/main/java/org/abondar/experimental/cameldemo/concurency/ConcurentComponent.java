package org.abondar.experimental.cameldemo.concurency;


import org.apache.camel.ComponentConfiguration;
import org.apache.camel.Endpoint;


import org.apache.camel.EndpointConfiguration;
import org.apache.camel.support.DefaultComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConcurentComponent extends DefaultComponent implements Runnable {

    private static final Log LOG = LogFactory.getLog(ConcurentComponent.class);
    private ScheduledExecutorService service;

    @Override
    public void run() {
        LOG.info("I am running");
    }

    @Override
    protected Endpoint createEndpoint(String s, String s1, Map<String, Object> map) throws Exception {
        return null;
    }

    @Override
    public void start() {
        service = getCamelContext().getExecutorServiceManager()
                .newScheduledThreadPool(this, "Back task", 1);
        service.scheduleWithFixedDelay(this, 1, 1, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        getCamelContext().getExecutorServiceManager().shutdown(service);

    }


    @Override
    public void suspend() {

    }

    @Override
    public void resume() {


    }

    @Override
    public void shutdown() {
         super.shutdown();
    }


}

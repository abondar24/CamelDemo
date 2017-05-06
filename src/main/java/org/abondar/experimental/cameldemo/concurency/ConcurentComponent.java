package org.abondar.experimental.cameldemo.concurency;


import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConcurentComponent extends DefaultComponent implements Runnable{

    private static final Log LOG = LogFactory.getLog(ConcurentComponent.class);
    private ScheduledExecutorService service;

    @Override
    public void run() {
        LOG.info("I am running");
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        service = getCamelContext().getExecutorServiceManager()
                .newScheduledThreadPool(this,"Back task",1);
        service.scheduleWithFixedDelay(this,1,1, TimeUnit.SECONDS);
    }

    @Override
    protected void doStop() throws Exception {
        getCamelContext().getExecutorServiceManager().shutdown(service);
        super.doStop();
    }

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        return null;
    }
}

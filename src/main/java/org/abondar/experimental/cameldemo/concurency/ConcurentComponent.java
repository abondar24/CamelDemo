package org.abondar.experimental.cameldemo.concurency;


import org.apache.camel.Endpoint;
import org.apache.camel.component.extension.ComponentExtension;
import org.apache.camel.impl.DefaultComponent;
import org.apache.camel.spi.PropertyConfigurer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
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
    protected Endpoint createEndpoint(String s, String s1, Map<String, Object> map) throws Exception {
        return null;
    }

    @Override
    public Endpoint createEndpoint(String uri, Map<String, Object> parameters) throws Exception {
        return null;
    }

    @Override
    public PropertyConfigurer getComponentPropertyConfigurer() {
        return super.getComponentPropertyConfigurer();
    }

    @Override
    public PropertyConfigurer getEndpointPropertyConfigurer() {
        return super.getEndpointPropertyConfigurer();
    }

    @Override
    public Collection<Class<? extends ComponentExtension>> getSupportedExtensions() {
        return super.getSupportedExtensions();
    }

    @Override
    public <T extends ComponentExtension> Optional<T> getExtension(Class<T> extensionType) {
        return super.getExtension(extensionType);
    }

    @Override
    public String getDefaultName() {
        return super.getDefaultName();
    }

    @Override
    public boolean isAutowiredEnabled() {
        return super.isAutowiredEnabled();
    }

    @Override
    public void build() {
        super.build();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }


    @Override
    public void start() {
        //super.start();
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
    public void resume()  {


    }

    @Override
    public void shutdown()  {
     // super.shutdown();
    }
}

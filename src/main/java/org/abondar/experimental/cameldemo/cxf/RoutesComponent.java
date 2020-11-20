package org.abondar.experimental.cameldemo.cxf;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RoutesComponent extends SpringRouteBuilder {


    @Override
    public void configure() throws Exception {
        from("cxf:bean:demoEndpoint")
                .to("seda:demo")
        .transform(constant("OK"));

        from("seda:demo")
                .to("mock:end");
    }
}

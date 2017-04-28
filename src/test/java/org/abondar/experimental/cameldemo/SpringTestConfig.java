package org.abondar.experimental.cameldemo;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringTestConfig extends CamelConfiguration{

    @Override
    protected void setupCamelContext(CamelContext camelContext) throws Exception {
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file://target/inbox")
                        .to("file://target/outbox");
            }
        });
        super.setupCamelContext(camelContext);
    }
}

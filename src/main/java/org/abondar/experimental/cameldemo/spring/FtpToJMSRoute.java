package org.abondar.experimental.cameldemo.spring;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FtpToJMSRoute extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {
        from("ftp://172.17.0.3?username=abondar&password=berkley217")
                .to("jms:queueSS1");
    }
}

package org.abondar.experimental.cameldemo.cxf;


import org.apache.camel.CamelConfiguration;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CxfConfig implements CamelConfiguration {

    @Bean
    public CxfEndpoint demoEndpoint(){
        CxfEndpoint endpoint = new CxfEndpoint();
        endpoint.setAddress("http://localhost:9090/demo/");
        endpoint.setServiceClass(DemoEndpoint.class);
        return endpoint;
    }
}

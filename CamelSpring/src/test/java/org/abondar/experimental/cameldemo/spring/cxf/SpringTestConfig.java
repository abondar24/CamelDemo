package org.abondar.experimental.cameldemo.spring.cxf;

import org.apache.camel.CamelConfiguration;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
public class SpringTestConfig implements CamelConfiguration {

    public CxfEndpoint demoEndpoint(){
        CxfEndpoint endpoint = new CxfEndpoint();
        endpoint.setAddress("mock://cxf_test");
        endpoint.setServiceClass(DemoEndpoint.class);
        return endpoint;
    }
}

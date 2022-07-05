package org.abondar.experimental.cameldemo.spring.cxf;


import javax.jws.WebService;

@WebService
public interface DemoEndpoint {
    String call(String name, int id);
}

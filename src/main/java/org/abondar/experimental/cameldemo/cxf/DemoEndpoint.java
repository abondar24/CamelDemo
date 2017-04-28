package org.abondar.experimental.cameldemo.cxf;


import javax.jws.WebService;

@WebService
public interface DemoEndpoint {
    String call(String name, int id);
}

package org.abondar.experimental.cameldemo.mina;


import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class SmallStupidServer {

    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("mina:tcp://localhost:8031?textline=true&sync=false")
                        .to("stream:out");
            }
        });

        context.start();
        Thread.sleep(1000000000);
        context.stop();

    }
}

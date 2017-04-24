package org.abondar.experimental.cameldemo;


import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ContentBasedFileCopier {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        RouteBuilder builder = new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:home/abondar/Documents?noop=false")
                        .choice()
                        .when(header("CamelFileName")
                        .endsWith(".xml"))
                        .to("file:home/abondar/Downloads/ss1")
                        .when(header("CamelFileName")
                                .endsWith(".doc"))
                        .to("file:home/abondar/Downloads").end();

                from("file:home/abondar/Downloads/ss1").process(
                        exchange -> System.out.println(exchange.getIn().getBody()));
            }
        };

        context.addRoutes(builder);

        context.start();
        Thread.sleep(10000);
        context.stop();
    }
}

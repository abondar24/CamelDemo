package org.abondar.experimental.cameldemo.file;


import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ContentBasedFileCopier {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:/home/abondar/Documents?noop=true")
                        .choice()
                        .when(header("CamelFileName")
                                .endsWith(".xml"))
                        .to("file:/home/abondar/Downloads/ss1")
                        .endChoice()
                        .when(header("CamelFileName")
                                .endsWith(".pdf"))
                        .to("file:/home/abondar/Downloads/ss2")
                        .end();

            }
        });

        context.start();
        Thread.sleep(10000);
        context.stop();
    }
}

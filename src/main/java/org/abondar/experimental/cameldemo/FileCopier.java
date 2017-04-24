package org.abondar.experimental.cameldemo;


import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileCopier {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        RouteBuilder builder = new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:home/abondar/Documents/ss1.doc?noop=true")
                        .to("file:home/abondar/Downloads/ss1");
            }
        };

        context.addRoutes(builder);

        context.start();
        Thread.sleep(10000);
        context.stop();
        }
    }


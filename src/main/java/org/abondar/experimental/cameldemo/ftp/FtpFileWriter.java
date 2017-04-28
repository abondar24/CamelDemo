package org.abondar.experimental.cameldemo.ftp;


import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FtpFileWriter {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("stream:in?promptMessage=Enter message:")
                        .to("ftp://abondar:berkley217@172.17.0.4/home/out.txt");
            }
        });

        context.start();
        Thread.sleep(10000);
        context.stop();

    }
}

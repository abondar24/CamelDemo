package org.abondar.experimental.cameldemo.mina;


import org.abondar.experimental.cameldemo.command.Command;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class MinaServer implements Command {


    @Override
    public void execute() {

        try {
            CamelContext context = new DefaultCamelContext();

            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from("mina:tcp://localhost:8031?textline=true&sync=false")
                            .to("stream:out");
                }
            });


            context.start();
            Thread.sleep(1000000000);
            context.stop();
        } catch (Exception ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }

    }
}

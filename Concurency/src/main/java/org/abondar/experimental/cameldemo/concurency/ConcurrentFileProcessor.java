package org.abondar.experimental.cameldemo.concurency;


import org.abondar.experimental.cameldemo.command.Command;
import org.abondar.experimental.cameldemo.dataconversion.TextToCSVProcessor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


public class ConcurrentFileProcessor implements Command {

    @Override
    public void execute() {
        try {
            CamelContext context = new DefaultCamelContext();

            context.addRoutes(new RouteBuilder() {


                @Override
                public void configure() throws Exception {
                    from("file:/home/abondar/Documents?charset=UTF-8")
                            .log("Starting processing file: ${header.CamelFileName}")
                            .split(body().tokenize("\n")).streaming()
                            .process(new TextToCSVProcessor())
                            .to("seda:update?concurrentConsumers=20")
                            .end()
                            .log("Done processing file: ${header.CamelFileName}");

                }
            });

            context.start();
            Thread.sleep(10000);
            context.stop();

        } catch (Exception ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
}


package org.abondar.experimental.cameldemo.file;


import org.abondar.experimental.cameldemo.command.Command;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FilePrinter  implements Command {

    @Override
    public void execute() {
         try {
             CamelContext context = new DefaultCamelContext();

             context.addRoutes(new RouteBuilder() {
                 @Override
                 public void configure() throws Exception {
                     from("file:/home/abondar/Documents?noop=true")
                             .to("stream:out");
                 }
             });

             context.start();
             Thread.sleep(10000);
             context.stop();

         }catch (Exception ex){
             System.err.println(ex.getMessage());
             System.exit(1);
         }
    }
}

package org.abondar.experimental.cameldemo.file;


import org.abondar.experimental.cameldemo.command.Command;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileWriter implements Command {

    @Override
    public void execute() {
         try {
             CamelContext context = new DefaultCamelContext();

             context.addRoutes(new RouteBuilder() {
                 @Override
                 public void configure() throws Exception {
                     from("stream:in?promptMessage=Enter message:")
                             .to("file:/home/abondar/Downloads/ss1?fileName=out.txt");
                 }
             });

             context.start();
             Thread.sleep(3500);
             context.stop();
         } catch (Exception ex){
             System.err.println(ex.getMessage());
             System.exit(1);
         }
    }
}

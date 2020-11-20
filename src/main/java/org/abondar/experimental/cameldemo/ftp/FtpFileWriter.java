package org.abondar.experimental.cameldemo.ftp;


import org.abondar.experimental.cameldemo.command.Command;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FtpFileWriter implements Command {

    @Override
    public void execute() {
          try {
              CamelContext context = new DefaultCamelContext();

              context.addRoutes(new RouteBuilder() {
                  @Override
                  public void configure() throws Exception {
                      from("stream:in?promptMessage=Enter message:")
                              .to("ftp://admin:admin@localhost/home/out.txt");
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

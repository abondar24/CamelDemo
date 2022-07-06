package org.abondar.experimental.cameldemo.concurency;


import org.abondar.experimental.cameldemo.command.Command;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class ConcurrentComponentCommand implements Command {

    @Override
    public void execute() {
       try {
           CamelContext context = new DefaultCamelContext();


           context.addComponent("cc",new ConcurentComponent());
           context.getComponent("cc",ConcurentComponent.class).start();



           context.start();
           System.out.println("10 second wait");
           Thread.sleep(10000);
           context.stop();
       } catch (Exception ex){
           System.err.println(ex.getMessage());
           System.exit(1);
       }
    }
}

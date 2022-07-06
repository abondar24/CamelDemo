package org.abondar.experimental.cameldemo.dataconversion.command;


import org.abondar.experimental.cameldemo.command.Command;
import org.abondar.experimental.cameldemo.dataconversion.TextBean;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.BindyType;

public class TextToCsvBindy implements Command {

    @Override
    public void execute() {
       try {
           TextBean bean = new TextBean("1123442","555544",
                   "12/10/2013","7", new String[]{"s1","t21","s34"});

           CamelContext context = new DefaultCamelContext();

           context.addRoutes(new RouteBuilder() {
               @Override
               public void configure() throws Exception {
                   from("timer://tt1?fixedRate=true&period=300")
                           .setBody(constant(bean))
                           .marshal()
                           .bindy(BindyType.Csv,TextBean.class)
                           .to("file:/home/abondar/Downloads?fileName=out1.csv");
               }
           });

           context.start();
           Thread.sleep(1000);
           context.stop();
       } catch (Exception ex){
           System.err.println(ex.getMessage());
           System.exit(1);
       }
    }
}

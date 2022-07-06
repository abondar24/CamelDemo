package org.abondar.experimental.cameldemo.ftp;


import org.abondar.experimental.cameldemo.command.Command;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;
import java.util.Set;

public class FtpToJmsCopier implements Command {

    @Override
    public void execute() {
        try {
            CamelContext camelContext = new DefaultCamelContext();
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));


            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public Set<String> updateRoutesToCamelContext(CamelContext context) throws Exception {
                    return null;
                }

                @Override
                public void configure() throws Exception {
                    from("ftp://localhost?username=admin&password=admin")
                            .process(exchange ->
                                    System.out.println("SS-21: "+exchange.getIn().getHeader("CamelFileName")))
                            .to("jms:jms.queueCamel");
                }
            });

            camelContext.start();
            Thread.sleep(10000);
            camelContext.stop();
        } catch (Exception ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
}

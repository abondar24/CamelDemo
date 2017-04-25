package org.abondar.experimental.cameldemo;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

public class FtpToJMS {
    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.17.0.3:61616");

        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));


        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("ftp://172.17.0.4?username=abondar&password=admin123")
                        .process(exchange ->
                                System.out.println("SS-21: "+exchange.getIn().getHeader("CamelFileName")))
                        .to("jms:queueSS1");
            }
        });

        camelContext.start();
        Thread.sleep(10000);
        camelContext.stop();
    }
}

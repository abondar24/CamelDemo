package org.abondar.experimental.cameldemo.concurency;


import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class ConcurrentComponentDemo {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();


        context.addComponent("cc",new ConcurentComponent());
        context.getComponent("cc",ConcurentComponent.class).start();



        context.start();
        System.out.println("10 second wait");
        Thread.sleep(10000);
        context.stop();


    }
}

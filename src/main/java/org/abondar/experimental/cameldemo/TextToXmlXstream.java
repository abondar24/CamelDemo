package org.abondar.experimental.cameldemo;


import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


public class TextToXmlXstream {
    public static void main(String[] args) throws Exception {

        TextBean bean = new TextBean("1123442","555544",
                "12/10/2013","7", new String[]{"s1","t21","s34"});

        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("timer://tt1?fixedRate=true&period=300")
                        .setBody(constant(bean))
                        .marshal()
                        .xstream()
                        .to("file:/home/abondar/Downloads?fileName=out.xml");
            }
        });

        context.start();
        Thread.sleep(1000);
        context.stop();

    }
}

package org.abondar.experimental.cameldemo.concurency;



import org.abondar.experimental.cameldemo.command.Command;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.ThreadPoolBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.Set;
import java.util.concurrent.ExecutorService;

public class FileReadThreadPoolCommand implements Command {

    @Override
    public void execute() {
        try {
            CamelContext context = new DefaultCamelContext();


            ThreadPoolBuilder builder = new ThreadPoolBuilder(context);
            ExecutorService pool = builder.poolSize(5).maxPoolSize(30).maxQueueSize(200).build("pool");
            context.addRoutes(new RouteBuilder() {

                @Override
                public void configure() throws Exception {
                    from("file:/home/abondar/Documents?charset=UTF-8")
                            .threads().executorService(pool)
                            .to("log:start");


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

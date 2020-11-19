package org.abondar.experimental.cameldemo.greeter;


import org.abondar.experimental.cameldemo.command.Command;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GreetCommand implements Command {


    public void execute() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(GreeterConfig.class);
        ctx.refresh();

        GreeterCaller caller  = ctx.getBean(GreeterCaller.class);
        caller.call();

        DanishGreeter danishGreeter = ctx.getBean(DanishGreeter.class);

        caller.setGreeter(danishGreeter);
        caller.call();
    }


}

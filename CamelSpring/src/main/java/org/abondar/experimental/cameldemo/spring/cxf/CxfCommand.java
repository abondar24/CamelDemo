package org.abondar.experimental.cameldemo.spring.cxf;


import org.abondar.experimental.cameldemo.command.Command;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CxfCommand implements Command {
    @Override
    public void execute() {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(CxfConfig.class);
        ctx.refresh();
    }
}

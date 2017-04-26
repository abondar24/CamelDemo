package org.abondar.experimental.cameldemo.spring;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GreetApp {
    private Greeter greeter;

    public void setGreeter(Greeter greeter) {
        this.greeter = greeter;
    }


    public void execute() {
        System.out.println(greeter.hello());
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(CamelSpringConfig.class);
        ctx.refresh();

        GreetApp greetApp = ctx.getBean(GreetApp.class);
        greetApp.execute();
    }
}

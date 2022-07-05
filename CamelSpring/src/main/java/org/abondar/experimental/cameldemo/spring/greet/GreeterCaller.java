package org.abondar.experimental.cameldemo.spring.greet;

public class GreeterCaller {
    private Greeter greeter;

    public void setGreeter(Greeter greeter) {
        this.greeter = greeter;
    }

    public void call(){
        System.out.println(greeter.hello());
    }
}

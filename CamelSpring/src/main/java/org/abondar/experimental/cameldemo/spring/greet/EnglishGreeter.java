package org.abondar.experimental.cameldemo.spring.greet;


public class EnglishGreeter implements Greeter {

    @Override
    public String hello() {
        return "Hello bro!";
    }
}

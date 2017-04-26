package org.abondar.experimental.cameldemo.spring;


public class DanishGreeter implements Greeter{
    @Override
    public String hello() {
        return "Davs bro!";
    }
}

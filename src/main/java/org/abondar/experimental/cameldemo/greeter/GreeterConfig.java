package org.abondar.experimental.cameldemo.greeter;


import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class GreeterConfig extends CamelConfiguration {
    @Bean
    Greeter englishGreeter() {
        return new EnglishGreeter();
    }

    @Bean
    Greeter danishGreeter(){
        return new DanishGreeter();
    }

    @Bean
    GreeterCaller caller() {
        GreeterCaller caller = new GreeterCaller();
        caller.setGreeter(englishGreeter());
        return caller;
    }


}

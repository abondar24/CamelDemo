package org.abondar.experimental.cameldemo.spring.greet;


import org.apache.camel.CamelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class GreeterConfig implements CamelConfiguration {
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

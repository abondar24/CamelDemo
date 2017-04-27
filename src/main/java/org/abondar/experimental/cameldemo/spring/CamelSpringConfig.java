package org.abondar.experimental.cameldemo.spring;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CamelSpringConfig extends CamelConfiguration {

    @Bean
    Greeter englishGreeter(){
        return new EnglishGreeter();
    }

    @Bean
    GreetApp greetApp(){
        GreetApp greetApp = new GreetApp();
        greetApp.setGreeter(englishGreeter());
        return greetApp;
    }

    @Bean
    JmsComponent jmsComponent(){
        JmsComponent jmsComponent = new JmsComponent();

        ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://172.17.0.4:61616");

        jmsComponent.setConnectionFactory(connectionFactory);

        return jmsComponent;
    }

    @Bean
    FtpToJMSRoute ftpToJMSRoute(){
      return new FtpToJMSRoute();
    }

}

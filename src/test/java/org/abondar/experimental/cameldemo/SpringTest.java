package org.abondar.experimental.cameldemo;


import org.apache.camel.Exchange;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.File;

public class SpringTest extends CamelSpringTestSupport {
    @Override
    protected AbstractApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext ctx =  new AnnotationConfigApplicationContext();
        ctx.register(SpringTestConfig.class);
        ctx.refresh();
        return ctx;
    }

    @Test
    public void testMoveFile() throws Exception {
        template.sendBodyAndHeader("file://target/inbox","Hello",
                Exchange.FILE_NAME,"hello.txt");

        Thread.sleep(1000);

        File target = new File("target/outbox/hello.txt");

        String content = context.getTypeConverter().convertTo(String.class,target);
        assertEquals("Hello", content);
    }
}

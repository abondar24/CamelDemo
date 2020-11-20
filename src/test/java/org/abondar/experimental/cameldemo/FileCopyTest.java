package org.abondar.experimental.cameldemo;


import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.io.File;

public class FileCopyTest extends CamelTestSupport{

    public void setUp() throws Exception{
        deleteDirectory("inbox");
        deleteDirectory("outbox");
        super.setUp();
    }


    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file://target/inbox")
                        .to("file://target/outbox");
            }
        };
    }



    @Test
    public void testFileCopy() throws Exception {
        MockEndpoint resultEndpoint = resolveMandatoryEndpoint("mock:file://target/inbox", MockEndpoint.class);
        template.sendBodyAndHeader("file://target/inbox","Hi",
                Exchange.FILE_NAME,"hello.txt");

        resultEndpoint.assertIsSatisfied();

    }
}

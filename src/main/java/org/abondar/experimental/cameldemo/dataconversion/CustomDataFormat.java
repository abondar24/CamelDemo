package org.abondar.experimental.cameldemo.dataconversion;


import org.apache.activemq.util.ServiceStopper;
import org.apache.activemq.util.ServiceSupport;
import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CustomDataFormat extends ServiceSupport implements DataFormat {


    @Override
    public void marshal(Exchange exchange, Object graph, OutputStream stream) throws Exception {
        byte[] bytes = exchange.getContext().getTypeConverter().mandatoryConvertTo(byte[].class, graph);
        String body = reverseBytes(bytes);
        stream.write(body.getBytes());
    }


    @Override
    public Object unmarshal(Exchange exchange, InputStream stream) throws Exception {
        byte[] bytes = exchange.getContext().getTypeConverter().mandatoryConvertTo(byte[].class, stream);
        String body = reverseBytes(bytes);
        return body;
    }


    @Override
    protected void doStop(ServiceStopper serviceStopper) throws Exception {

    }

    @Override
    protected void doStart() throws Exception {

    }

    @Override
    public void start(){

    }

    @Override
    public void stop(){

    }

    private static String reverseBytes(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length);
        for  (int i = data.length - 1; i>=0; i--){
            char ch = (char)data[i];
            sb.append(ch);
        }
        return sb.toString();
    }

    @Override
    public void build() {
        DataFormat.super.build();
    }

    @Override
    public void init() {
        DataFormat.super.init();
    }

    @Override
    public void close() throws IOException {
        DataFormat.super.close();
    }
}

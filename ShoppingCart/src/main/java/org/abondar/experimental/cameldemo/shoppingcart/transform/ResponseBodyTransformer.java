package org.abondar.experimental.cameldemo.shoppingcart.transform;

import org.springframework.stereotype.Component;

@Component
public class ResponseBodyTransformer {


    public String transFormBody(byte[] body){
        byte[] resp = (byte[]) body;
        return new String(resp);
    }

}

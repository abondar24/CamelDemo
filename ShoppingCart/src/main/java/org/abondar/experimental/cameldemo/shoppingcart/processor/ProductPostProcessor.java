package org.abondar.experimental.cameldemo.shoppingcart.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abondar.experimental.cameldemo.shoppingcart.model.CartProductRequest;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductPostProcessor implements Processor {

    private ObjectMapper mapper;

    @Autowired
    public ProductPostProcessor(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        var body =   exchange.getIn().getBody();
        var productRequest = (CartProductRequest) body;

        productRequest.products().forEach(cartProduct -> {
            String id = UUID.randomUUID().toString();
            cartProduct.setId(id);
        });

        var productBody= mapper.writeValueAsString(productRequest.products());


        Message msg = new DefaultMessage(exchange);
        msg.setBody(productBody);
        exchange.setMessage(msg);
    }
}

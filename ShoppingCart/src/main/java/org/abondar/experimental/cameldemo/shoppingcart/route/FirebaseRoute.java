package org.abondar.experimental.cameldemo.shoppingcart.route;

import org.abondar.experimental.cameldemo.shoppingcart.model.CartProduct;
import org.abondar.experimental.cameldemo.shoppingcart.model.CartProductRequest;
import org.abondar.experimental.cameldemo.shoppingcart.processor.ProductPostProcessor;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

@Component
public class FirebaseRoute extends RouteBuilder {

  @Value("${firebase.url}")
  private String firebaseUrl;

  @Value("${firebase.products}")
  private String prodcutsJson;

  private ProductPostProcessor productPostProcessor;

  @Autowired
    public FirebaseRoute(ProductPostProcessor productPostProcessor) {
        this.productPostProcessor = productPostProcessor;
    }

    @Override
  public void configure() throws Exception {

    from("direct:postToFirebase")
        .process(productPostProcessor)
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .to(firebaseUrl + prodcutsJson)
        .transform()
        .body(
            bdy -> {
              byte[] resp = (byte[]) bdy;
                return new String(resp);
            });
  }
}

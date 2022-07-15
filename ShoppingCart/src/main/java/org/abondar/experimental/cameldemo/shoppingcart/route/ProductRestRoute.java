package org.abondar.experimental.cameldemo.shoppingcart.route;

import org.abondar.experimental.cameldemo.shoppingcart.model.CartProduct;
import org.abondar.experimental.cameldemo.shoppingcart.model.CartProductPostResponse;
import org.abondar.experimental.cameldemo.shoppingcart.model.CartProductRequest;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProductRestRoute extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    rest()
        .path("/product/api")
            .consumes("application/json")
            .produces("application/json")

        .post()
        .apiDocs(true)
            .type(CartProductRequest.class)
            .outType(CartProductPostResponse.class)
        .to("log:org.abondar.experimental.cameldemo.shoppingcart.route?level=INFO")
            .to("direct:postToFirebase")

        .get("/{id}")
            .outType(CartProduct.class)
            .to("seda:out");
  }
}

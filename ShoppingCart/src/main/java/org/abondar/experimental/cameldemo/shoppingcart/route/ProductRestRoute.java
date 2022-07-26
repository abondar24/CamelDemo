package org.abondar.experimental.cameldemo.shoppingcart.route;

import org.abondar.experimental.cameldemo.shoppingcart.model.CartProduct;
import org.abondar.experimental.cameldemo.shoppingcart.model.CartProductPostResponse;
import org.abondar.experimental.cameldemo.shoppingcart.model.CartProductRequest;
import org.abondar.experimental.cameldemo.shoppingcart.util.RouteUtil;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

@Component
public class ProductRestRoute extends RouteBuilder {
  @Override
  public void configure(){
    rest()
        .path("/product")
            .consumes("application/json")
            .produces("application/json")

        .post()
        .apiDocs(true)
            .type(CartProductRequest.class)
            .outType(CartProductPostResponse.class)
        .to(RouteUtil.INFO_LOG)
            .to(RouteUtil.POST_ENDPOINT)

        .get("/{id}")
            .apiDocs(true)
            .outType(CartProduct.class)
            .to(RouteUtil.INFO_LOG)
            .to(RouteUtil.GET_ID_ENDPOINT)

        .get()
            .apiDocs(true)
            .param()
            .name("limit")
            .type(RestParamType.query)
            .endParam()
            .outType(CartProduct.class)
            .to(RouteUtil.INFO_LOG)
            .to(RouteUtil.GET_LIMIT_ENDPOINT);

  }
}

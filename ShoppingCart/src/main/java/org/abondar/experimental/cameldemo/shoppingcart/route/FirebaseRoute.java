package org.abondar.experimental.cameldemo.shoppingcart.route;

import org.abondar.experimental.cameldemo.shoppingcart.processor.ProductPostProcessor;
import org.abondar.experimental.cameldemo.shoppingcart.transform.ResponseBodyTransformer;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FirebaseRoute extends RouteBuilder {

  @Value("${firebase.url}")
  private String firebaseUrl;

  @Value("${firebase.products}")
  private String prodcutsJson;

  private final ProductPostProcessor productPostProcessor;

  private final ResponseBodyTransformer bodyTransformer;

  @Autowired
  public FirebaseRoute(
      ProductPostProcessor productPostProcessor, ResponseBodyTransformer bodyTransformer) {
    this.productPostProcessor = productPostProcessor;
    this.bodyTransformer = bodyTransformer;
  }

  @Override
  public void configure() throws Exception {

    from("direct:post")
        .process(productPostProcessor)
        .setHeader(Exchange.HTTP_METHOD, constant("PATCH"))
        .to(firebaseUrl + prodcutsJson)
        .transform()
        .body(bdy -> bodyTransformer.transFormBody((byte[]) bdy));

    from("direct:getById")
        .removeHeader(Exchange.HTTP_URI)
        .setHeader(Exchange.HTTP_METHOD, constant("GET"))
        .toD(firebaseUrl + prodcutsJson + "?orderBy=\"$key\"&equalTo=\"${header.id}\"")
        .transform()
        .body(bdy -> bodyTransformer.transFormBody((byte[]) bdy));
  }
}

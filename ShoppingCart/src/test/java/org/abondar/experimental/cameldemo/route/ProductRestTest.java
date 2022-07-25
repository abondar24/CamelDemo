package org.abondar.experimental.cameldemo.route;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import org.abondar.experimental.cameldemo.shoppingcart.model.CartProduct;
import org.abondar.experimental.cameldemo.shoppingcart.model.CartProductPostResponse;
import org.abondar.experimental.cameldemo.shoppingcart.processor.ProductProcessor;
import org.abondar.experimental.cameldemo.shoppingcart.route.FirebaseRoute;
import org.abondar.experimental.cameldemo.shoppingcart.route.ProductRestRoute;
import org.abondar.experimental.cameldemo.shoppingcart.transform.ResponseBodyTransformer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EnableAutoConfiguration
@SpringBootTest(
    properties = {"firebase.products=testProducts.json"},
    classes = {
      ProductRestRoute.class,
      FirebaseRoute.class,
      ProductProcessor.class,
      ResponseBodyTransformer.class
    })
@ActiveProfiles("integration")
public class ProductRestTest {

  private static WebClient client;
  private String testId;

  @BeforeAll
  public static void initClient() {
    Vertx vertx = Vertx.vertx();
    client = WebClient.create(vertx);
  }

  @Test
  @Order(1)
  public void postProductTest() {
    var req = client.post("localhost:8080/cart/product");
    var testProduct = new CartProduct("test", "test", BigDecimal.valueOf(0));

    req.sendJson(testProduct)
        .onSuccess(
            response -> {
              var body = response.bodyAsJson(CartProductPostResponse.class);

              testId = body.name();
              assertEquals(200, response.statusCode());
              assertEquals("test", body.toString());
            })
        .onFailure(System.out::println);
  }

  @Test
  @Order(2)
  public void getByIdProductTest() {
    var req = client.get("localhost:8080/cart/product" + testId);

    req.send()
        .onSuccess(
            response -> {
              var body = response.bodyAsJson(CartProduct.class);

              assertEquals(200, response.statusCode());
              assertEquals("test", body.getName());
              assertEquals("test", body.getImgUrl());
              assertEquals(BigDecimal.valueOf(0), body.getPrice());
            })
        .onFailure(System.out::println);
  }

  @Test
  @Order(2)
  public void getByLimitProductTest() {
    var req = client.get("localhost:8080/cart/product");
    req.setQueryParam("limit", "1");

    req.send()
        .onSuccess(
            response -> {
              var body = response.bodyAsJson(CartProduct.class);
              assertEquals(200, response.statusCode());
              assertEquals("test", body.getName());
              assertEquals("test", body.getImgUrl());
              assertEquals(BigDecimal.valueOf(0), body.getPrice());
            })
        .onFailure(System.out::println);
  }
}

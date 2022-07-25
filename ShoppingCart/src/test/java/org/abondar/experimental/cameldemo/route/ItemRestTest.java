package org.abondar.experimental.cameldemo.route;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import org.abondar.experimental.cameldemo.shoppingcart.model.CartItems;
import org.abondar.experimental.cameldemo.shoppingcart.processor.ProductProcessor;
import org.abondar.experimental.cameldemo.shoppingcart.route.FirebaseRoute;
import org.abondar.experimental.cameldemo.shoppingcart.route.ItemRestRoute;
import org.abondar.experimental.cameldemo.shoppingcart.transform.ResponseBodyTransformer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@EnableAutoConfiguration
@SpringBootTest(
    properties = {
      "firebase.cartItems=testItems.json"
    },
    classes = {
      ItemRestRoute.class,
      FirebaseRoute.class,
      ProductProcessor.class,
      ResponseBodyTransformer.class
    })
@ActiveProfiles("integration")
public class ItemRestTest {

  @Test
  public void getItemTest() {
    var vertx = Vertx.vertx();
    var client = WebClient.create(vertx);
    var req = client.get("localhost:8080/cart/item");

    req.send()
        .onSuccess(
            response -> {
              var body = response.bodyAsJson(CartItems.class);

              assertEquals(200, response.statusCode());
              assertTrue(body.changed());
              assertTrue(body.showCart());
              assertEquals(0, body.itemsTotal());
              assertTrue(body.items().isEmpty());
            })
        .onFailure(System.out::println);
  }
}

package org.abondar.experimental.cameldemo.route;

import org.abondar.experimental.cameldemo.shoppingcart.model.CartItems;
import org.abondar.experimental.cameldemo.shoppingcart.model.CartProduct;
import org.abondar.experimental.cameldemo.shoppingcart.model.CartProductRequest;
import org.abondar.experimental.cameldemo.shoppingcart.processor.ProductProcessor;
import org.abondar.experimental.cameldemo.shoppingcart.route.FirebaseRoute;
import org.abondar.experimental.cameldemo.shoppingcart.transform.ResponseBodyTransformer;
import org.abondar.experimental.cameldemo.shoppingcart.util.RouteUtil;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ToDynamicDefinition;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest(
    classes = {FirebaseRoute.class, ProductProcessor.class, ResponseBodyTransformer.class})
public class FirebaseRouteTest {

  private static final String MOCK_URI =
      "https://shoppingcart-a62bb-default-rtdb.europe-west1.firebasedatabase.app/products.json";

  private static final String MOCK_URI_GET_ID =
      MOCK_URI + RouteUtil.GET_ID_QUERY;

  private static final String MOCK_URI_GET_LIMIT =
      MOCK_URI + RouteUtil.GET_LIMIT_QUERY;

  private static final String MOCK_URI_ITEM =
      "https://shoppingcart-a62bb-default-rtdb.europe-west1.firebasedatabase.app/cartItems.json";

  @Autowired private ProducerTemplate producerTemplate;

  @EndpointInject("mock:" + MOCK_URI)
  MockEndpoint postEndpoint;

  @EndpointInject("mock:" + MOCK_URI_GET_ID)
  MockEndpoint getIdEndpoint;

  @EndpointInject("mock:" + MOCK_URI_GET_LIMIT)
  MockEndpoint getLimitEndpoint;

  @EndpointInject("mock:" + MOCK_URI_ITEM)
  MockEndpoint getItemEndpoint;

  @Test
  public void postTest() throws Exception {
    var body = new CartProductRequest(List.of(generateTestData()));

    AdviceWith.adviceWith(
        producerTemplate.getCamelContext(),
        RouteUtil.POST_ROUTE,
        a ->
            a.interceptSendToEndpoint(MOCK_URI)
                .skipSendToOriginalEndpoint()
                .setBody(bdy -> new byte[45])
                .to(postEndpoint.getEndpointUri()));

    producerTemplate.sendBody(RouteUtil.POST_ENDPOINT, body);

    postEndpoint.expectedBodiesReceived(body);
    postEndpoint.expectedMessageCount(1);
    postEndpoint.assertIsSatisfied();
  }

  @Test
  public void getByIdTest() throws Exception {
    var product = generateTestData();

    AdviceWith.adviceWith(
        producerTemplate.getCamelContext(),
        RouteUtil.GET_ID_ROUTE,
        a ->
            a.weaveByType(ToDynamicDefinition.class)
                .replace()
                .setBody(bdy -> product.toString().getBytes())
                .to(getIdEndpoint.getEndpointUri()));

    producerTemplate.sendBodyAndHeader(RouteUtil.GET_ID_ENDPOINT, null, "id", 1);

    getIdEndpoint.expectedHeaderReceived("id", 1);
    getIdEndpoint.expectedMessageCount(1);
    getIdEndpoint.assertIsSatisfied();
  }

  @Test
  public void getByLimitTest() throws Exception {
    var product = generateTestData();

    AdviceWith.adviceWith(
        producerTemplate.getCamelContext(),
        RouteUtil.GET_LIMIT_ROUTE,
        a ->
            a.weaveByType(ToDynamicDefinition.class)
                .replace()
                .setBody(bdy -> product.toString().getBytes())
                .to(getLimitEndpoint.getEndpointUri()));

    producerTemplate.sendBodyAndHeader(RouteUtil.GET_LIMIT_ENDPOINT, null, "limit", 1);

    getLimitEndpoint.expectedHeaderReceived("limit", 1);
    getLimitEndpoint.expectedMessageCount(1);
    getLimitEndpoint.assertIsSatisfied();
  }

  @Test
  public void getItemTest() throws Exception {

    var items = new CartItems(false, 0, false,List.of());
    AdviceWith.adviceWith(
        producerTemplate.getCamelContext(),
            RouteUtil.GET_ITEM_ROUTE,
        a ->
            a.interceptSendToEndpoint(MOCK_URI_ITEM)
                .skipSendToOriginalEndpoint()
                .setBody(bdy -> items.toString().getBytes())
                .to(getItemEndpoint.getEndpointUri()));

    producerTemplate.sendBody(RouteUtil.GET_ITEMS_ENDPOINT, null);

    getItemEndpoint.expectedMessageCount(1);
    getItemEndpoint.assertIsSatisfied();
  }

  private CartProduct generateTestData() {
    return new CartProduct("test", "test", BigDecimal.valueOf(25));
  }
}

package org.abondar.experimental.cameldemo.shoppingcart.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CartProduct{


   private String id;
   private String name;
   private String imgUrl;
   private BigDecimal price;

}



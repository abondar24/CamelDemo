package org.abondar.experimental.cameldemo.shoppingcart.model;

import java.math.BigDecimal;

public record CartProduct(
        long id,
        String name,
        String imgUrl,
        BigDecimal price
) {
}

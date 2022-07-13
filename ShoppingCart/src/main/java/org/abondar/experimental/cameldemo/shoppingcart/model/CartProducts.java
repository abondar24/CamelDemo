package org.abondar.experimental.cameldemo.shoppingcart.model;

import java.util.List;

public record CartProducts(
        List<CartProduct> products
) {
}

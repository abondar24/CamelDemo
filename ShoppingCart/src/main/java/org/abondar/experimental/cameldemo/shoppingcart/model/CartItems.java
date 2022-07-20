package org.abondar.experimental.cameldemo.shoppingcart.model;

public record CartItems(
        boolean changed,
        long itemsTotal,
        boolean showCart
) {
}

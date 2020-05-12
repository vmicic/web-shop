package com.ftn.webshop.domain;

import javax.persistence.Entity;
import java.util.HashMap;

@Entity
public class Cart extends BaseEntity {

    private HashMap<String, Integer> cart;

    public Cart() {
    }

    public HashMap<String, Integer> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, Integer> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cart=" + cart +
                '}';
    }
}

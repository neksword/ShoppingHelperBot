package com.neksword.shoppinghelperbot;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Good {

    private final UUID id = UUID.randomUUID();
    private String name;
    private Integer quantity;
    private Float price;
    private Float total;

    public Good() {
        this.name = "Name";
        this.quantity = 1;
        this.price = (float) 0;
        this.total = (float) 0;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public void decrementQuantity() {
        this.quantity--;
    }

    public Float total() {
        return this.quantity * this.price;
    }
}

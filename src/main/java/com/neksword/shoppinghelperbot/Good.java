package com.neksword.shoppinghelperbot;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Good {

    private String name;
    private Integer quantity;
    private Float price;
    private Float total;
    private final UUID id = UUID.randomUUID();

    public Good() {
        this.name = "Name";
        this.quantity = 0;
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

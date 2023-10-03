package com.neksword.shoppinghelperbot;

import lombok.Getter;

@Getter
public enum Operations {
    INCREMENT("increment"),
    DECREMENT("decrement"),
    DELETE("delete");

    private final String operation;

    Operations(String operation) {
        this.operation = operation;
    }
}

package com.neksword.shoppinghelperbot;

import lombok.Getter;

@Getter
public enum Command {
    START("start"),
    INCREMENT("increment"),
    DECREMENT("decrement"),
    DELETE("delete"),
    EXPORT("export"),
    ADD("add");

    private final String operation;

    Command(String operation) {
        this.operation = operation;
    }
}

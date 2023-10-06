package com.neksword.shoppinghelperbot;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.UUID;

@Getter
@Setter
public class ShoppingList {
    private final java.util.List<Good> goodsList;
    private final java.util.UUID id = UUID.randomUUID();
    private ShoppingList() {
        this.goodsList = new LinkedList<>();
    }



    public void addGood(Good good) {
        this.goodsList.add(good);
    }

    public static ShoppingList newShoppingList() {
        final var good = new Good();
        final var shoppingList = new ShoppingList();
        shoppingList.addGood(good);
        return shoppingList;
    }

    public void removeGoodByName(String goodName) {
        final var goodByName = this.goodsList.stream().filter(good -> good.getName().equals(goodName)).findFirst().get();
        this.goodsList.remove(goodByName);
    }
}

package com.neksword.shoppinghelperbot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ManagementKeyboard {

    public static InlineKeyboardMarkup inlineKeyboard(ShoppingList shoppingList) {
        final var keyboard = shoppingList.getGoodsList().stream().map(good -> keyboardRow(shoppingList, good)).collect(Collectors.toCollection(LinkedList::new));
        return InlineKeyboardMarkup.builder().keyboard(keyboard).build();
    }

    private static List<InlineKeyboardButton> keyboardRow(ShoppingList shoppingList, Good good) {
        final var shoppingListIdAsString = shoppingList.getId().toString();
        final var goodName = good.getName();
        final var nameButton = InlineKeyboardButton.builder().text(goodName).callbackData(shoppingListIdAsString).build();
        final var incrementButton = InlineKeyboardButton.builder().text("+").callbackData(String.join("_", shoppingListIdAsString, "increment", goodName)).build();
        final var quantityButton = InlineKeyboardButton.builder().text(good.getQuantity().toString()).callbackData(String.join("_", shoppingListIdAsString, "quantity", goodName)).build();
        final var decrementButton = InlineKeyboardButton.builder().text("-").callbackData(String.join("_", shoppingListIdAsString, "decrement", goodName)).build();
        final var deleteButton = InlineKeyboardButton.builder().text("\uD83D\uDDD1").callbackData(String.join("_", shoppingListIdAsString, "delete", goodName)).build();
        final var inlineKeyboardRow = new LinkedList<InlineKeyboardButton>();
        inlineKeyboardRow.add(nameButton);
        inlineKeyboardRow.add(incrementButton);
        inlineKeyboardRow.add(quantityButton);
        inlineKeyboardRow.add(decrementButton);
        inlineKeyboardRow.add(deleteButton);
        return inlineKeyboardRow;
    }
}

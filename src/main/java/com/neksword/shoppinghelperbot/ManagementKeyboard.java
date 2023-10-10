package com.neksword.shoppinghelperbot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.join;

public class ManagementKeyboard {

    public static InlineKeyboardMarkup inlineKeyboard(ShoppingList shoppingList) {
        final var keyboard = shoppingList.getGoodsList()
                                         .stream()
                                         .map(good -> goodKeyboardRow(shoppingList, good))
                                         .collect(Collectors.toCollection(LinkedList::new));
        final var exportButtonRow = new LinkedList<InlineKeyboardButton>();
        final var shoppingListIdAsString = shoppingList.getId().toString();
        final var exportButton = InlineKeyboardButton.builder()
                                                     .text("Export to PDF")
                                                     .callbackData(join("_", shoppingListIdAsString, Command.EXPORT.getOperation(), "export"))
                                                     .build();
        exportButtonRow.add(exportButton);
        final var addGoodButtonRow = new LinkedList<InlineKeyboardButton>();
        final var addGoodButton = InlineKeyboardButton.builder()
                                                      .text("Add a good")
                                                      .callbackData(join("_", shoppingListIdAsString, Command.ADD.getOperation(), "new"))
                                                      .build();
        addGoodButtonRow.add(addGoodButton);
        keyboard.add(exportButtonRow);
        keyboard.add(addGoodButtonRow);
        return InlineKeyboardMarkup.builder().keyboard(keyboard).build();
    }

    private static List<InlineKeyboardButton> goodKeyboardRow(ShoppingList shoppingList, Good good) {
        final var shoppingListIdAsString = shoppingList.getId().toString();
        final var goodName = good.getName();
        final var nameButton = InlineKeyboardButton.builder()
                                                   .text(goodName)
                                                   .callbackData(shoppingListIdAsString)
                                                   .build();
        final var incrementButton = InlineKeyboardButton.builder()
                                                        .text("+")
                                                        .callbackData(join("_", shoppingListIdAsString, Command.INCREMENT.getOperation(), goodName))
                                                        .build();
        final var quantityButton = InlineKeyboardButton.builder()
                                                       .text(good.getQuantity().toString())
                                                       .callbackData(join("_", shoppingListIdAsString, "empty"))
                                                       .build();
        final var decrementButton = InlineKeyboardButton.builder()
                                                        .text("-")
                                                        .callbackData(join("_", shoppingListIdAsString, Command.DECREMENT.getOperation(), goodName))
                                                        .build();
        final var deleteButton = InlineKeyboardButton.builder()
                                                     .text("\uD83D\uDDD1")
                                                     .callbackData(join("_", shoppingListIdAsString, Command.DELETE.getOperation(), goodName))
                                                     .build();
        final var firstButtonRow = new LinkedList<InlineKeyboardButton>();
        firstButtonRow.add(nameButton);
        firstButtonRow.add(incrementButton);
        firstButtonRow.add(quantityButton);
        firstButtonRow.add(decrementButton);
        firstButtonRow.add(deleteButton);
        return firstButtonRow;
    }
}

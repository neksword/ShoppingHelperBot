package com.neksword.shoppinghelperbot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

@Component
public class ShoppingHelperBot extends TelegramLongPollingBot {

    public ShoppingHelperBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    private final Map<UUID, ShoppingList> shoppingListMap = new HashMap<>();
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.toString());
        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            final var newShoppingList = ShoppingList.newShoppingList();
            shoppingListMap.put(newShoppingList.getId(), newShoppingList);
            final var sendMessage = SendMessage.builder().chatId(update.getMessage().getChatId()).text("Here is your shopping list:").replyMarkup(ManagementKeyboard.inlineKeyboard(newShoppingList)).build();
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if (update.hasCallbackQuery()) {
            final var callbackQuery = update.getCallbackQuery();
            final var callbackQueryId = callbackQuery.getId();
            final var messageId = callbackQuery.getMessage().getMessageId();
            final var data = callbackQuery.getData().split("_");
            final var chatId = callbackQuery.getMessage().getChatId();
            final var answerQuery = AnswerCallbackQuery.builder().callbackQueryId(callbackQueryId).build();
            final var shoppingList = shoppingListMap.get(UUID.fromString(data[0]));
            if (data[1].equals(Operations.INCREMENT.getOperation())) {
                shoppingList.getGoodsList().stream().filter(good -> good.getName().equals(data[2])).findFirst().get().incrementQuantity();
            }
            final var editMessage = EditMessageText.builder().messageId(messageId).replyMarkup(ManagementKeyboard.inlineKeyboard(shoppingList)).chatId(chatId).text("Your list is edited").build();
            try {
                execute(answerQuery);
                execute(editMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public String getBotUsername() {
        return "nekspetbot";
    }
}

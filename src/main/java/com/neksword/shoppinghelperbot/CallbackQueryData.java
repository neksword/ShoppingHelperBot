package com.neksword.shoppinghelperbot;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Getter
public class CallbackQueryData {

    private final String shoppingListIdAsString;
    private final String commandAsString;
    private final String goodNameAsString;
    private final String callbackQueryId;
    private final Integer messageId;
    private final Long chatId;


    public CallbackQueryData(CallbackQuery callbackQuery) {
        final var data = callbackQuery.getData().split("_");
        this.shoppingListIdAsString = data[0];
        this.commandAsString = data[1];
        this.goodNameAsString = data[2];
        this.callbackQueryId = callbackQuery.getId();
        this.messageId = callbackQuery.getMessage().getMessageId();
        this.chatId = callbackQuery.getMessage().getChatId();
    }
}

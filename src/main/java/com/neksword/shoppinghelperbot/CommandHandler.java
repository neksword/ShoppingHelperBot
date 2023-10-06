package com.neksword.shoppinghelperbot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public interface CommandHandler {

    void handle(Update update, ShoppingHelperBot bot) throws TelegramApiException;
}

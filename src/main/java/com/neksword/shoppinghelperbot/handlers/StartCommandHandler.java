package com.neksword.shoppinghelperbot.handlers;

import com.neksword.shoppinghelperbot.ShoppingHelperBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class StartCommandHandler implements CommandHandler {
    @Override
    public void handle(Update update, ShoppingHelperBot bot) throws TelegramApiException {

    }
}

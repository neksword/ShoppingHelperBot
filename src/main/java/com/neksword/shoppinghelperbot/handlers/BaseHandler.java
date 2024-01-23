package com.neksword.shoppinghelperbot.handlers;

import com.neksword.shoppinghelperbot.ShoppingHelperBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BaseHandler implements CommandHandler {

    protected final CommandHandler nextHandler;

    @Autowired
    public BaseHandler(@Qualifier("incrementHandler") CommandHandler handler) {
        this.nextHandler = handler;
    }

    public BaseHandler() {
        this.nextHandler = null;
    }

    @Override
    public void handle(Update update, ShoppingHelperBot bot) throws TelegramApiException {
        if (nextHandler != null) {
            nextHandler.handle(update, bot);
        }
    }
}

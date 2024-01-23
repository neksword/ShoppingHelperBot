package com.neksword.shoppinghelperbot.handlers;

import com.neksword.shoppinghelperbot.ShoppingHelperBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class CommandHandlerChain implements CommandHandler {

    @Autowired
    List<CommandHandler> handlers;

    @Override
    public void handle(Update update, ShoppingHelperBot bot) throws TelegramApiException {
        for (CommandHandler handler : handlers) {
            handler.handle(update, bot);
        }
    }
}

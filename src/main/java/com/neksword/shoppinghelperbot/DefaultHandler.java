package com.neksword.shoppinghelperbot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component("defaultHandler")
public class DefaultHandler extends BaseHandler {

    public DefaultHandler() {
        super();
    }

    @Override
    public void handle(Update update, ShoppingHelperBot bot) throws TelegramApiException {
        final var errorMessage = SendMessage.builder()
                                            .text("Incorrect request. Please use buttons assigned to your shopping list to edit/remove " +
                                                    "goods, Export your shopping list with 'Export' button or start over with '/start' command.")
                                            .build();
        bot.execute(errorMessage);
    }
}

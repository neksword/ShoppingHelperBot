package com.neksword.shoppinghelperbot;

import com.neksword.shoppinghelperbot.handlers.CommandHandlerChain;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Getter
public class ShoppingHelperBot extends TelegramLongPollingBot {

    private final CommandHandlerChain handlerChain;
    private final Map<UUID, ShoppingList> shoppingListMap = new HashMap<>();

    public ShoppingHelperBot(@Value("${bot.token}") String botToken, CommandHandlerChain handlerChain) {
        super(botToken);
        this.handlerChain = handlerChain;
    }

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
        } else {
            try {
                handlerChain.handle(update, this);
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

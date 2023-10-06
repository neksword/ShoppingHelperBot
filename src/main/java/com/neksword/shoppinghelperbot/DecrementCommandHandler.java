package com.neksword.shoppinghelperbot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.UUID;

@Component("decrementHandler")
public class DecrementCommandHandler extends BaseHandler {
    public DecrementCommandHandler(@Qualifier("deleteHandler") CommandHandler handler) {
        super(handler);
    }

    @Override
    public void handle(Update update, ShoppingHelperBot bot) throws TelegramApiException {
        if (update.hasCallbackQuery()) {
            final var callbackQuery = update.getCallbackQuery();
            final var callbackQueryData = new CallbackQueryData(callbackQuery);
            if (callbackQueryData.getCommandAsString().equals(Command.DECREMENT.getOperation())) {
                final var answerQuery = AnswerCallbackQuery.builder().callbackQueryId(callbackQueryData.getCallbackQueryId()).build();
                final var shoppingList = bot.getShoppingListMap().get(UUID.fromString(callbackQueryData.getShoppingListIdAsString()));
                shoppingList.getGoodsList()
                            .stream()
                            .filter(good -> good.getName().equals(callbackQueryData.getGoodNameAsString()))
                            .findFirst()
                            .get()
                            .decrementQuantity();
                final var editMessage = EditMessageText.builder()
                                                       .messageId(callbackQueryData.getMessageId())
                                                       .replyMarkup(ManagementKeyboard.inlineKeyboard(shoppingList))
                                                       .chatId(callbackQueryData.getChatId())
                                                       .text("Your list is edited")
                                                       .build();
                bot.execute(answerQuery);
                bot.execute(editMessage);
            } else nextHandler.handle(update, bot);
        }


    }
}

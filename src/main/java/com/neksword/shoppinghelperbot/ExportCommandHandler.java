package com.neksword.shoppinghelperbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.UUID;

@Component("exportHandler")
public class ExportCommandHandler extends BaseHandler {

    private final Exporter exporter;

    @Autowired(required = false)
    public ExportCommandHandler(@Qualifier("addGoodHandler") CommandHandler handler, Exporter exporter) {
        super(handler);
        this.exporter = exporter;
    }

    @Override
    public void handle(Update update, ShoppingHelperBot bot) throws TelegramApiException {
        if (update.hasCallbackQuery()) {
            final var callbackQueryData = new CallbackQueryData(update.getCallbackQuery());
            if (callbackQueryData.getCommandAsString().equals(Command.EXPORT.getOperation())) {
                final var fileToExport = exporter.export(bot.getShoppingListMap()
                                                      .get(UUID.fromString(callbackQueryData.getShoppingListIdAsString())), callbackQueryData);
                final var answerCallbackQuery = AnswerCallbackQuery.builder()
                                                                   .callbackQueryId(callbackQueryData.getCallbackQueryId())
                                                                   .build();
                final SendDocument sendDocument;
                sendDocument = SendDocument.builder()
                                           .chatId(callbackQueryData.getChatId())
                                           .document(new InputFile(fileToExport, fileToExport.getName()))
                                           .build();
                bot.execute(answerCallbackQuery);
                bot.execute(sendDocument);
            } else nextHandler.handle(update, bot);
        }
    }
}

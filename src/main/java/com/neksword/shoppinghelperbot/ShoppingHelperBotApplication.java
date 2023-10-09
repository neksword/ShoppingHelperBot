package com.neksword.shoppinghelperbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class ShoppingHelperBotApplication {

    public static void main(String[] args) {
        final var ctx = SpringApplication.run(ShoppingHelperBotApplication.class, args);
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(ctx.getBean("shoppingHelperBot", TelegramLongPollingBot.class));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}

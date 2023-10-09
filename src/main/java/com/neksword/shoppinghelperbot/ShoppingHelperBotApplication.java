package com.neksword.shoppinghelperbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@PropertySource("classpath:.env")
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

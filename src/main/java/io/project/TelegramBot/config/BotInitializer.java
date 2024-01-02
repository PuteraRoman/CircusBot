package io.project.TelegramBot.config;

import io.project.TelegramBot.service.TelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@Slf4j
public class BotInitializer {
    @Autowired
    TelegramBot telegramBot; // Autowired instance of TelegramBot

    @EventListener({ContextRefreshedEvent.class}) // EventListener for ContextRefreshedEvent
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class); // Creating instance of TelegramBotsApi
        try {
            telegramBotsApi.registerBot(telegramBot); // Registering TelegramBot using TelegramBotsApi
        } catch (TelegramApiException e) {
            log.error("Error catch: " + e.getMessage()); // Logging error if registration fails
        }
    }
}

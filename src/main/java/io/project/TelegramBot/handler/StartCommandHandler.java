package io.project.TelegramBot.handler;

import io.project.TelegramBot.UserInput;
import io.project.TelegramBot.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static io.project.TelegramBot.constant.AnswerMessages.HELLO_TEXT;

@Component
public class StartCommandHandler implements Handlers {

    public final UserService userService;

    public StartCommandHandler(UserService userService){
        this.userService = userService;
    }

    @Override
    public boolean support(String command) {
        // Checks if the command received is "/start"
        return "/start".equals(command);
    }

    @Override
    public BotApiMethod handle(UserInput userInput) {
        // Retrieves the chat ID from UserInput and sends a welcome message
        long chatId = userInput.getChatId();
        return new SendMessage(String.valueOf(chatId), HELLO_TEXT);
    }
}

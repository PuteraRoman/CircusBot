package io.project.TelegramBot.handler;

import io.project.TelegramBot.UserInput;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static io.project.TelegramBot.constant.AnswerMessages.HELP_TEXT;

@Component
public class HelpCommandHandler implements Handlers {

    @Override
    public boolean support(String command) {
        // Checks if the command received is "/help"
        return "/help".equals(command);
    }

    @Override
    public BotApiMethod handle(UserInput userInput) {
        // Retrieves chat ID from user input
        long chatId = userInput.getChatId();

        // Retrieves the help text from constants
        String answer = HELP_TEXT;

        // Creates and returns a SendMessage instance to send the help text to the user
        return new SendMessage(String.valueOf(chatId), answer);
    }

}

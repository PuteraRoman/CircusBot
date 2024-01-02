package io.project.TelegramBot.handler;

import io.project.TelegramBot.UserInput;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
;

 public interface  Handlers {
     boolean support(String command);

     BotApiMethod handle(UserInput userInput);

 }



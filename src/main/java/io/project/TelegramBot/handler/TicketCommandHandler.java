package io.project.TelegramBot.handler;

import io.project.TelegramBot.UserInput;
import io.project.TelegramBot.service.TicketService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public class TicketCommandHandler implements Handlers {
    private final TicketService ticketService;

    public TicketCommandHandler(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @Override
    public boolean support(String command) {
        // Checks if the command received is "/ticket_order"
        return "/ticket_order".equals(command);
    }

    @Override
    public BotApiMethod handle(UserInput userInput) {
        // Logic to handle the "/ticket_order" command (To be implemented)
        // Currently, it returns null
        return null;
    }
}

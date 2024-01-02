package io.project.TelegramBot.service;

import io.project.TelegramBot.entity.Ticket;
import io.project.TelegramBot.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TicketService {
    private final UserService userService;

    TicketService(UserService userService){
            this.userService = userService;
        }

    @Transactional
    public void addTicket(Ticket ticket){
        Optional<User> user = userService.findById(ticket.getId());
        user.get().setTickets(ticket);
    }
}

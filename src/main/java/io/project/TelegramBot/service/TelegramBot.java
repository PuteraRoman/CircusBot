    package io.project.TelegramBot.service;

    import io.project.TelegramBot.UserInput;
    import io.project.TelegramBot.config.BotConfig;
    import io.project.TelegramBot.entity.User;
    import io.project.TelegramBot.handler.*;
    import io.project.TelegramBot.repository.UserRepository;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Component;
    import org.telegram.telegrambots.bots.TelegramLongPollingBot;
    import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
    import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
    import org.telegram.telegrambots.meta.api.objects.Update;
    import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
    import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
    import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

    import java.sql.Timestamp;
    import java.util.*;


// Import statements for necessary classes and interfaces

    @Component
    @Slf4j
    public class TelegramBot extends TelegramLongPollingBot {

        // Dependency injection of various services and configurations
        private final UserRepository userRepository;
        private final List<Handlers> handlers;
        private final TicketService ticketService;
        private final UserService userService;
        private final BotConfig botConfig;

        // Constructor with dependencies injected
        public TelegramBot(BotConfig botConfig, UserService userService, TicketService ticketService, UserRepository userRepository) {
            this.botConfig = botConfig;
            this.userRepository = userRepository;
            this.userService = userService;
            this.ticketService = ticketService;

            // Initializing handlers for different commands
            this.handlers = List.of(
                    new HelpCommandHandler(),
                    new StartCommandHandler(userService),
                    new RegisterCommandHandler(userService),
                    new TicketCommandHandler(ticketService)
            );

            // Creating a list of bot commands
            List<BotCommand> menu = new ArrayList<>();
            menu.add(new BotCommand("/start", "welcome message"));
            menu.add(new BotCommand("/help", "information about all commands in this bot"));
            menu.add(new BotCommand("/register", "register button"));

            try {
                // Setting bot commands using SetMyCommands API
                execute(new SetMyCommands(menu, new BotCommandScopeDefault(), null));
            } catch (TelegramApiException e) {
                log.error("Setting bot command list: " + e.getMessage());
            }
        }

        // Overriding method to get the bot's username
        @Override
        public String getBotUsername() {
            return botConfig.getBotName();
        }

        // Overriding method to get the bot's token
        @Override
        public String getBotToken() {
            return botConfig.getToken();
        }

        // Method to handle incoming updates
        @Override
        public void onUpdateReceived(Update update) {
            if (update.hasMessage()) {
                if (update.getMessage().hasContact()) {
                    // Handling received contact information
                    // Creating a new User entity and setting contact details
                    User user = new User();
                    long chatId = update.getMessage().getChatId();
                    String firstName = update.getMessage().getContact().getFirstName();
                    String lastName = update.getMessage().getContact().getLastName();
                    String phoneNumber = update.getMessage().getContact().getPhoneNumber();
                    user.setChatId(chatId);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPhoneNumber(phoneNumber);
                }
                if (update.getMessage().hasText()) {
                    // Handling received text messages
                    long chatId = update.getMessage().getChatId();
                    String firstName = update.getMessage().getChat().getFirstName();
                    String lastName = update.getMessage().getChat().getLastName();
                    String text = update.getMessage().getText();

                    // Creating UserInput object with message details
                    UserInput userInput = new UserInput(chatId, firstName, lastName, new Timestamp((long) update.getMessage().getDate() * 1000), text);

                    // Handling the received message using appropriate handler
                    var message = handle(userInput);

                    // Sending the response message
                    sendMessage(message);
                }
            }
        }

        // Method to send a message via the Telegram API
        private void sendMessage(SendMessage message) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                // Handling exception if sending message fails
                log.error("Error sending message: " + e.getMessage());
            }
        }

        // Method to handle user input based on commands and execute appropriate handler
        private SendMessage handle(UserInput userInput) {
            Optional<Handlers> handler = Optional.empty();
            for (Handlers current : handlers) {
                if (current.support(userInput.getCommand())) {
                    handler = Optional.of(current);
                    break;
                }
            }
            return (SendMessage) handler
                    .orElseThrow(() -> new IllegalArgumentException("Sorry, command was not recognized: " + userInput.getCommand()))
                    .handle(userInput);
        }
    }

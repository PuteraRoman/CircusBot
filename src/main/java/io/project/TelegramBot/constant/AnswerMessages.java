package io.project.TelegramBot.constant;

public class AnswerMessages {

    public static final String HELP_TEXT = "Hello! To get started, please follow these steps:\n" +
            "1. Use the command /register to provide your information and select a visit date.\n" +
            "2. After providing your details, a ticket will be generated and sent to you.\n" +
            "3. Our operator will contact you shortly after.\n" +
            "\n" +
            "Thank you!";

    public static final String HELLO_TEXT = "\"Welcome! \uD83C\uDFA9 This is the Circus Ticket Purchase Bot." +
            " \uD83C\uDFAA You're in the right place to reserve tickets for our spectacular circus shows!" +
            " \uD83C\uDF89 Feel free to navigate through the available commands or proceed with '/register' to start booking your tickets. " +
            "Enjoy the show! \uD83C\uDF9F\uFE0F\"";

    public static final String REGISTERED ="Seems like you are registered yet!";

        public static final String NUMBER_NULL = "Provided number cannot be empty.";

        public static final String NUMBER_ERROR ="The number should match the pattern '+380' followed by nine digits (for example, +380123456789).";

}

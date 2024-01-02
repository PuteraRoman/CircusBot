package io.project.TelegramBot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {
    @Value("${bot.name}") // Value injection for bot name from application.properties
    String botName;
    @Value("${bot.token}") // Value injection for bot token from application.properties
    String token;
}

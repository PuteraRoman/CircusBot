package io.project.TelegramBot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneNumber {
    private Long chatId;

    private String phoneNumber;
}

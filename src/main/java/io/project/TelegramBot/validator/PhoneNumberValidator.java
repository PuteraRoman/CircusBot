package io.project.TelegramBot.validator;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class PhoneNumberValidator {
    // Regular expression pattern to validate a phone number with the Ukrainian format
    private static final Pattern PHONE_NUMBER = Pattern.compile("^\\+?3?8?(0\\d{9})$");

    /**
     * Validates the provided phone number.
     *
     * @param phoneNumber The phone number to be validated.
     * @return True if the phone number matches the Ukrainian format, false otherwise.
     */
    public boolean validNumber(String phoneNumber) {
        return validatePhoneNumber(phoneNumber);
    }

    /**
     * Checks if the provided string matches the phone number pattern.
     *
     * @param phoneNumber The phone number string to be checked for the valid format.
     * @return True if the provided string matches the phone number pattern, false otherwise.
     */
    private boolean validatePhoneNumber(String phoneNumber) {
        return PHONE_NUMBER.matcher(phoneNumber).matches();
    }
}

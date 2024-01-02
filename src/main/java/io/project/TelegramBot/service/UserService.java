package io.project.TelegramBot.service;

import io.project.TelegramBot.dto.PhoneNumber;
import io.project.TelegramBot.entity.User;
import io.project.TelegramBot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user by their unique chat ID.
     *
     * @param chatId The unique identifier for the user's chat.
     * @return An Optional containing the User if found, empty otherwise.
     */
    public Optional<User> findById(Long chatId){
        return userRepository.findById(chatId);
    }

    /**
     * Saves a User entity into the UserRepository.
     *
     * @param user The User entity to be saved.
     * @return True if the user is successfully saved, false otherwise.
     */
    public boolean save(User user){
        if (user == null){
            return false;
        } else {
            userRepository.save(user);
            return true;
        }
    }

    /**
     * Updates the phone number of a User identified by their chat ID.
     *
     * @param phoneNumber The PhoneNumber object containing the chat ID and phone number to update.
     */
    public void phoneNumber(PhoneNumber phoneNumber){
        Optional<User> user = findById(phoneNumber.getChatId());
        if(user.isPresent()) {
            user.get().setPhoneNumber(phoneNumber.getPhoneNumber());
        }
    }
}

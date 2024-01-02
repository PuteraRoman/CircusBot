package io.project.TelegramBot.repository;

import io.project.TelegramBot.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}

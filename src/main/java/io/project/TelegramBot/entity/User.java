package io.project.TelegramBot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.glassfish.grizzly.http.util.TimeStamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "userData")
public class User {
    @Id
    private Long chatId;

    private String firstName;

    private String lastName;

    private Timestamp registerAt;

    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets = new ArrayList<>();

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getRegisterAt() {
        return registerAt;
    }

    public void setRegisterAt(Timestamp registerAt) {

        this.registerAt = registerAt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

public void setTickets(Ticket ticket){
    if(ticket != null){
        tickets.add(ticket);
    }
}


    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber != null){
            this.phoneNumber = phoneNumber;
        }
    }
    @Override
    public String toString() {
        return "User{" +
                "chatId=" + chatId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registerAt=" + registerAt +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

}

package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    int id;
    String name;
    String surname;
    String email;
    String password;
    int eventId;

    public User(String name, String surname, String email, int eventId, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.eventId = eventId;
    }
}

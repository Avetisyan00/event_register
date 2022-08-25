package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    int id;
    String name;
    String place;
    boolean isOnline;
    double price;
    EventType eventType;

    public Event(String name, String place, boolean isOnline, double price, EventType eventType) {
        this.name = name;
        this.place = place;
        this.isOnline = isOnline;
        this.price = price;
        this.eventType = eventType;
    }
}

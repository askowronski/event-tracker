package com.eventtracker.backend.entities;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "events")
public class Event {

    @Id
    private String id;
    private String type;
    private Date date;
    private Long duration;

    public Event(String type, Date date, Long duration) {
        this.type = type;
        this.date = date;
        this.duration = duration;
    }
}
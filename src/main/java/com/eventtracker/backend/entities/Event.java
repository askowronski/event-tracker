package com.eventtracker.backend.entities;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "events")
public class Event {

    @Id
    private String id;
    @NotBlank(message = "Type cannot be blank.")
    private String type;
    private Long duration;
    @NotBlank(message = "Name cannot be blank.")
    private String name;
    private String notes;
    @NotNull(message = "Start Time cannot be null")
    private Date startTime;
    private Date endTime;
    private boolean multiPeriod;
    public EventTypeEntity eventType;

    public boolean isOnGoing() {
        return endTime == null && duration == null;
    }
}

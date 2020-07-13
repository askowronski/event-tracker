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
    private Long duration;
    private String name;
    private String notes;
    private Date startTime;
    
	
}

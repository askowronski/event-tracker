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
    
	public Event(String id, String type, Date date, Long duration, String name, String notes, Date startTime) {
		super();
		this.id = id;
		this.type = type;
		this.duration = duration;
		this.name = name;
		this.notes = notes;
		this.startTime = startTime;
	} 
	
}
package com.eventtracker.backend.repository;

import com.eventtracker.backend.entities.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {

}

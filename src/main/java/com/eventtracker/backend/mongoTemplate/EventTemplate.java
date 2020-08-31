package com.eventtracker.backend.mongoTemplate;

import com.eventtracker.backend.entities.Event;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EventTemplate  {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public EventTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<String> findDistinctEventTypes() {
        return mongoTemplate.findDistinct("type", Event.class, String.class);
    }
}
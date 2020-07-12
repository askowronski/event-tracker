package com.eventtracker.backend.api.controller;

import com.eventtracker.backend.entities.Event;
import com.eventtracker.backend.repository.EventRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    public EventRepository eventRepository;

    @GetMapping("/event")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
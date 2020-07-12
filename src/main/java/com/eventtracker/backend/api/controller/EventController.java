package com.eventtracker.backend.api.controller;

import com.eventtracker.backend.entities.Event;
import com.eventtracker.backend.repository.EventRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

	@Autowired
	public EventRepository eventRepository;

	@GetMapping("/events")
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	@PostMapping("/event")
	public Event createEvent(@RequestBody Event event) {
		return eventRepository.insert(event);
	}
}
package com.eventtracker.backend.api.controller;

import com.eventtracker.backend.api.dto.CalendarDateData;
import com.eventtracker.backend.api.dto.CalendarDto;
import com.eventtracker.backend.api.dto.EventType;
import com.eventtracker.backend.entities.Event;
import com.eventtracker.backend.repository.EventRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

	@Autowired
	public EventRepository eventRepository;

	@GetMapping("/events")
	@CrossOrigin()
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

    @GetMapping("/events/calendarData")
    @CrossOrigin()
    public CalendarDto getAllEventsForCalendar() {
        return this.convertEntitiesToDto(eventRepository.findAll());
    }

	@PostMapping("/event")
	@CrossOrigin()
	public Event createEvent(@RequestBody Event event) {
		return eventRepository.insert(event);
	}

	public CalendarDto convertEntitiesToDto(List<Event> events) {
	    Map<String, CalendarDateData> dateDataMap = new HashMap();
	    List<EventType> eventTypeList = this.getEventTypes(events);

	    events.stream().forEach(e -> {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = df.format(e.getStartTime());
            if (dateDataMap.get(dateString) == null) {
                CalendarDateData dateData = new CalendarDateData();
                HashSet<String> dots = new HashSet<>();
                dots.add(e.getType());
                dateData.setDots(dots);
                dateData.setSelected(true);
                dateDataMap.put(dateString, dateData);
            } else {
                dateDataMap.get(dateString).getDots().add(e.getType());
            }
        });

	    return new CalendarDto(eventTypeList, dateDataMap);
    }

    public List<EventType> getEventTypes(List<Event> events) {
        List<String> types = events.stream().map(e -> e.getType()).filter(type -> type != null).distinct().collect(Collectors.toList());
        List<EventType> eventTypes = new ArrayList<>();

        for (int i = 0 ;i < types.size(); i++) {
            String type = types.get(i);
            eventTypes.add(new EventType(type, CalendarDto.colors[i], CalendarDto.colors[i]));
        }
        return eventTypes;
    }
}
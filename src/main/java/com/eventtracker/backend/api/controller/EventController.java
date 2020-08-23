package com.eventtracker.backend.api.controller;

import com.eventtracker.backend.api.dto.reactNative.CalendarDateData;
import com.eventtracker.backend.api.dto.reactNative.CalendarDto;
import com.eventtracker.backend.api.dto.reactNative.EventType;
import com.eventtracker.backend.api.dto.web.WebCalendarDto;
import com.eventtracker.backend.entities.Event;
import com.eventtracker.backend.repository.EventRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
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

    @GetMapping("/events/calendarData/rn")
    @CrossOrigin()
    public CalendarDto getAllEventsForCalendarRN() {
        return this.convertEntitiesToDtoRN(eventRepository.findAll());
    }

    @GetMapping("/events/calendarData/web")
    @CrossOrigin()
    public List<WebCalendarDto> getAllEventsForCalendarWeb() {
        return this.convertEntitiesToDtoWeb(eventRepository.findAll());
    }

	@PostMapping("/event")
	@CrossOrigin()
	public Event createEvent(@Valid @RequestBody Event event) {
		return eventRepository.insert(event);
	}

	public CalendarDto convertEntitiesToDtoRN(List<Event> events) {
	    Map<String, CalendarDateData> dateDataMap = new HashMap();
	    List<EventType> eventTypeList = this.getEventTypes(events);

	    events.stream().forEach(e -> {
	        EventType type = eventTypeList.stream().filter(et -> et.getKey().equals(e.getType())).findFirst().get();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = df.format(e.getStartTime());
            if (dateDataMap.get(dateString) == null) {
                CalendarDateData dateData = new CalendarDateData();
                HashSet<EventType> dots = new HashSet<>();
                dots.add(type);
                dateData.setDots(dots);
                dateData.setSelected(true);
                dateDataMap.put(dateString, dateData);
            } else {
                dateDataMap.get(dateString).getDots().add(type);
            }
        });

	    return new CalendarDto(eventTypeList, dateDataMap);
    }

    public List<WebCalendarDto> convertEntitiesToDtoWeb(List<Event> events) {
	    List<WebCalendarDto> webDtos = new ArrayList<>();
	    events.stream().forEach(e -> {
	        WebCalendarDto webCalendarDto = new WebCalendarDto();
	        webCalendarDto.setAllDay(false);
	        webCalendarDto.setTitle(e.getType());
	        webCalendarDto.setStart(e.getStartTime());

	        if (e.getEndTime() == null && e.getDuration() != null) {
                Date endTime = (Date) e.getStartTime().clone();
                endTime.setTime(e.getStartTime().getTime() + e.getDuration()*1000);
	            webCalendarDto.setEnd(endTime);
            } else if (e.isOnGoing()){
	            webCalendarDto.setEnd(new Date());
            } else {
                webCalendarDto.setEnd(e.getEndTime());
            }
	        webDtos.add(webCalendarDto);
        });
	    return webDtos;
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
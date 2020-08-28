package com.eventtracker.backend.api.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eventtracker.backend.api.dto.web.WebCalendarDto;
import com.eventtracker.backend.entities.Event;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class EventControllerTest {

    EventController controller = new EventController();
    static List<Event> events = new ArrayList<>();


    @Test
    public void testGetOnGoingEventsInWeb() {
        Event noEndDate = new Event();
        noEndDate.setName("No end date");
        noEndDate.setStartTime(new Date());

        events.add(noEndDate);
        List<WebCalendarDto> webCalendarDtos = controller.convertEntitiesToDtoWeb(events);
        assertTrue(webCalendarDtos.get(0).getEnd().after(webCalendarDtos.get(0).getStart()));
        assertEquals(webCalendarDtos.get(0).getEnd().getDay(), new Date().getDay());
    }

}

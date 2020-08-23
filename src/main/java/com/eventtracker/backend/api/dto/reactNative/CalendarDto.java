package com.eventtracker.backend.api.dto.reactNative;


import java.util.List;
import java.util.Map;

public class CalendarDto {


    public final static String[] colors = {"red", "blue", "green", "purple", "yellow", "black", "teal", "orange"};

    public List<EventType> eventTypeList;
    public Map<String, CalendarDateData> dateMap;

    public CalendarDto() {
    }

    public CalendarDto(List<EventType> eventTypeList,
            Map<String, CalendarDateData> dateMap) {
        this.eventTypeList = eventTypeList;
        this.dateMap = dateMap;
    }

    public List<EventType> getEventTypeList() {
        return eventTypeList;
    }

    public void setEventTypeList(List<EventType> eventTypeList) {
        this.eventTypeList = eventTypeList;
    }

    public Map<String, CalendarDateData> getDateMap() {
        return dateMap;
    }

    public void setDateMap(
            Map<String, CalendarDateData> dateMap) {
        this.dateMap = dateMap;
    }
}

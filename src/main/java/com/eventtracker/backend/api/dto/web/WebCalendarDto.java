package com.eventtracker.backend.api.dto.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebCalendarDto {

    public String title;
    public Date start;
    public Date end;
    public boolean allDay;

    public static String toJson(WebCalendarDto dto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(dto);
    }

    public static String toJson(List<WebCalendarDto> dtos) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(dtos);
    }

}

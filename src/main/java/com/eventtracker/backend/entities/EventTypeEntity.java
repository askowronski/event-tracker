package com.eventtracker.backend.entities;

import java.util.Map;
import lombok.Data;

@Data
public class EventTypeEntity {

    public String type;
    public Map<String,Object> fields;
}

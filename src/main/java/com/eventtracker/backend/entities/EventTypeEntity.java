package com.eventtracker.backend.entities;

import java.util.ArrayList;
import java.util.Map;
import lombok.Data;

@Data
public class EventTypeEntity {

    public String type;
    public ArrayList<Map<String,Object>> fields;
}

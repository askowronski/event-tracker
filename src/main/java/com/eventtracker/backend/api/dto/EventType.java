package com.eventtracker.backend.api.dto;

public class EventType {

    public String key;
    public String color;
    public String selectedDotColor;

    public EventType(String key, String color, String selectedDotColor) {
        this.key = key;
        this.color = color;
        this.selectedDotColor = selectedDotColor;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSelectedDotColor() {
        return selectedDotColor;
    }

    public void setSelectedDotColor(String selectedDotColor) {
        this.selectedDotColor = selectedDotColor;
    }
}

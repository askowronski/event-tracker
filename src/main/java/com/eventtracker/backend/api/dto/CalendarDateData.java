package com.eventtracker.backend.api.dto;

import java.util.HashSet;
import java.util.List;

public class CalendarDateData {

    public HashSet<String> dots;
    public boolean selected;

    public CalendarDateData() {
    }

    public CalendarDateData(HashSet<String> dots, boolean selected) {
        this.dots = dots;
        this.selected = selected;
    }

    public HashSet<String> getDots() {
        return dots;
    }

    public void setDots(HashSet<String> dots) {
        this.dots = dots;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}

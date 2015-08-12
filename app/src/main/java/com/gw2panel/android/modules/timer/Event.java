package com.gw2panel.android.modules.timer;

public class Event {

    private Integer time;
    private String name;

    public Event(Integer i, String s) {
        this.time = i;
        this.name = s;
    }

    public Integer getTime() {
        return time;
    }

    public String getName() {
        return name;
    }
}

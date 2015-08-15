package com.gw2panel.android.modules.timer;

public class Event {

    private int time;
    private String name;

    public Event(Integer time, String name) {
        this.time = time;
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

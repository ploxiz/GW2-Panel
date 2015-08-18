package com.gw2panel.android.modules.timer;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Timer {

    private List<Event> events = new ArrayList<>();
    private List<Event> upcomingEvents = new ArrayList<>();

    public Timer() {
        events.add(new Event(0, "Taidha Convington,Tequatl"));
        events.add(new Event(15, "Frozen Maw"));
        events.add(new Event(30, "Megadestroyer"));
        events.add(new Event(45, "Fire Elemental"));
        events.add(new Event(60, "The Shatterer,Evolved Jungle Wurm"));
        events.add(new Event(75, "Jungle Wurm"));
        events.add(new Event(90, "Modniir Ulgoth"));
        events.add(new Event(105, "Shadow Behemoth"));
        events.add(new Event(120, "Golem Mark II, Karka Queen"));
        events.add(new Event(135, "Frozen Maw"));
        events.add(new Event(150, "Claw of Jormag"));
        events.add(new Event(165, "Fire Elemental"));
        events.add(new Event(180, "Taidha Convington"));
        events.add(new Event(195, "Jungle Wurm"));
        events.add(new Event(210, "Megadestroyer"));
        events.add(new Event(225, "Shadow Behemoth"));
        events.add(new Event(240, "The Shatterer,Evolved Jungle Wurm"));
        events.add(new Event(255, "Frozen Maw"));
        events.add(new Event(270, "Modniir Ulgoth"));
        events.add(new Event(285, "Fire Elemental"));
        events.add(new Event(300, "Golem Mark II"));
        events.add(new Event(315, "Jungle Wurm"));
        events.add(new Event(330, "Claw of Jormag"));
        events.add(new Event(345, "Shadow Behemoth"));
        events.add(new Event(360, "Taidha Covington,Karka Queen"));
        events.add(new Event(375, "Frozen Maw"));
        events.add(new Event(390, "Megadestroyer"));
        events.add(new Event(405, "Fire Elemental"));
        events.add(new Event(420, "The Shatterer,Tequatl"));
        events.add(new Event(435, "Jungle Wurm"));
        events.add(new Event(450, "Modniir Ulgoth"));
        events.add(new Event(465, "Shadow Behemoth"));
        events.add(new Event(480, "Golem Mark II,Evolved Jungle Wurm"));
        events.add(new Event(495, "Frozen Maw"));
        events.add(new Event(510, "Claw of Jormag"));
        events.add(new Event(525, "Fire Elemental"));
        events.add(new Event(540, "Taidha Covington"));
        events.add(new Event(555, "Jungle Wurm"));
        events.add(new Event(570, "Megadestroyer"));
        events.add(new Event(585, "Shadow Behemoth"));
        events.add(new Event(600, "The Shatterer"));
        events.add(new Event(615, "Frozen Maw"));
        events.add(new Event(630, "Modniir Ulgoth,Karka Queen"));
        events.add(new Event(645, "Fire Elemental"));
        events.add(new Event(660, "Golem Mark II"));
        events.add(new Event(675, "Jungle Wurm"));
        events.add(new Event(690, "Claw of Jormag,Tequatl"));
        events.add(new Event(705, "Shadow Behemoth"));
        events.add(new Event(720, "Taidha Covington"));
        events.add(new Event(735, "Frozen Maw"));
        events.add(new Event(750, "Megadestroyer,Evolved Jungle Wurm"));
        events.add(new Event(765, "Fire Elementa"));
        events.add(new Event(780, "The Shatterer"));
        events.add(new Event(795, "Jungle Wurm"));
        events.add(new Event(810, "Modniir Ulgoth"));
        events.add(new Event(825, "Shadow Behemoth"));
        events.add(new Event(840, "Golem Mark II"));
        events.add(new Event(855, "Frozen Maw"));
        events.add(new Event(870, "Claw of Jormag"));
        events.add(new Event(885, "Fire Elemental"));
        events.add(new Event(900, "Taidha Covington,Karka Queen"));
        events.add(new Event(915, "Jungle Wurm"));
        events.add(new Event(930, "Megadestroyer"));
        events.add(new Event(945, "Shadow Behemoth"));
        events.add(new Event(960, "The Shatterer,Tequatl"));
        events.add(new Event(975, "Frozen Maw"));
        events.add(new Event(990, "Modniir Ulgoth"));
        events.add(new Event(1005, "Fire Elemental"));
        events.add(new Event(1020, "Golem Mark II,Evolved Jungle Wurm"));
        events.add(new Event(1035, "Jungle Wurm"));
        events.add(new Event(1050, "Claw of Jormag"));
        events.add(new Event(1065, "Shadow Behemoth"));
        events.add(new Event(1080, "Taidha Covington,Karka Queen"));
        events.add(new Event(1095, "Frozen Maw"));
        events.add(new Event(1110, "Megadestroyer"));
        events.add(new Event(1125, "Fire Elemental"));
        events.add(new Event(1140, "The Shatterer,Tequatl"));
        events.add(new Event(1155, "Jungle Wurm"));
        events.add(new Event(1170, "Modniir Ulgoth"));
        events.add(new Event(1185, "Shadow Behemoth"));
        events.add(new Event(1200, "Golem Mark II,Evolved Jungle Wurm"));
        events.add(new Event(1215, "Frozen Maw"));
        events.add(new Event(1230, "Claw of Jormag"));
        events.add(new Event(1245, "Fire Elemental"));
        events.add(new Event(1260, "Taidha Covington"));
        events.add(new Event(1275, "Jungle Wurm"));
        events.add(new Event(1290, "Megadestroyer"));
        events.add(new Event(1305, "Shadow Behemoth"));
        events.add(new Event(1320, "The Shatterer"));
        events.add(new Event(1335, "Frozen Maw"));
        events.add(new Event(1350, "Modniir Ulgoth"));
        events.add(new Event(1365, "Fire Elemental"));
        events.add(new Event(1380, "Golem Mark II,Karka Queen"));
        events.add(new Event(1395, "Jungle Wurm"));
        events.add(new Event(1410, "Claw of Jormag"));
        events.add(new Event(1425, "Shadow Behemoth"));

        fetch();
    }

    public void fetch() {
        upcomingEvents.clear();
        int nextUpcomingEventIndex = fetchNextUpcomingEventIndex(); System.out.println("Index = " + nextUpcomingEventIndex); // out
        int offset = getCurrentTimeZoneOffset(); System.out.println("Offset: " + convert(offset)); // out

        if (events.size() - 1 - nextUpcomingEventIndex >= 15) {
            for (int i = nextUpcomingEventIndex; i < nextUpcomingEventIndex + 15; i++ ) {
                upcomingEvents.add(new Event(events.get(i).getTime() + offset, events.get(i).getName()));
            }
        } else {
            for (int i = nextUpcomingEventIndex; i < events.size(); i++) {
                upcomingEvents.add(new Event(events.get(i).getTime() + offset, events.get(i).getName()));
            }
            int i = 0;
            while (upcomingEvents.size() < 15) {
                upcomingEvents.add(new Event(events.get(i).getTime() + offset, events.get(i).getName()));
                i++;
            }
        }
        for (Event event : upcomingEvents) {
            System.out.println(event.getName() + " at " + convert(event.getTime())); // out
        }

        // Separates the events which happen at the same time.
        for (int i = 0; i < upcomingEvents.size(); i++) {
            if (upcomingEvents.get(i).getName().contains(",")) {
                String[] split = upcomingEvents.get(i).getName().split(",");
                String name1 = split[0];
                String name2 = split[1];
                int time = upcomingEvents.get(i).getTime();
                upcomingEvents.get(i).setName(name1);
                upcomingEvents.add(i + 1, new Event(time, name2));
            }
        }
    }

    private int fetchNextUpcomingEventIndex() {
        DateTime dateTime = new DateTime(DateTimeZone.UTC);
        int hour = dateTime.getHourOfDay() * 60;
        int minute = dateTime.getMinuteOfHour();

        int timeUTC = hour + minute; System.out.println("UTC Time: " + convert(timeUTC));
        int nextEventIndex = 0;
        for (Event event : events) {
            if (timeUTC - event.getTime() >= 0 && timeUTC - event.getTime() < 15) { // also includes the event which is currently going on!
                System.out.println(events.get(nextEventIndex).getName() + " at " + convert(events.get(nextEventIndex).getTime()) + " UTC"); // out
                return nextEventIndex;
            }
            nextEventIndex++;
        }

        return 0;
    }

    private int getCurrentTimeZoneOffset() { // TODO: check with negative offset! - BUG - possibly fixed
        DateTimeZone tz = DateTimeZone.getDefault();
        Long instant = DateTime.now().getMillis();
        long offsetInMilliseconds = tz.getOffset(instant);
        return (int) TimeUnit.MILLISECONDS.toMinutes(offsetInMilliseconds);
    }

    // used only for testing purposes (feedback in console)
    public String convert(int time) {
        int hour = time / 60;
        int minute = time % 60;

        String hourString = Integer.toString(hour);
        String minuteString = Integer.toString(minute);

        if (hour >= 24) {
            hour = hour - 24;
        }

        if (hour < 10) {
            hourString = "0" + Integer.toString(hour);
        }
        if (minute < 10 ) {
            minuteString = "0" + Integer.toString(minute);
        }

        return hourString + ":" + minuteString;
    }

    public List<Event> getUpcomingEvents() {
        return upcomingEvents;
    }

    public static void main(String[] args) {
        new Timer();
    }

}

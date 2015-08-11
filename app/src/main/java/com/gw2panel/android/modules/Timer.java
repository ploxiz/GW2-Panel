package com.gw2panel.android.modules;

/*
Figure ou the timezone
Figure out what boss is up (the exact position in the timer table)
3. Show everything that is after that specific point.
*/

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Timer {

    public Timer() {
        HashMap<Integer, String> events = new HashMap<>();
        events.put(0, "Taidha Convington,Tequatl");
        events.put(15, "Frozen Maw");
        events.put(30, "Megadestroyer");
        events.put(45, "Fire Elemental");
        events.put(60, "The Shatterer,Evolved Jungle Wurm");
        events.put(75, "Jungle Wurm");
        events.put(90, "Modniir Ulgoth");
        events.put(105, "Shadow Behemoth");
        events.put(120, "Golem Mark II, Karka Queen");
        events.put(135, "Frozen Maw");
        events.put(150, "Claw of Jormag");
        events.put(165, "Fire Elemental");
        events.put(180, "Taidha Convington");
        events.put(195, "Jungle Wurm");
        events.put(210, "Megadestroyer");
        events.put(225, "Shadow Behemoth");
        events.put(240, "The Shatterer,Evolved Jungle Wurm");
        events.put(255, "Frozen Maw");
        events.put(270, "Modniir Ulgoth");
        events.put(285, "Fire Elemental");
        events.put(300, "Golem Mark II");
        events.put(315, "Jungle Wurm");
        events.put(330, "Claw of Jormag");
        events.put(345, "Shadow Behemoth");
        events.put(360, "Taidha Covington,Karka Queen");
        events.put(375, "Frozen Maw");
        events.put(390, "Megadestroyer");
        events.put(405, "Fire Elemental");
        events.put(420, "The Shatterer,Tequatl");
        events.put(435, "Jungle Wurm");
        events.put(450, "Modniir Ulgoth");
        events.put(465, "Shadow Behemoth");
        events.put(480, "Golem Mark II,Evolved Jungle Wurm");
        events.put(495, "Frozen Maw");
        events.put(510, "Claw of Jormag");
        events.put(525, "Fire Elemental");
        events.put(540, "Taidha Covington");
        events.put(555, "Jungle Wurm");
        events.put(570, "Megadestroyer");
        events.put(585, "Shadow Behemoth");
        events.put(600, "The Shatterer");
        events.put(615, "Frozen Maw");
        events.put(630, "Modniir Ulgoth,Karka Queen");
        events.put(645, "Fire Elemental");
        events.put(660, "Golem Mark II");
        events.put(675, "Jungle Wurm");
        events.put(690, "Claw of Jormag,Tequatl");
        events.put(705, "Shadow Behemoth");
        events.put(720, "Taidha Covington");
        events.put(735, "Frozen Maw");
        events.put(750, "Megadestroyer,Evolved Jungle Wurm");
        events.put(765, "Fire Elementa");
        events.put(780, "The Shatterer");
        events.put(795, "Jungle Wurm");
        events.put(810, "Modniir Ulgoth");
        events.put(825, "Shadow Behemoth");
        events.put(840, "Golem Mark II");
        events.put(855, "Frozen Maw");
        events.put(870, "Claw of Jormag");
        events.put(885, "Fire Elemental");
        events.put(900, "Taidha Covington,Karka Queen");
        events.put(915, "Jungle Wurm");
        events.put(930, "Megadestroyer");
        events.put(945, "Shadow Behemoth");
        events.put(960, "The Shatterer,Tequatl");
        events.put(975, "Frozen Maw");
        events.put(990, "Modniir Ulgoth");
        events.put(1005, "Fire Elemental");
        events.put(1020, "Golem Mark II,Evolved Jungle Wurm");
        events.put(1035, "Jungle Wurm");
        events.put(1050, "Claw of Jormag");
        events.put(1065, "Shadow Behemoth");
        events.put(1080, "Taidha Covington,Karka Queen");
        events.put(1095, "Frozen Maw");
        events.put(1110, "Megadestroyer");
        events.put(1125, "Fire Elemental");
        events.put(1140, "The Shatterer,Tequatl");
        events.put(1155, "Jungle Wurm");
        events.put(1170, "Modniir Ulgoth");
        events.put(1185, "Shadow Behemoth");
        events.put(1200, "Golem Mark II,Evolved Jungle Wurm");
        events.put(1215, "Frozen Maw");
        events.put(1230, "Claw of Jormag");
        events.put(1245, "Fire Elemental");
        events.put(1260, "Taidha Covington");
        events.put(1275, "Jungle Wurm");
        events.put(1290, "Megadestroyer");
        events.put(1305, "Shadow Behemoth");
        events.put(1320, "The Shatterer");
        events.put(1335, "Frozen Maw");
        events.put(1350, "Modniir Ulgoth");
        events.put(1365, "Fire Elemental");
        events.put(1380, "Golem Mark II,Karka Queen");
        events.put(1395, "Jungle Wurm");
        events.put(1410, "Claw of Jormag");
        events.put(1425, "Shadow Behemoth");

        int offset = getCurrentTimeZoneOffset() * 60; System.out.println("Offset: " + convert(offset));
        int localTime = getLocalTime(); System.out.println("Local Time: " + convert(localTime));
        int utcTime = localTime - offset; System.out.println("UTC Time: " + convert(utcTime));

        int nextEventTime = 0;
        for (int eventTime : events.keySet()) {
            if (utcTime - eventTime <= 0 && utcTime - eventTime > -15) {
                nextEventTime = eventTime; System.out.println("Next event: " + events.get(nextEventTime) + " at " + convert(nextEventTime + offset));
                break;
            }
        }

        HashMap<Integer, String> upcomingEvents = new HashMap<>();
        for (int i = nextEventTime; i <= 1425; i = i + 15) {
            upcomingEvents.put(i, events.get(i));
        }
        if (upcomingEvents.size() < 10) {
            int i = 0;
            while (upcomingEvents.size() == 10) {
                upcomingEvents.put(i, events.get(i));
                i = i + 15;
            }
        }
        for (int time : upcomingEvents.keySet()) {
            System.out.println(upcomingEvents.get(time) + " at " + convert(time));
        }

    }

    private int getLocalTime() {
        DateTime time = new DateTime();

        int second = time.getSecondOfMinute(); // TODO: add seconds - ENCHANTMENT
        int minute = time.getMinuteOfHour();
        int hour = time.getHourOfDay();

        return hour * 60 + minute;
    }

    private String convert(int time) {
        int hour = time / 60;
        int minute = time % 60;

        String hourString = Integer.toString(hour);
        String minuteString = Integer.toString(minute);
        if (hour < 10) {
            hourString = "0" + Integer.toString(hour);
        }
        if (minute < 10) {
            minuteString = "0" + Integer.toString(minute);
        }

        return hourString + ":" + minuteString;
    }

    private int getCurrentTimeZoneOffset() { // TODO: check with negative offset! - BUG
        DateTimeZone tz = DateTimeZone.getDefault();
        Long instant = DateTime.now().getMillis();
        long offsetInMilliseconds = tz.getOffset(instant);
        return (int) TimeUnit.MILLISECONDS.toHours(offsetInMilliseconds);
    }

    public static void main(String[] args) {
        new Timer();
    }

}

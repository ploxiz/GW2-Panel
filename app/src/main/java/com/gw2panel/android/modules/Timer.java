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

    private static String[][] events = new String[][] {
            {"000000", "Taidha Convington,Tequatl"},
            {"001500", "Frozen Maw"},
            {"003000", "Megadestroyer"},
            {"004500", "Fire Elemental"},
            {"010000", "The Shatterer,Evolved Jungle Wurm"},
            {"011500", "Jungle Wurm"},
            {"013000", "Modniir Ulgoth"},
            {"014500", "Shadow Behemoth"},
            {"020000", "Golem Mark II, Karka Queen"},
            {"021500", "Frozen Maw"},
            {"023000", "Claw of Jormag"},
            {"024500", "Fire Elemental"},
            {"030000", "Taidha Convington"},
            {"031500", "Jungle Wurm"},
            {"033000", "Megadestroyer"},
            {"034500", "Shadow Behemoth"},
            {"040000", "The Shatterer,Evolved Jungle Wurm"},
            {"041500", "Frozen Maw"},
            {"043000", "Modniir Ulgoth"},
            {"044500", "Fire Elemental"},
            {"050000", "Golem Mark II"},
            {"051500", "Jungle Wurm"},
            {"053000", "Claw of Jormag"},
            {"054500", "Shadow Behemoth"},
            {"060000", "Taidha Covington,Karka Queen"},
            {"061500", "Frozen Maw"},
            {"063000", "Megadestroyer"},
            {"064500", "Fire Elemental"},
            {"070000", "The Shatterer,Tequatl"},
            {"071500", "Jungle Wurm"},
            {"073000", "Modniir Ulgoth"},
            {"074500", "Shadow Behemoth"},
            {"080000", "Golem Mark II,Evolved Jungle Wurm"},
            {"081500", "Frozen Maw"},
            {"083000", "Claw of Jormag"},
            {"084500", "Fire Elemental"},
            {"090000", "Taidha Covington"},
            {"091500", "Jungle Wurm"},
            {"093000", "Megadestroyer"},
            {"094500", "Shadow Behemoth"},
            {"100000", "The Shatterer"},
            {"101500", "Frozen Maw"},
            {"103000", "Modniir Ulgoth,Karka Queen"},
            {"104500", "Fire Elemental"},
            {"110000", "Golem Mark II"},
            {"111500", "Jungle Wurm"},
            {"113000", "Claw of Jormag,Tequatl"},
            {"114500", "Shadow Behemoth"},
            {"120000", "Taidha Covington"},
            {"121500", "Frozen Maw"},
            {"123000", "Megadestroyer,Evolved Jungle Wurm"},
            {"124500", "Fire Elementa"},
            {"130000", "The Shatterer"},
            {"131500", "Jungle Wurm"},
            {"133000", "Modniir Ulgoth"},
            {"134500", "Shadow Behemoth"},
            {"140000", "Golem Mark II"},
            {"141500", "Frozen Maw"},
            {"143000", "Claw of Jormag"},
            {"144500", "Fire Elemental"},
            {"150000", "Taidha Covington,Karka Queen"},
            {"151500", "Jungle Wurm"},
            {"153000", "Megadestroyer"},
            {"154500", "Shadow Behemoth"},
            {"160000", "The Shatterer,Tequatl"},
            {"161500", "Frozen Maw"},
            {"163000", "Modniir Ulgoth"},
            {"164500", "Fire Elemental"},
            {"170000", "Golem Mark II,Evolved Jungle Wurm"},
            {"171500", "Jungle Wurm"},
            {"173000", "Claw of Jormag"},
            {"174500", "Shadow Behemoth"},
            {"180000", "Taidha Covington,Karka Queen"},
            {"181500", "Frozen Maw"},
            {"183000", "Megadestroyer"},
            {"184500", "Fire Elemental"},
            {"190000", "The Shatterer,Tequatl"},
            {"191500", "Jungle Wurm"},
            {"193000", "Modniir Ulgoth"},
            {"194500", "Shadow Behemoth"},
            {"200000", "Golem Mark II,Evolved Jungle Wurm"},
            {"201500", "Frozen Maw"},
            {"203000", "Claw of Jormag"},
            {"204500", "Fire Elemental"},
            {"210000", "Taidha Covington"},
            {"211500", "Jungle Wurm"},
            {"213000", "Megadestroyer"},
            {"214500", "Shadow Behemoth"},
            {"220000", "The Shatterer"},
            {"221500", "Frozen Maw"},
            {"223000", "Modniir Ulgoth"},
            {"224500", "Fire Elemental"},
            {"230000", "Golem Mark II,Karka Queen"},
            {"231500", "Jungle Wurm"},
            {"233000", "Claw of Jormag"},
            {"234500", "Shadow Behemoth"}
    };

    public static HashMap<String, String> upcomingEvents = new HashMap<>();


    public Timer() {
        int localTime = getLocalTime();
        int offset = getCurrentTimeZoneOffset() * 10000;
        int utcTime = localTime - offset;

        int position = 0;
        for (int i = 0; i < events.length; i++) {
            if (localTime - Integer.parseInt(events[i][0]) > -1500 && localTime - Integer.parseInt(events[i][0]) < 0) {
                position = i;
                break;
            }
        }

        for (int i = position; i < events.length; i++) {
            upcomingEvents.put(events[i][0], events[i][1]);
        }
        int i = 0;
        while (upcomingEvents.size() < 10) {
            upcomingEvents.put(events[i][0], events[i][1]);
            i++;
        }
    }

    private static int getLocalTime() {
        DateTime time = new DateTime();

        int second = time.getSecondOfMinute();
        int minute = time.getMinuteOfHour();
        int hour = time.getHourOfDay();

        return Integer.parseInt(format(hour) + format(minute) + format(second)); // TODO: int=>no0
    }

    private static String format(int l) {
        if (l < 10) {
            return "0" + Integer.toString(l);
        }
        return Integer.toString(l);
    }

    private static int getCurrentTimeZoneOffset() {
        DateTimeZone tz = DateTimeZone.getDefault();
        Long instant = DateTime.now().getMillis();
        long offsetInMilliseconds = tz.getOffset(instant);
        return (int) TimeUnit.MILLISECONDS.toHours(offsetInMilliseconds);
    }

    public static void main(String[] args) {

        int localTime = getLocalTime();
        System.out.println("Local Time: " + localTime);

        int offset = getCurrentTimeZoneOffset() * 10000;
        System.out.println("Offset: " + offset);

        int utcTime = localTime - offset;
        System.out.println("UTC Time: " + utcTime);

        int position = 0;
        for (int i = 0; i < events.length; i++) {
            if (localTime - Integer.parseInt(events[i][0]) > -1500 && localTime - Integer.parseInt(events[i][0]) < 0) {
                position = i;
                break;
            }
        }
        System.out.println(events[position][1]);

        for (int i = position; i < events.length; i++) {
            upcomingEvents.put(events[i][0], events[i][1]);
        }
        int i = 0;
        while (upcomingEvents.size() < 10) {
            upcomingEvents.put(events[i][0], events[i][1]);
            i++;
        }
        for (Map.Entry<String, String> upcomingEvent: upcomingEvents.entrySet()) {
            System.out.println(upcomingEvent.getKey() + " " + upcomingEvent.getValue());
        }
    }

}

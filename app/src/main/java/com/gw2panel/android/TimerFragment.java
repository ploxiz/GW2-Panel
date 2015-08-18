package com.gw2panel.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.gw2panel.android.adapters.NewsAdapter;
import com.gw2panel.android.adapters.TimerAdapter;
import com.gw2panel.android.adapters.objects.TimerObject;
import com.gw2panel.android.modules.timer.Event;
import com.gw2panel.android.modules.timer.Timer;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimerFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TimerFragment newInstance(int sectionNumber) {
        TimerFragment fragment = new TimerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timer, container, false);

        final ListView listView = (ListView) rootView.findViewById(R.id.timer_listView);
        TextView eventName = (TextView) rootView.findViewById(R.id.timer_textView_name);
        TextView eventStart = (TextView) rootView.findViewById(R.id.timer_textView_startTimer);
        TextView eventCount = (TextView) rootView.findViewById(R.id.timer_textView_countTimer);

        // TODO: there is small delay when calls the Timer. - BUG
        final Timer timer = new Timer();
        List<Event> upcomingEvents = timer.getUpcomingEvents();

        final ArrayList<TimerObject> timerObject = new ArrayList<>();

        timerObject.add(new TimerObject(upcomingEvents.get(0).getName(), "In progress", convert(upcomingEvents.get(0).getTime()), ""));
        if (upcomingEvents.get(0).getTime() == upcomingEvents.get(1).getTime()) {
            timerObject.add(new TimerObject(upcomingEvents.get(1).getName(), "In progress", convert(upcomingEvents.get(1).getTime()), ""));
            for (int i = 2; i < upcomingEvents.size(); i++) {
                timerObject.add(new TimerObject(upcomingEvents.get(i).getName(), "Upcoming", convert(upcomingEvents.get(i).getTime()), count(upcomingEvents.get(i).getTime())));
            }
        } else {
            for (int i = 1; i < upcomingEvents.size(); i++) {
                timerObject.add(new TimerObject(upcomingEvents.get(i).getName(), "Upcoming", convert(upcomingEvents.get(i).getTime()), count(upcomingEvents.get(i).getTime())));
            }
        }
        final TimerAdapter timerAdapter = new TimerAdapter(getActivity(), timerObject);
        listView.setAdapter(timerAdapter);

        /* TODO:
         1. find the difference between the current second and the one at which the next event is happening (0)
         2. refresh after that difference and after refresh at every minute
         */

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timer.fetch();
                List<Event> upcomingEvents = timer.getUpcomingEvents();

                timerObject.clear();
                timerObject.add(new TimerObject(upcomingEvents.get(0).getName(), "In progress", convert(upcomingEvents.get(0).getTime()), ""));
                if (upcomingEvents.get(0).getTime() == upcomingEvents.get(1).getTime()) {
                    timerObject.add(new TimerObject(upcomingEvents.get(1).getName(), "In progress", convert(upcomingEvents.get(1).getTime()), ""));
                    for (int i = 2; i < upcomingEvents.size(); i++) {
                        timerObject.add(new TimerObject(upcomingEvents.get(i).getName(), "Upcoming", convert(upcomingEvents.get(i).getTime()), count(upcomingEvents.get(i).getTime())));
                    }
                } else {
                    for (int i = 1; i < upcomingEvents.size(); i++) {
                        timerObject.add(new TimerObject(upcomingEvents.get(i).getName(), "Upcoming", convert(upcomingEvents.get(i).getTime()), count(upcomingEvents.get(i).getTime())));
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timerAdapter.notifyDataSetChanged();
                    }
                });
                handler.postDelayed(this, 60 * 1000);
            }
        }, 60 * 1000);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    private void populateListView(ListView listView, Timer timer) {
        List<Event> upcomingEvents = timer.getUpcomingEvents();

        ArrayList<TimerObject> timerObject = new ArrayList<>();

        timerObject.add(new TimerObject(upcomingEvents.get(0).getName(), "In progress", convert(upcomingEvents.get(0).getTime()), ""));
        if (upcomingEvents.get(0).getTime() == upcomingEvents.get(1).getTime()) {
            timerObject.add(new TimerObject(upcomingEvents.get(1).getName(), "In progress", convert(upcomingEvents.get(1).getTime()), ""));
            for (int i = 2; i < upcomingEvents.size(); i++) {
                timerObject.add(new TimerObject(upcomingEvents.get(i).getName(), "Upcoming", convert(upcomingEvents.get(i).getTime()), count(upcomingEvents.get(i).getTime())));
            }
        } else {
            for (int i = 1; i < upcomingEvents.size(); i++) {
                timerObject.add(new TimerObject(upcomingEvents.get(i).getName(), "Upcoming", convert(upcomingEvents.get(i).getTime()), count(upcomingEvents.get(i).getTime())));
            }
        }
        TimerAdapter timerAdapter = new TimerAdapter(getActivity(), timerObject);
        listView.setAdapter(timerAdapter);
    }

    private String count(int eventTime) {
        DateTime dateTime = new DateTime();
        int hour = dateTime.getHourOfDay() * 60;
        int minute = dateTime.getMinuteOfHour();
        int localTime = hour + minute;

        String count = convert(eventTime - localTime);
        String[] split = count.split(":");
        String hourString = Integer.toString(Integer.parseInt(split[0])); // removes the 0 at the beginning of the String
        String minuteString = Integer.toString(Integer.parseInt(split[1])); // removes the 0 at the beginning of the String

        // formatting
        if (Integer.parseInt(hourString) == 0) {
            if (Integer.parseInt(minuteString) == 1) {
                return minuteString + " minute";
            } else return minuteString + " minutes";
        } else {
            if (Integer.parseInt(hourString) != 0 && Integer.parseInt(minuteString) == 0) {
                if (Integer.parseInt(hourString) == 1) {
                    return hourString + " hour";
                } else return hourString + " hours";
            }
            else {
                if (Integer.parseInt(hourString) == 1) {
                    if (Integer.parseInt(minuteString) == 1) {
                        return hourString + " hour and " + minuteString + " minute";
                    }
                    else return hourString + " hour and " + minuteString + " minutes";
                } else {
                    if (Integer.parseInt(minuteString) == 1) {
                        return hourString + " hours and " + minuteString + " minute";
                    }
                    else return hourString + " hours and " + minuteString + " minutes";
                }
            }
        }
    }

    private String convert(int time) {
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
}

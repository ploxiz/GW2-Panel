package com.gw2panel.android;

import android.app.Activity;
import android.os.Bundle;
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

        final Timer timer = new Timer();

        populateListView(listView, timer);

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

        timerObject.add(new TimerObject(upcomingEvents.get(0).getName(), "In progress", Integer.toString(upcomingEvents.get(0).getTime()), "PLACEHOLDER"));
        for (int i = 1; i < upcomingEvents.size(); i++) {
            // newsObject.add(new NewsObject(news.getTitles().get(i), news.getDescriptions().get(i), news.getDates().get(i)));
            // timerObject.add(new TimerObject(upcomingEvents.get(i).getName(), "In progress", timer.convert(upcomingEvents.get(i).getTime() + timer.getOffset()) , "PLACEHOLDER"));
            timerObject.add(new TimerObject(upcomingEvents.get(0).getName(), "Upcoming", Integer.toString(upcomingEvents.get(0).getTime()), "PLACEHOLDER"));
        }

        TimerAdapter timerAdapter = new TimerAdapter(getActivity(), timerObject);
        listView.setAdapter(timerAdapter);
    }
}

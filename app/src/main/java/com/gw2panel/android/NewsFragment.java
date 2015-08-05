package com.gw2panel.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gw2panel.android.adapters.NewsAdapter;
import com.gw2panel.android.adapters.objects.NewsObject;
import com.gw2panel.android.modules.News;

import java.io.IOException;
import java.util.ArrayList;

public class NewsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static NewsFragment newInstance(int sectionNumber) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        final TextView errorMessage = (TextView) rootView.findViewById(R.id.news_textview_error);
        final Button retryButton = (Button) rootView.findViewById(R.id.news_button_retry);
        final ListView listView = (ListView) rootView.findViewById(R.id.news_listView);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task(errorMessage, retryButton, listView).execute();
            }
        });
        new Task(errorMessage, retryButton, listView).execute();

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    // In order to avoid using a network operation in the main thread.
    private class Task extends AsyncTask<Void, Void, News> {

        private ProgressDialog dialog;

        private TextView mErrorMessage;
        private Button mRetryButton;
        private ListView mListView;

        public Task(TextView errorMessage, Button retryButton, ListView listView) {
            this.mErrorMessage = errorMessage;
            this.mRetryButton = retryButton;
            this.mListView = listView;

            dialog = new ProgressDialog(getActivity());
        }

        @Override
        protected News doInBackground(Void... params) {
            try {
                return new News();
            } catch (IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showError(mErrorMessage, mRetryButton, true);
                    }
                });
                return null;
            }
        }

        protected void onPreExecute() {
            this.dialog.setMessage("Retrieving content...");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(final News news) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if (news != null) {
                // Checks if there was a connection problem and reacts accordingly.
                if (news.getTitles().isEmpty()) {
                    showError(mErrorMessage, mRetryButton, true);
                    mRetryButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                news.fetchNews(); // TODO: ?
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (!news.getTitles().isEmpty()) {
                                showError(mErrorMessage, mRetryButton, false);
                                populateListView(mListView, news);
                                initializeNewsBigFragment(news);
                            }
                        }
                    });
                } else {
                    showError(mErrorMessage, mRetryButton, false);
                    populateListView(mListView, news);
                    initializeNewsBigFragment(news);
                }
            }
        }

        // Also includes the retry button.
        private void showError(TextView errorMessage, Button retryButton, boolean b) {
            if (b) {
                errorMessage.setVisibility(View.VISIBLE);
                retryButton.setVisibility(View.VISIBLE);
            } else {
                errorMessage.setVisibility(View.INVISIBLE);
                retryButton.setVisibility(View.INVISIBLE);
            }
        }

        private void populateListView(ListView listView, News news) {
            ArrayList<NewsObject> newsObject = new ArrayList<>();

            for (int i = 0; i < news.getTitles().size(); i++) {
                newsObject.add(new NewsObject(news.getTitles().get(i), news.getDescriptions().get(i), news.getDates().get(i)));
            }

            NewsAdapter newsAdapter = new NewsAdapter(getActivity(), newsObject);
            listView.setAdapter(newsAdapter);
        }

        private void initializeNewsBigFragment(final News news) {
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", news.getURLs().get(position));

                    Fragment newsBigFragment = new NewsBigFragment();
                    newsBigFragment.setArguments(bundle);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, newsBigFragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }
}

package com.gw2panel.android;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class NewsBigFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static NewsBigFragment newInstance(int sectionNumber) {
        NewsBigFragment fragment = new NewsBigFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public NewsBigFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_big, container, false);

        final TextView titleTextView = (TextView) rootView.findViewById(R.id.news_big_textview_title);
        final TextView attributionTextView = (TextView) rootView.findViewById(R.id.news_big_textview_attribution);
        final TextView postTextView = (TextView) rootView.findViewById(R.id.news_big_textview_post);
        final WebView webView = (WebView) rootView.findViewById(R.id.webView);
        final TextView errorMessage = (TextView) rootView.findViewById(R.id.news_big_textview_error);
        final Button retryButton = (Button) rootView.findViewById(R.id.news_big_button_retry);

        webView.getSettings().setJavaScriptEnabled(true);

        Bundle bundle = getArguments();
        final String url = bundle.getString("url");

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task(titleTextView, attributionTextView, postTextView, url, webView, errorMessage, retryButton).execute();
            }
        });
        new Task(titleTextView, attributionTextView, postTextView, url, webView, errorMessage, retryButton).execute();

        return rootView;
    }

    private class Task extends AsyncTask<Void, Void, String> {

        private ProgressDialog dialog;

        private TextView mTitleTextView;
        private TextView mAttributionTextView;
        private TextView mPostTextView;
        private WebView mWebView;
        private TextView mErrorMessage;
        private Button mRetryButton;

        private String url;
        private String title;
        private String attribution;
        private String post;

        public Task(TextView titleTextView, TextView attributionTextView, TextView postTextView, String u, WebView webView, TextView errorMessage, Button retryButton) {
            this.mTitleTextView = titleTextView;
            this.mAttributionTextView = attributionTextView;
            this.mPostTextView = postTextView;
            this.mWebView = webView;
            this.mErrorMessage = errorMessage;
            this.mRetryButton = retryButton;

            url = u;

            dialog = new ProgressDialog(getActivity());
        }

        @Override
        protected String doInBackground(Void... params) {
            Document source;
            try {
                source = Jsoup.connect(url).get();

                Element titleElement = source.getElementsByClass("blog-title").first();
                title = titleElement.text();

                Element attributionElement = source.getElementsByClass("blog-attribution").first();
                attribution = attributionElement.text();

                Element postElement = source.getElementsByClass("text").first();
                postElement.select("object").remove(); // Basically removes flash applications.
                post = postElement.text();

                return postElement.html();
            }
            catch (IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showError(mErrorMessage, mRetryButton, true);
                    }
                });
            }
            return null;
        }

        protected void onPreExecute() {
            dialog.setMessage("Retrieving content...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if (post != null) {
                result = "<html><head><style>img, iframe { max-width: 100%; width: auto; height: auto; } </style></head><body>" + result + "</body></html>";
                showError(mErrorMessage, mRetryButton, false);
                mTitleTextView.setText(title);
                mAttributionTextView.setText(attribution);
                mPostTextView.setText(post);
                mWebView.loadDataWithBaseURL("http:///android_asset/", result, "text/html", "UTF-8", "");
            }
        }

        private void showError(TextView errorMessage, Button retryButton, boolean b) {
            if (b) {
                errorMessage.setVisibility(View.VISIBLE);
                retryButton.setVisibility(View.VISIBLE);
            } else {
                errorMessage.setVisibility(View.INVISIBLE);
                retryButton.setVisibility(View.INVISIBLE);
            }
        }

    }

}

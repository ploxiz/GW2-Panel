package com.gw2panel.android.modules;

//  import android.os.StrictMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class News {

    private Document source;
    private List<String> titles = new ArrayList<>();
    private List<String> descriptions = new ArrayList<>();
    private List<String> dates = new ArrayList<>();
    private List<String> urls = new ArrayList<>();

    public News() {
        fetchNews();
    }

    public void connect() throws IOException {
        source = Jsoup.connect("https://www.guildwars2.com/en/news/").get();
    }

    public void fetchTitles() {
        Elements titlesBundle = source.getElementsByClass("blog-title");
        for (Element title : titlesBundle) {
            titles.add(title.text());
        }
    }

    public void fetchDescriptions() {
        Elements descriptionsBundle = source.getElementsByClass("text");
        for (Element description : descriptionsBundle) {
            descriptions.add(description.text());
        }
    }

    public void fetchDates() {
        Elements datesBundle = source.getElementsByClass("blog-attribution");
        for (Element date : datesBundle) {
            String dateString = date.text();
            int index = dateString.indexOf(" on ");
            dates.add(dateString.substring(index + 4));
        }
    }

    public void fetchURLs() {
        Elements p = source.getElementsByClass("more");
        Elements urlsBundle = p.select("a");
        for (Element url : urlsBundle) {
            urls.add(url.attr("href"));
        }
    }

    public String fetchFullPost(int id) throws IOException {
        Document postSource = Jsoup.connect(urls.get(id)).get();

        Element post = postSource.getElementsByClass("text").first();

        return post.text();
    }

    public void fetchNews() {

        /*
        // TODO: not exactly the ideal way of getting through android.os.NetworkOnMainThreadException (should find a different solution)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        */

        try {
            connect();
            fetchTitles();
            fetchDescriptions();
            fetchDates();
            fetchURLs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getTitles() {
        return titles;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public List<String> getDates() {
        return dates;
    }
}

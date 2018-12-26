package com.example.android.spaceapps.Utils;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Word {
    String query, matched_query, meaning, domain, type;
    ArrayList<String> images, related;

    public Word(String query, String matched_query, String meaning, String domain, String type, ArrayList<String> images, ArrayList<String> related){
        this.query = query;
        this.matched_query = matched_query;
        this.meaning = meaning;
        this.domain = domain;
        this.type = type;
        this.images = images;
        this.related = related;
    }

    public String getQuery() {
        return query;
    }

    public String getMatched_query() {
        return matched_query;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getDomain() {
        return domain;
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public ArrayList<String> getRelated() {
        return related;
    }
}

package com.example.android.spaceapps.SearchWord;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.android.spaceapps.Utils.Word;
import com.example.android.spaceapps.WordUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import static java.lang.System.in;

public class QueryLoader extends AsyncTask<String, Void, Word>{

    //Creating an interface to get results from onPostExeciute()
    public interface AsyncResponse {
        void processFinish(Word word);
    }

    @Override
    protected Word doInBackground(String... strings) {

        Log.v("Query Loader: -> ", "URL passed " + strings[0]);
        Log.v("Query Loader", "Query Passed " + strings[1]);
        Word word = QueryUtil.fetchMeaning(strings[0]+strings[1],"");
        Log.v("Query Loader", word.getQuery());
        return word;
    }

    @Override
    protected void onPostExecute(Word word) {
        super.onPostExecute(word);
        WordUtils.query = word.getQuery();
        WordUtils.matchedQuery = word.getMatched_query();
        WordUtils.meaning = word.getMeaning();
        WordUtils.domain = word.getDomain();
        WordUtils.type = word.getType();
        WordUtils.images = word.getImages();

        SearchWordFragement ob = new SearchWordFragement();
        Log.v("QueryLoaderPostExecute", WordUtils.query);
        ob.updateUI();

    }
}

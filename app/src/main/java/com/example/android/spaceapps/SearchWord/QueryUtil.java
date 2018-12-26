package com.example.android.spaceapps.SearchWord;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.example.android.spaceapps.Utils.Word;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public final class QueryUtil {

    private QueryUtil() {
    }

    public static Word createWord(String wordJSON, String query2) {

        Word w = null;
        if(TextUtils.isEmpty(wordJSON))
            return null;

        try {
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject baseJsonResponse = new JSONObject(wordJSON);
            JSONObject wordQuery = baseJsonResponse.getJSONObject("message");
            String query = wordQuery.getString("query");
            String matchedQuery = wordQuery.getString("matched_query");
            String meaning = wordQuery.getString("meaning");
            String domain = wordQuery.getString("domain");
            String type = wordQuery.getString("type");

            ArrayList<String> imageLists = new ArrayList<>();
            JSONArray image = wordQuery.getJSONArray("images");
            for(int i = 0; i < image.length(); i++)
                imageLists.add(image.getString(i));

            ArrayList<String> relatedLists = new ArrayList<>();
            JSONArray related = wordQuery.getJSONArray("related");
            for(int i = 0; i < related.length(); i++)
                relatedLists.add(related.getString(i));

            w = new Word(query, matchedQuery, meaning, domain, type, imageLists, relatedLists);

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        Log.v("Query Util", w.getQuery());
        return w;
    }

    private static URL createUrl(String stringUrl){
        URL url = null;
        try{
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest (URL url)throws IOException {
        String jsonResponse = "";
        if(url == null)
            return jsonResponse;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode()==200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }

            else
                Log.e("Query Util","Error Response Code " +urlConnection.getResponseCode() );
        }
        catch (IOException e){
            Log.e("Query Util","Problem retrieving json results: ",e);
        }

        finally{
            if(urlConnection != null)
                urlConnection.disconnect();
            if(inputStream != null)
                inputStream.close();
        }

        Log.v("QueryUtil", "Fetched -> " + jsonResponse);
        return jsonResponse;

    }

    @NonNull
    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static Word fetchMeaning(String requestUrl, String query) {

        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("Query Util", "Problem making the HTTP request.", e);
        }

        ArrayList<String> list = new ArrayList<>();
        list.add("");

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        Word word = createWord(jsonResponse, query);
        return word;
    }

}

package com.example.android.spaceapps.CreatePost;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class PostWordAsync extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {

        String data = "";

        Log.v("Post Word Async", params[0]);
        Log.v("Post Word Async", params[1]);

        HttpURLConnection httpURLConnection = null;
        try {

            httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoOutput(true);

            HashMap<String, String> hm= new HashMap<>();
            hm.put("command", "value");
            hm.put("wordid","23");
            hm.put("userid", "23");
            hm.put("title", "jfcjyasvkc");
            hm.put("story", "khl");
            hm.put("image", "nkuhiohoi");
            hm.put("link", "nkuholi");
            data = hm.toString();

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes("PostData=" + data);
            wr.flush();
            wr.close();

            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);

//            HashMap<String, String> hm= new HashMap<>();
//            hm.put("command", "value");
//            hm.put("wordid","23");
//            hm.put("userid", "23");
//            hm.put("title", "jfcjyasvkc");
//            hm.put("story", "khl");
//            hm.put("image", "nkuhiohoi");
//            hm.put("link", "nkuholi");
//            data = hm.toString();

            int inputStreamData = inputStreamReader.read();
            while (inputStreamData != -1) {
                //char current = (char) inputStreamData;
                //inputStreamData = inputStreamReader.read();
                //data += current;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.e("TAG", result);
    }
}

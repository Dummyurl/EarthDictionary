package com.example.android.spaceapps.SearchWord;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.android.spaceapps.CreatePost.PostBlog;
import com.example.android.spaceapps.CreatePost.PostWord;
import com.example.android.spaceapps.R;
import com.example.android.spaceapps.ShowUserHistory.HistoryFragment;
import com.example.android.spaceapps.Utils.Word;
import com.example.android.spaceapps.WordUtils;

public class SearchWordFragement extends Fragment implements QueryLoader.AsyncResponse {

    String query;
    TextView txtWordTitle, txtWordMeaning;
    Button btnShare;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        final EditText searchEditText = view.findViewById(R.id.word_edit_text);
        Button searchButton = view.findViewById(R.id.btn_search);
        txtWordTitle = view.findViewById(R.id.txt_search_word_name);
        txtWordMeaning = view.findViewById(R.id.txt_word_description);
        btnShare = view.findViewById(R.id.btn_share);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = searchEditText.getText().toString();
                new QueryLoader().execute("https://banch.ooo/api.php?command=fetchWord&word=", query);
                Log.v("SearchWordQuery", WordUtils.query);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.FragmentManager fragmentManager = getFragmentManager();
                android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PostBlog newFragment = new PostBlog();
                fragmentTransaction = fragmentTransaction.replace(R.id.fragment_container, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        WordUtils.view = view;
        return view;
    }

    @Override
    public void processFinish(Word word) {
        Log.v("Value onPostExecute", word.getQuery());
    }

    public void updateUI(){

        View view = WordUtils.view;

        TextView txtWord = view.findViewById(R.id.txt_search_word_name);
        txtWord.setText(WordUtils.query);
        TextView txtMeaning = view.findViewById(R.id.txt_word_description);
        txtMeaning.setText(WordUtils.meaning);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

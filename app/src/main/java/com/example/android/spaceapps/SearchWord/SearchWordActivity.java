package com.example.android.spaceapps.SearchWord;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.spaceapps.CreatePost.PostBlog;
import com.example.android.spaceapps.R;
import com.example.android.spaceapps.WordUtils;

public class SearchWordActivity extends Activity {

    String query;
    TextView txtWordTitle, txtWordMeaning;
    Button btnShare;

    @Nullable
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        setContentView(R.layout.fragment_search);

        final EditText searchEditText = findViewById(R.id.word_edit_text);
        Button searchButton = findViewById(R.id.btn_search);
        txtWordTitle = findViewById(R.id.txt_search_word_name);
        txtWordMeaning = findViewById(R.id.txt_word_description);
        btnShare = findViewById(R.id.btn_share);

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

            }
        });

        return super.onCreateView(name, context, attrs);
    }

    public void updateUI(){
        View view = WordUtils.view;
        //TextView txtWord = view.findViewById(R.id.txt_search_word_name);
        txtWordTitle.setText(WordUtils.query);
        TextView txtMeaning = view.findViewById(R.id.txt_word_description);
        txtMeaning.setText(WordUtils.meaning);

    }
}

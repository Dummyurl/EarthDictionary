package com.example.android.spaceapps.CreatePost;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.spaceapps.R;
import com.example.android.spaceapps.Utils.Word;
import com.example.android.spaceapps.WordUtils;

public class PostWord extends Fragment {

    EditText txtAbout, txtUrl, txtReferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_post_blog, container, false);
        Log.v("Postword", "OnCreate");

        TextView txtword = view.findViewById(R.id.txtPostWord);
        txtword.setText(WordUtils.query);

        txtAbout = view.findViewById(R.id.txt_write_about);
        txtUrl = view.findViewById(R.id.txt_image_url);
        txtReferences = view.findViewById(R.id.txt_references);

        Button btnPost = view.findViewById(R.id.btn_post);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String about = txtAbout.getText().toString();
                String imgUrl = txtUrl.getText().toString();
                String references = txtReferences.getText().toString();

                Log.v("PostWord", about);
                new PostWordAsync().execute("https://banch.ooo/api.php?command=addShare&wordid=15&userid=1&title=HelloWorld&story=Hey&image=&link=", about);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("Postword", "OnActivityCreate");
    }
}

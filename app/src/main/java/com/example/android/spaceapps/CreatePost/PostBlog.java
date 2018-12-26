package com.example.android.spaceapps.CreatePost;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.spaceapps.R;
import com.example.android.spaceapps.SearchWord.SearchWordFragement;

public class PostBlog extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_post_blog, container, false);
        return view;
    }
}

package com.example.android.spaceapps.SearchWord;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.spaceapps.R;

import java.util.ArrayList;

public class SeeAlsoAdapter extends ArrayAdapter<SeeAlso> {

    Context context;
    ArrayList<SeeAlso> seeAlsos;

    public SeeAlsoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SeeAlso> seeAlsoObjects) {
        super(context, resource, seeAlsoObjects);
        this.context = context;
        this.seeAlsos = seeAlsoObjects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item_see_also,parent,false);
            SeeAlso seeAlso = seeAlsos.get(position);

            TextView txtSeeAlsoLabel = listItem.findViewById(R.id.list_see_also_link_label);
            TextView txtSeeAlsoLink = listItem.findViewById(R.id.list_see_also_link);

            txtSeeAlsoLabel.setText(seeAlso.getLabel());
            txtSeeAlsoLink.setText(seeAlso.getLabelLink());
        }
        return listItem;
    }
}

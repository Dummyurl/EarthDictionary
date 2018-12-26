package com.example.android.spaceapps.SearchWord;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.spaceapps.R;

import java.util.List;

public class QueryImageAdapter extends ArrayAdapter<Bitmap> {

    Context context;
    List<Bitmap> queryImages;

    public QueryImageAdapter(@NonNull Context context, int resource, @NonNull List<Bitmap> queryImages) {
        super(context, resource, queryImages);
        this.context = context;
        this.queryImages = queryImages;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item_query_bitmap,parent,false);
            Bitmap bitmap = queryImages.get(position);
            ImageView img = listItem.findViewById(R.id.list_bitmap_query);
            img.setImageBitmap(bitmap);
        }
        return listItem;
    }
}

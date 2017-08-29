package com.umutjan.picturelistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by umutj_000 on 2017-08-27.
 */

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    //private ArrayList<String> list;

    public CustomAdapter(Context mContext) {

        this.mContext = mContext;
        //LayoutInflater inflater = LayoutInflater.from(mContext);
        //list = FullLogActivity.productList;

    }

    @Override
    public int getCount() {
        return MainActivity.textList.size();
    }

    @Override
    public Object getItem(int i) {
        return MainActivity.textList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        view = View.inflate(mContext, R.layout.customlayout, null);
        TextView textView = (TextView)view.findViewById(R.id.textView);
        RatingBar ratingBar = (RatingBar)view.findViewById(R.id.ratingBar);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);


        //textView.setText(MainActivity.textList.get(i));
        //textView.setText("Lmaoo");
        //ratingBar.setRating(5);

        /*
        if (MainActivity.imageViewArrayList.isEmpty()) {

            imageView.setImageResource(android.R.drawable.ic_dialog_alert);

        } else {
            ;
        }
        */
        /*
        if (MainActivity.imageDrawableList.isEmpty()) {
            imageView.setImageResource(android.R.drawable.ic_dialog_alert);
        } else {
            imageView.setImageDrawable(MainActivity.imageDrawableList.get(i));
        }

        logMessage("We've atleast came here");
        */
        textView.setText(MainActivity.textList.get(i));
        ratingBar.setRating(MainActivity.ratingList.get(i));

        byte[] imageArray = MainActivity.imageList.get(i);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageArray, 0, imageArray.length);
        imageView.setImageBitmap(bitmap);

        return view;
    }

    public void logMessage(String message) {

        Log.i("report", message);

    }
}

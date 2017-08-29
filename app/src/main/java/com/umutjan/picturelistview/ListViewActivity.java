package com.umutjan.picturelistview;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {



    //static ImageView[] imageView;

    ListView listView;

    DataBaseHelper mDataBaseHelper;

    //static ArrayList<String> name;

    //static ArrayList<Integer> rating;

    //static ArrayList<byte[]> image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        setTitle("Log");

        listView = (ListView) findViewById(R.id.listView);

        mDataBaseHelper = new DataBaseHelper(this);

        populateListView();


    }

    public void populateListView() {

        MainActivity.textList = new ArrayList<>();

        MainActivity.ratingList = new ArrayList<>();

        MainActivity.imageList = new ArrayList<>();

        Cursor data = mDataBaseHelper.getData();



        while (data.moveToNext()) {
            //name.add(data.getString(1));
            //rating.add(data.getInt(2));
            //image.add(data.getBlob(3));
            MainActivity.textList.add(data.getString(1));
            MainActivity.ratingList.add(data.getInt(2));
            MainActivity.imageList.add(data.getBlob(3));
        }

        //toastMessage("Now the length of the text arraylist is " + MainActivity.textList.size());

        CustomAdapter adapter = new CustomAdapter(this);

        listView.setAdapter(adapter);

        //logMessage("We've atleast came here");
    }

    public void logMessage(String message) {

        Log.i("report", message);

    }

    public void toastMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }
}

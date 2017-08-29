package com.umutjan.picturelistview;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editText;

    private static final int CAMERA_REQUEST = 1888;

    static ArrayList<String> textList;

    static ArrayList<Integer> ratingList;

    static ArrayList<byte[]> imageList;

    //static ArrayList<ImageView> imageViewArrayList;

    //static ArrayList<Drawable> imageDrawableList;

    RatingBar ratingBar;

    DataBaseHelper mDataBaseHelper;

    Button btnDelete, btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCamera = (Button) findViewById(R.id.btnCamera);
        Button btnView = (Button) findViewById(R.id.btnView);
        Button btnEnter = (Button) findViewById(R.id.btnEnter);

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnInfo = (Button) findViewById(R.id.btninfo);

        textList = new ArrayList<>();

        ratingList = new ArrayList<>();

        imageList = new ArrayList<>();

        //imageViewArrayList = new ArrayList<>();

        //imageDrawableList = new ArrayList<>();

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        editText = (EditText) findViewById(R.id.editText);

        imageView = (ImageView) findViewById(R.id.imageView);

        mDataBaseHelper = new DataBaseHelper(this);

        logMessage("The name of the table is " + mDataBaseHelper.getTableName());
        logMessage("The number of colums the table has is " + mDataBaseHelper.getDataBaseCount());

        String imageResource = imageView.getResources().getResourceName(R.drawable.insert_photo);
        logMessage("The imageResource is " + imageResource);
        logMessage("HAAAAAAAAAAAAAHHHHHHHHHHHHAAAAAAAAA");

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToView = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(goToView);
            }
        });

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEntry = editText.getText().toString();
                int rating = (int) ratingBar.getRating();
                byte[] image = imageViewToByte(imageView);
                //Drawable drawable = R.drawable.insert_photo;


                /*
                try {

                }

                catch(Exception e) {
                    e.printStackTrace();
                }
                */

                if (editText.length() == 0 && rating == 0) {
                    toastMessage("Please enter a name and rating");
                }
                else if (editText.length() > 0 && rating == 0) {
                    toastMessage("Please enter a rating");
                }
                else if (editText.length() == 0 && rating > 0) {
                    toastMessage("Please enter a name");
                }
                else {
                    logMessage("We're at the add stage");
                    addItem(newEntry, rating, image);
                    editText.setText("");
                    ratingBar.setRating(0);
                    imageView.setImageResource(R.drawable.insert_photo);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //logMessage("We're at least here");
                deleteDataBase();

                toastMessage("All items deleted");
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = mDataBaseHelper.getDataBaseCount();
                toastMessage("the number of columns right now are " + count);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED) {
            if (requestCode == CAMERA_REQUEST) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);
            }
        }

        //imageViewArrayList.add(imageView);
        /*
        try {
            imageView.setImageBitmap(bitmap);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        */
        //Drawable drawable = imageView.getDrawable();
        //imageDrawableList.add(drawable);


    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void addItem(String name, int rating, byte[] image) {
        boolean insertItem;

        insertItem = mDataBaseHelper.addData(name, rating, image);

        if (insertItem) {
            toastMessage("Item inserted successfully");
        } else {
            toastMessage("Something went wrong");
        }
    }

    public void deleteDataBase() {

        mDataBaseHelper.deleteAll();
        textList.clear();
        ratingList.clear();
        imageList.clear();
        logMessage("We're at least here");
    }

    public void toastMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    public void logMessage(String message) {

        Log.i("report", message);

    }
}

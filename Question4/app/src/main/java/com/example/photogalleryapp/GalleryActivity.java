package com.example.photogalleryapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<File> images = new ArrayList<>();
    ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        gridView = findViewById(R.id.gridView);

        loadImages();

        imageAdapter = new ImageAdapter(this, images);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(GalleryActivity.this, ImageDetailActivity.class);
            intent.putExtra("imagePath", images.get(position).getAbsolutePath());
            startActivity(intent);
        });
    }

    private void loadImages() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png")) {
                    images.add(file);
                }
            }
        }
    }
}
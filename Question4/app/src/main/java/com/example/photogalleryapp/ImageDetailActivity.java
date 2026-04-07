package com.example.photogalleryapp;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvName, tvPath, tvSize, tvDate;
    Button btnDelete;
    File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        imageView = findViewById(R.id.imageViewDetail);
        tvName = findViewById(R.id.tvImageName);
        tvPath = findViewById(R.id.tvImagePath);
        tvSize = findViewById(R.id.tvImageSize);
        tvDate = findViewById(R.id.tvImageDate);
        btnDelete = findViewById(R.id.btnDeleteImage);

        String path = getIntent().getStringExtra("imagePath");
        imageFile = new File(path);

        Bitmap bitmap = BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bitmap);

        tvName.setText("Name: " + imageFile.getName());
        tvPath.setText("Path: " + imageFile.getAbsolutePath());
        tvSize.setText("Size: " + imageFile.length() / 1024 + " KB");
        tvDate.setText("Date Taken: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(imageFile.lastModified())));

        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Image")
                    .setMessage("Are you sure you want to delete this image?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        if (imageFile.delete()) {
                            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(this, "Failed to delete", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}
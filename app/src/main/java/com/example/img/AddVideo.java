package com.example.img;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

public class AddVideo extends AppCompatActivity {
    private int CAMERA_CAPTURE;
    Button btnVd;
    VideoView VV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video);

        btnVd = findViewById(R.id.btnVd);
        VV = findViewById(R.id.video);
        btnVd.setOnClickListener(view -> {
            try{
                Intent captureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(captureIntent, CAMERA_CAPTURE);
            }
            catch (ActivityNotFoundException cant){
                String error = "Camera isn't available for ur device";
                Toast toast = Toast.makeText(this, error, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        VV.setVideoURI(uri);
        VV.start();
    }
}
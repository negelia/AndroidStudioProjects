package com.example.img;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private int CAMERA_CAPTURE;
    Button btnPh;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPh = findViewById(R.id.btnPh);
        img = findViewById(R.id.image);
        btnPh.setOnClickListener(view -> {
            try{
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
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
        Bundle extras = data.getExtras();
        Bitmap thumbnailBitmap = (Bitmap) extras.get("data");
        img.setImageBitmap(thumbnailBitmap);
    }

    public void submitButtonClick(View view) {
        Intent intent = new Intent(this, AddVideo.class);
        startActivity(intent);
    }
}
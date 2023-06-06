package com.example.saka;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private StorageReference mStorageRef;
    ImageView imageView;
    CameraManager cameraManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        cameraManager = new CameraManager(this);

        Button takePhotoButton = findViewById(R.id.button_camera);
        imageView = findViewById(R.id.imageView);

        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = cameraManager.getTakePictureIntent();
        if (takePictureIntent != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            String currentPhotoPath = cameraManager.getCurrentPhotoPath();
            Uri file = Uri.fromFile(new File(currentPhotoPath));
            StorageReference imageRef = mStorageRef.child("images/" + UUID.randomUUID().toString());
            UploadTask uploadTask = imageRef.putFile(file);
            // 이후의 업로드 및 데이터베이스 관련 처리 코드를 추가하십시오.
        }
    }
}

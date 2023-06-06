package com.example.saka;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // MainActivity에서 전달된 데이터 가져오기
        String disposalDay = getIntent().getStringExtra("disposalDay");
        String disposalMethod = getIntent().getStringExtra("disposalMethod");
        String type = getIntent().getStringExtra("type");

        // 데이터를 해당하는 TextView에 설정
        TextView disposalDayTextView = findViewById(R.id.tv_disposalDay);
        disposalDayTextView.setText(disposalDay);

        TextView disposalMethodTextView = findViewById(R.id.tv_disposalMethod);
        disposalMethodTextView.setText(disposalMethod);

        TextView typeTextView = findViewById(R.id.tv_type);
        typeTextView.setText(type);

        // Picasso 라이브러리를 사용하여 사진을 ImageView에 로드
        ImageView photoImageView = findViewById(R.id.iv_photo);
        String photoUrl = getIntent().getStringExtra("photoUrl");
        Picasso.get().load(photoUrl).into(photoImageView);
    }
}

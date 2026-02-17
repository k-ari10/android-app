package com.example.kuma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShousaiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shousai); // レイアウトXMLが activity_shousai.xml であること

        // TextView の初期化
        TextView spotNameTextView = findViewById(R.id.spot_name);
        TextView spotDetailTextView = findViewById(R.id.spot_detail);

        // ResultActivity からのデータ受け取り
        if (getIntent().getExtras() != null) {
            String spotName = getIntent().getStringExtra("spotName");
            String spotDetail = getIntent().getStringExtra("spotDetail");

            spotNameTextView.setText(spotName != null ? "スポットの名前: " + spotName : "スポット名なし");
            spotDetailTextView.setText(spotDetail != null ? "詳細: " + spotDetail : "詳細情報なし");
        } else {
            spotNameTextView.setText("スポット名なし");
            spotDetailTextView.setText("詳細情報なし");
        }

        // 「戻る」ボタンの処理
        Button backButton = findViewById(R.id.button_back);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // 「決定」ボタンの処理（KumaActivity に遷移）
        Button decideButton = findViewById(R.id.button_decide);
        if (decideButton != null) {
            decideButton.setOnClickListener(v -> {
                Intent intent = new Intent(ShousaiActivity.this, KumaActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
}

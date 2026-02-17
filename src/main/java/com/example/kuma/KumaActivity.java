package com.example.kuma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class KumaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuma); // レイアウトXMLは activity_kuma.xml

        Button retryButton = findViewById(R.id.retryButton);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // もう一度探すボタン押下で最初の検索画面（例: SearchActivity）に戻る
                Intent intent = new Intent(KumaActivity.this, SearchActivity.class);
                startActivity(intent);
                finish(); // 現在の画面は閉じる
            }
        });
    }
}

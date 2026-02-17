package com.example.kuma;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ImageView spotImageView;
    private TextView imageStatusText;
    private Map<String, String> mPlaceResult; // 検索結果を保持するフィールド

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        dbHelper = new DatabaseHelper(this);

        TextView selectedConditionsText = findViewById(R.id.selectedConditionsText);
        TextView spotNameText = findViewById(R.id.spotNameText);
        imageStatusText = findViewById(R.id.imageStatus);
        spotImageView = findViewById(R.id.spotImage);

        // 画像クリックで詳細画面に遷移
        spotImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlaceResult != null) {
                    String spotName = mPlaceResult.get("Place_names");
                    Intent intent = new Intent(ResultActivity.this, ShousaiActivity.class);
                    intent.putExtra("spotName", spotName);
                    startActivity(intent);
                } else {
                    Toast.makeText(ResultActivity.this, "表示する詳細情報がありません。", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 詳細表示ボタンのリスナー設定
        Button viewDetailButton = findViewById(R.id.button_view_detail);
        if (viewDetailButton != null) {
            viewDetailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPlaceResult != null) {
                        String spotName = mPlaceResult.get("Place_names");
                        Intent intent = new Intent(ResultActivity.this, ShousaiActivity.class);
                        intent.putExtra("spotName", spotName);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ResultActivity.this, "表示する詳細情報がありません。", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int namesId = extras.getInt("namesId", 0);
            int weatherId = extras.getInt("weatherId", 0);
            int seasonId = extras.getInt("seasonId", 0);

            String selectedAreaText = extras.getString("selectedAreaText", "未選択");
            String selectedWeatherText = extras.getString("selectedWeatherText", "未選択");
            String selectedSeasonText = extras.getString("selectedSeasonText", "未選択");

            selectedConditionsText.setText("選択された条件:\n地域: " + selectedAreaText +
                    "\n天気: " + selectedWeatherText +
                    "\n季節: " + selectedSeasonText);

            // データベース検索を実行し、結果をフィールド mPlaceResult に保存
            mPlaceResult = searchPlaceInDatabase(namesId, seasonId, weatherId);

            if (mPlaceResult != null) {
                String placeName = mPlaceResult.get("Place_names");
                String imageName = mPlaceResult.get("image_name");

                spotNameText.setText("おすすめスポット: " + placeName);

                if (imageName != null && !imageName.isEmpty()) {
                    int imageResId = getResources().getIdentifier(
                            imageName, "drawable", getPackageName());

                    Log.d("ImageLoad", "Attempting to load image: " + imageName + ", Resource ID: " + imageResId);

                    if (imageResId != 0) {
                        spotImageView.setImageResource(imageResId);
                        spotImageView.setVisibility(View.VISIBLE);
                        imageStatusText.setText(""); // ここで「画像を表示中」を消す
                    } else {
                        spotImageView.setVisibility(View.GONE);
                        imageStatusText.setText("画像ファイルが見つかりません: " + imageName + " (drawableフォルダを確認)");
                        Toast.makeText(this, "画像ファイル「" + imageName + "」が見つかりません。", Toast.LENGTH_LONG).show();
                    }
                } else {
                    spotImageView.setVisibility(View.GONE);
                    imageStatusText.setText("画像情報なし");
                    Toast.makeText(this, "データベースに画像名が登録されていません。", Toast.LENGTH_SHORT).show();
                }

            } else {
                spotNameText.setText("条件に合うスポットは見つかりませんでした。\n別の組み合わせをお試しください。");
                spotImageView.setVisibility(View.GONE);
                imageStatusText.setText("スポットが見つかりません");
            }
        } else {
            selectedConditionsText.setText("条件が渡されませんでした。");
            spotNameText.setText("スポット情報なし。");
            spotImageView.setVisibility(View.GONE);
            imageStatusText.setText("条件が渡されませんでした");
        }
    }

    private Map<String, String> searchPlaceInDatabase(int namesId, int seasonId, int weatherId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        Map<String, String> result = null;

        try {
            String selection = "names_id = ? AND season_id = ? AND weather_id = ?";
            String[] selectionArgs = {
                    String.valueOf(namesId),
                    String.valueOf(seasonId),
                    String.valueOf(weatherId)
            };

            cursor = db.query(
                    "cooktailmemos",
                    new String[]{"Place_names", "image_name"},
                    selection,
                    selectionArgs,
                    null, null, null
            );

            if (cursor != null && cursor.moveToFirst()) {
                result = new HashMap<>();
                result.put("Place_names", cursor.getString(cursor.getColumnIndexOrThrow("Place_names")));
                result.put("image_name", cursor.getString(cursor.getColumnIndexOrThrow("image_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "データベースエラー: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

}

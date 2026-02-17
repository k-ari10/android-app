package com.example.kuma;

import android.content.Intent; // 画面遷移のためのIntentクラスをインポート
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast; // ユーザーへのメッセージ表示用

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RadioGroup areaGroup;
    private RadioGroup weatherGroup;
    private RadioGroup seasonGroup;
    // private TextView resultText; // 結果表示はResultActivityで行うため、このアクティビティでは不要になることが多い

    // ラジオボタンのテキストとデータベースIDのマッピングを定義
    // Mapを使って、テキスト（"東部"など）から対応するID（1, 2, 3など）を取得します。
    private Map<String, Integer> areaMap;
    private Map<String, Integer> weatherMap;
    private Map<String, Integer> seasonMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // エッジトゥエッジ表示を有効化
        EdgeToEdge.enable(this);
        // activity_main.xml レイアウトをこのアクティビティに設定
        setContentView(R.layout.activity_search);

        // システムバー（ステータスバーやナビゲーションバー）のインセットを処理
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.weatherRain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // XMLレイアウトからUI要素（RadioGroupとButton）を取得し、変数に割り当て
        areaGroup = findViewById(R.id.areaGroup);
        weatherGroup = findViewById(R.id.weatherGroup);
        seasonGroup = findViewById(R.id.seasonGroup);
        // resultText = findViewById(R.id.resultText); // 結果表示は別Activityで行うため、ここでは使わない
        Button searchButton = findViewById(R.id.searchButton);

        // テキストとデータベースIDのマッピングを初期化
        initMaps(); // 後述のメソッドで定義

        // 「探す」ボタンがクリックされた時の処理を設定
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 各RadioGroupで現在選択されているRadioButtonのID（R.id.areaEast など）を取得
                int selectedAreaRadioId = areaGroup.getCheckedRadioButtonId();
                int selectedWeatherRadioId = weatherGroup.getCheckedRadioButtonId();
                int selectedSeasonRadioId = seasonGroup.getCheckedRadioButtonId();

                // 選択されたRadioButtonの表示テキストを取得（"東部", "晴れ"など）
                String selectedAreaText = getRadioButtonText(selectedAreaRadioId);
                String selectedWeatherText = getRadioButtonText(selectedWeatherRadioId);
                String selectedSeasonText = getRadioButtonText(selectedSeasonRadioId);

                // 取得したテキストを、データベースに保存されている対応する数値IDに変換
                // Map.getOrDefault() を使用し、もし選択されていない（キーが見つからない）場合はデフォルト値0を返す
                // データベースのIDは1から始まるため、0は「未選択」や「無効なID」として扱える
                int namesId = areaMap.getOrDefault(selectedAreaText, 0);   // namesテーブルのID (地域)
                int weatherId = weatherMap.getOrDefault(selectedWeatherText, 0); // weatherテーブルのID (天気)
                int seasonId = seasonMap.getOrDefault(selectedSeasonText, 0); // seasonテーブルのID (季節)

                // すべての項目が選択されているか確認
                // もしどれか一つでも未選択（IDが0）であれば、ユーザーに警告メッセージを表示し、処理を中断
                if (namesId == 0 || weatherId == 0 || seasonId == 0) {
                    Toast.makeText(MainActivity.this, "すべての項目を選択してください。", Toast.LENGTH_SHORT).show();
                    return; // これ以上処理を進めない
                }

                // 新しい画面（ResultActivity）への遷移を準備
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);

                // 選択された各情報をIntentにExtrasとして追加し、ResultActivityに渡す
                // データベースIDと、元のテキストの両方を渡しておくと、ResultActivityでの表示やデバッグに便利です
                intent.putExtra("namesId", namesId); // 地域IDを渡す
                intent.putExtra("weatherId", weatherId); // 天気IDを渡す
                intent.putExtra("seasonId", seasonId);   // 季節IDを渡す

                intent.putExtra("selectedAreaText", selectedAreaText);         // 選択された地域のテキストを渡す
                intent.putExtra("selectedWeatherText", selectedWeatherText);   // 選択された天気のテキストを渡す
                intent.putExtra("selectedSeasonText", selectedSeasonText);     // 選択された季節のテキストを渡す

                // ResultActivityを開始（画面遷移を実行）
                startActivity(intent);
            }
        });
    }
    private String getRadioButtonText(int radioId) {
        if (radioId != -1) { // ラジオボタンが選択されている場合 (-1は未選択を示す)
            RadioButton radioButton = findViewById(radioId); // IDからRadioButtonオブジェクトを取得
            return radioButton.getText().toString(); // テキストを文字列として返す
        }
        return ""; // ラジオボタンが未選択の場合は空文字列を返す
    }

    /**
     * ラジオボタンのテキストとデータベースIDのマッピングを初期化するメソッド
     * このマッピングは、XMLに表示されているテキストと、データベースの関連テーブルに保存されているIDを結びつけます。
     */
    private void initMaps() {
        areaMap = new HashMap<>();
        // namesテーブルのIDと対応。
        // XMLのラジオボタンのテキストと、データベースのnamesテーブルのnameカラムの値、
        // およびnames_idカラムの値が一致するようにマッピングを設定します。
        // 例: "中部" (XMLテキスト) -> names_id=1 (データベースID)
        areaMap.put("中部", 1);
        areaMap.put("西部", 2);
        areaMap.put("東部", 3);

        weatherMap = new HashMap<>();
        // weatherテーブルのIDと対応。
        weatherMap.put("晴れ", 1);
        weatherMap.put("曇り", 2);
        weatherMap.put("雨", 3);

        seasonMap = new HashMap<>();
        // seasonテーブルのIDと対応。
        seasonMap.put("春", 1);
        seasonMap.put("夏", 2);
        seasonMap.put("秋", 3);
        seasonMap.put("冬", 4);
    }
}
package com.example.kuma;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cooktailmemb.db";
    private static final int DATABASE_VERSION = 6;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder sb1 = new StringBuilder();
        sb1.append("CREATE TABLE cooktailmemos(");

        sb1.append("Place_id INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb1.append("Place_names VARCHAR(20),");
        sb1.append("names_id INT,");
        sb1.append("season_id INT,");
        sb1.append("weather_id INT,");
        sb1.append("image_name TEXT");
        sb1.append(");");
        db.execSQL(sb1.toString());

        StringBuilder sb2 = new StringBuilder();
        sb2.append("CREATE TABLE weather(");
        sb2.append("Weather_id INTEGER PRIMARY KEY AUTOINCREMENT,"); // ★★★AUTOINCREMENTを追加★★★
        sb2.append("weather VARCHAR(20)");
        sb2.append(");");
        db.execSQL(sb2.toString());

        StringBuilder sb3 = new StringBuilder();
        sb3.append("CREATE TABLE season(");
        sb3.append("season_id INTEGER PRIMARY KEY AUTOINCREMENT,"); // ★★★AUTOINCREMENTを追加★★★
        sb3.append("season VARCHAR(20)");
        sb3.append(");");
        db.execSQL(sb3.toString());

        StringBuilder sb4 = new StringBuilder();
        sb4.append("CREATE TABLE names(");
        sb4.append("names_id INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb4.append("name VARCHAR(20)");
        sb4.append(");");
        db.execSQL(sb4.toString());


        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('日本平',1,1,1,'nihondaira_main_02');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('大浜プール',1,2,1,'oohama');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('寸又峡',1,3,1,'sumata');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('glamping&Port結',1,4,1,'glamping');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('サウナしきじ',1,1,2,'sauna');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('sauna MYSA',1,2,2,'saunamysa');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('薩埵峠',1,3,2,'sattatoge');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('大井川鉄道',1,4,2,'ooigawa');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('県立美術館',1,1,3,'kenritubizyutukan');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('スマートアクアリウム',1,2,3,'akuariumu');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('駿府匠宿',1,3,3,'takumi');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('美人湯',1,4,3,'bijinyu');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('浜松フラワーパーク',2,1,1,'hamamatsuflowar');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('竜ヶ岩洞',2,2,1,'ryuga');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('アンティークカフェロード',2,3,1,'cafe');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('弁天島の赤鳥居',2,4,1,'bentenjima');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('浜松まつり',2,1,2,'matsuri');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('フルーツパーク',2,2,2,'frurtpark');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('うなぎパイファクトリー',2,3,2,'unagi');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('掛川花鳥園',2,4,2,'kakegawa');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('磐田市香りの博物館',2,1,3,'iwata');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('エアパーク',2,2,3,'airpark');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('浜松楽器博物館',2,3,3,'gakki');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('浜名湖オルゴールミュージアム',2,4,3,'orugorl');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('河津桜',3,1,1,'img_sakura1');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('熱海の海上花火',3,2,1,'atamihanabi');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('まかいの牧場',3,3,1,'makai');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('修善寺',3,4,1,'shuzenji');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('アウトレット',3,1,2,'autoretto');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('どんぐり',3,2,2,'donguri');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('白糸の滝',3,3,2,'shiraito_titimg01');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('時之栖',3,4,2,'tokinosu');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('MOA美術館',3,1,3,'moa');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('沼津深海魚水族館',3,2,3,'numadu');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('土肥金山',3,3,3,'toi');");
        db.execSQL("INSERT INTO cooktailmemos (Place_names, names_id, season_id, weather_id, image_name) VALUES ('猫の博物館',3,4,3,'neko');");


        db.execSQL("INSERT INTO weather (weather) VALUES ('晴れ');"); // ★★★Weather_idを削除★★★
        db.execSQL("INSERT INTO weather (weather) VALUES ('曇り');");
        db.execSQL("INSERT INTO weather (weather) VALUES ('雨');");


        db.execSQL("INSERT INTO season (season) VALUES ('春');"); // ★★★season_idを削除★★★
        db.execSQL("INSERT INTO season (season) VALUES ('夏');");
        db.execSQL("INSERT INTO season (season) VALUES ('秋');");
        db.execSQL("INSERT INTO season (season) VALUES ('冬');");


        db.execSQL("INSERT INTO names (name) VALUES ('中部');"); // ★★★names_idを削除★★★
        db.execSQL("INSERT INTO names (name) VALUES ('西部');");
        db.execSQL("INSERT INTO names (name) VALUES ('東部');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS cooktailmemos");
        db.execSQL("DROP TABLE IF EXISTS weather");
        db.execSQL("DROP TABLE IF EXISTS season");
        db.execSQL("DROP TABLE IF EXISTS names");

        onCreate(db);
    }
}
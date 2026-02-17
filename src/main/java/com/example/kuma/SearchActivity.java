package com.example.kuma;

import android.os.Bundle;
import android.content.Intent;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    private RadioGroup areaGroup, weatherGroup, seasonGroup;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        areaGroup = findViewById(R.id.areaGroup);
        weatherGroup = findViewById(R.id.weatherGroup);
        seasonGroup = findViewById(R.id.seasonGroup);
        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(v -> {
            int nameId = getSelectedRadioId(areaGroup, "area");
            int weatherId = getSelectedRadioId(weatherGroup, "weather");
            int seasonId = getSelectedRadioId(seasonGroup, "season");

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("name_id", nameId);
            intent.putExtra("weather_id", weatherId);
            intent.putExtra("season_id", seasonId);
            startActivity(intent);
        });
    }

    private int getSelectedRadioId(RadioGroup group, String type) {
        int checkedId = group.getCheckedRadioButtonId();

        if (type.equals("area")) {
            if (checkedId == R.id.areaEast) return 3;
            if (checkedId == R.id.areaCenter) return 1;
            if (checkedId == R.id.areaWest) return 2;
        } else if (type.equals("weather")) {
            if (checkedId == R.id.weatherSunny) return 1;
            if (checkedId == R.id.weatherCloudy) return 2;
            if (checkedId == R.id.weatherRain) return 3;
        } else if (type.equals("season")) {
            if (checkedId == R.id.seasonSpring) return 1;
            if (checkedId == R.id.seasonSummer) return 2;
            if (checkedId == R.id.seasonAutumn) return 3;
            if (checkedId == R.id.seasonWinter) return 4;
        }

        return -1;
    }
}

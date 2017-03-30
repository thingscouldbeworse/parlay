package com.parley.parley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //opens up the settings activity page if settingsButton is clicked
        Button fontCustomize = (Button) findViewById(R.id.customize_font);

        fontCustomize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Settings.this, FontSettingsActivity.class));
            }
        });
    }
}

package com.parley.parley;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class FontSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_settings);

        //Defines the spinners and textview for the options
        final Spinner fontSizeSpinner = (Spinner) findViewById(R.id.font_size_numbers);
        final Spinner fontColorSpinner = (Spinner) findViewById(R.id.color_font_chooser);
        final Spinner fontStyleSpinner = (Spinner) findViewById(R.id.style_font_chooser);

        Button fontSave = (Button) findViewById(R.id.fontSaveButton);

        fontSave.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                // Pass values back to MainActivity
                SharedPreferences settings = getSharedPreferences("answers", MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();

                // Retrieve values from font spinners
                String fontSizeText = fontSizeSpinner.getSelectedItem().toString();
                Float fontSize = Float.parseFloat(fontSizeText);
                Integer fontColorIndex = fontColorSpinner.getSelectedItemPosition();
                String fontStyle = fontStyleSpinner.getSelectedItem().toString();

                // Send values
                editor.putFloat("fontSize", fontSize);
                editor.putInt("colorIndex", fontColorIndex);
                editor.putString("fontStyle", fontStyle);
                editor.commit();
                startActivity(new Intent(FontSettingsActivity.this, MainActivity.class));
            }
        });

    }
}

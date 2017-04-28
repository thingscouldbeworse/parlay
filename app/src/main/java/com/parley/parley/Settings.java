package com.parley.parley;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class Settings extends AppCompatActivity {
    private int textBubbleChoice;

    public Settings(){
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //opens up the settings activity page if settingsButton is clicked
        final Button fontCustomize = (Button) findViewById(R.id.customize_font);

        //Defines buttons for the Text Bubble Shape RadioGroup
        final RadioButton textBubbleCircle = (RadioButton) findViewById(R.id.circle);
        final RadioButton textBubbleHexagon = (RadioButton) findViewById(R.id.hexagon);
        final RadioButton textBubbleQuote = (RadioButton) findViewById(R.id.quote_bubble);
        final RadioButton textBubbleTriangle = (RadioButton) findViewById(R.id.triangle);
        final RadioButton textBubbleStar = (RadioButton) findViewById(R.id.star);
        final Spinner BubbleColorSpinner = (Spinner) findViewById(R.id.spinner);


        final Spinner backgroundColorSpinner = (Spinner) findViewById(R.id.background_color);

        fontCustomize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences settings = getSharedPreferences("prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("circle",textBubbleCircle.isChecked());
                editor.putBoolean("hexagon",textBubbleHexagon.isChecked());
                editor.putBoolean("quote",textBubbleQuote.isChecked());
                editor.putBoolean("triangle",textBubbleTriangle.isChecked());
                editor.putBoolean("star", textBubbleStar.isChecked());

                //retrieve text bubble color choice
                Integer BubbleColorIndex = BubbleColorSpinner.getSelectedItemPosition();
                editor.putInt("bubbleColor", BubbleColorIndex);

                Integer backgroundColorIndex = backgroundColorSpinner.getSelectedItemPosition();
                editor.putInt("backgroundColor", backgroundColorIndex);

                editor.commit();
                finish();

                startActivity(new Intent(Settings.this, FontSettingsActivity.class));
            }
        });


        final SharedPreferences settings = getSharedPreferences("prefs", MODE_PRIVATE);


        Button save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("circle",textBubbleCircle.isChecked());
                editor.putBoolean("hexagon",textBubbleHexagon.isChecked());
                editor.putBoolean("quote",textBubbleQuote.isChecked());
                editor.putBoolean("triangle",textBubbleTriangle.isChecked());
                editor.putBoolean("star", textBubbleStar.isChecked());

                Integer backgroundColorIndex = backgroundColorSpinner.getSelectedItemPosition();
                editor.putInt("backgroundColor", backgroundColorIndex);

                Integer BubbleColorIndex = BubbleColorSpinner.getSelectedItemPosition();
                editor.putInt("bubbleColor", BubbleColorIndex);
              
                editor.commit();
                finish();

                startActivity(new Intent(Settings.this, MainActivity.class));
            }
        });

    }

    public int getTextBubbleShape(){
        return textBubbleChoice;
    }
}
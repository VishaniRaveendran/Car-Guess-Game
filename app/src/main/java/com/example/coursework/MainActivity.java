package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;


public class MainActivity extends AppCompatActivity {
    Switch timer;
    boolean isTimerSwitchOn = false;
    public static final String TIMER_ON = "countdown.status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timer = findViewById(R.id.timer_switch);

        //To change the boolean value when the switch button clicked
        timer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isTimerSwitchOn = true;
                }else{
                    isTimerSwitchOn = false;
                }
            }
        });

    }

    public void launchCarMakeActivity(View view) {
        Intent carMakeIntent = new Intent(this, IdentifyCarMake.class);
        carMakeIntent.putExtra("switchValue", isTimerSwitchOn);   //To pass the boolean value to the other activity
        startActivity(carMakeIntent);
    }

    public void launchHintActivity(View view) {
        Intent hintIntent = new Intent(this, HintActivity.class);
        hintIntent.putExtra("switchValue", isTimerSwitchOn);   //To pass the boolean value to the other activity
        startActivity(hintIntent);
    }

    public void launchAdvancedLevelActivity(View view) {
        Intent advanceLevelIntent = new Intent(this, AdvancedLevelActivity.class);
        advanceLevelIntent.putExtra("switchValue", isTimerSwitchOn);   //To pass the boolean value to the other activity
        startActivity(advanceLevelIntent);
    }

    public void launchCarImageActivity(View view) {
        Intent carImageIntent = new Intent(this, IdentifyCarImage.class);
        carImageIntent.putExtra("switchValue", isTimerSwitchOn);   //To pass the boolean value to the other activity
        startActivity(carImageIntent);
    }
}
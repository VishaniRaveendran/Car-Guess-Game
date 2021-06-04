package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IdentifyCarMake extends AppCompatActivity {

    //------Declaration of brandSpinnerLabel to store the selected brand from the dropdown-----//
    private String brandSpinnerLabel;
    //------Declaration of brandname to store random brand name-----//
    private String brandName;
    //------Declaration of identify button------//
    private Button identifyButton;
    //------Declaration of resultBrandName to display whether answer is correct or wrong------//
    private TextView resultBrandName;
    //------Declaration of correctBrandName to display correct answer------//
    private TextView correctBrandName;
    //------Declaration of spinner------//
    private Spinner brand_spinner;
    //------Declaration of imageView------//
    private ImageView iv_car;
    //------Declaration of timer text------//
    private TextView timer;
    //------Declaration of CountDownTimer------//
    private CountDownTimer countDownTimer;
    private boolean isTimerSwitchOn = false;

    //--------------------Initialize brand to the List--------------------//
    public ArrayList<String> carListArray = new ArrayList<>(Arrays.asList( "alfa", "amg", "audi", "bentley", "bmw", "bugatti", "daihatsu",
                                                                            "ferrari", "ford", "genesis", "hyundai", "jaguar", "kia", "lexus",
                                                                            "lincoln", "lotus", "maserati", "mercedesbenz", "mini", "opel",
                                                                            "peugeot", "proton", "rangerover", "rollsroyce", "smart", "tata",
                                                                            "toyota", "troller", "vauxhall", "volvo"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_make);

        Intent intent = getIntent();
        //-------------To get the boolean value from main activity-------------//
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");

        //---------TextView is assign to timer using id----------//
        timer = findViewById(R.id.timer);
        //---------Image is assign to CarImage using id---------//
        iv_car = findViewById(R.id.identify_brand_image_view);
        //---------Spinner is assign to brand Dropdown using id---------//
        brand_spinner = findViewById(R.id.selected_brand_spinner);
        //---------TextView is assign to show Correct Brand using id---------//
        correctBrandName = findViewById(R.id.answerLabel);
        //---------TextView is assign to show Correct or Wrong message using id---------//
        resultBrandName = findViewById(R.id.correctAnswer);
        //---------Button is assign to identifyButton using id---------//
        identifyButton = findViewById(R.id.identify_brand_button);

        //---------Check whether switch is on or not---------//
        if (isTimerSwitchOn){
            switchTimer();
            begin();
        }else {
            begin();
        }
    }

    //----------------To handle timer--------------//
    private void switchTimer() {
        //----------------Create the countDownTimer and assigning time for 20 seconds----------------//
        countDownTimer = new CountDownTimer(21000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String seconds = millisUntilFinished / 1000 + "";
                timer.setText(seconds);
            }

            //---------------Method for when timer finish---------------//
            @Override
            public void onFinish() {
                submitAuto();
            }
        };
        //--------------------To Start the countdown--------------------//
        countDownTimer.start();
    }

    public void begin(){
        //--------------------Get random brand name from the array list--------------------//
        brandName = getABrandRandom();
        //--------------------Get image name with random image name and random Id--------------------//
        String imageName = brandName + "_" + getRandomNumber(1, 2);
        //--------------------Image is taken from the drawable file--------------------//
        iv_car.setImageDrawable( getResources().getDrawable(getBrandID(imageName, "drawable", getApplicationContext())));

        //--------------------Get the selected name from the drop down--------------------//
        brand_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //--------------------Get the drop down value--------------------//
                brandSpinnerLabel = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //--------------------ArrayAdapter with default spinner layout--------------------//
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.car_array, android.R.layout.simple_spinner_item);
        //--------------------Layout is set to get list of drop down--------------------//
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //--------------------Apply the adapter to the spinner.--------------------//
        if (brand_spinner != null) {
            brand_spinner.setAdapter(adapter);
        }

        //--------------------Setting button text to identify initially--------------------//
        identifyButton.setText("Identify");

    }

    //--------------------------Get a random brand--------------------//
    public String getABrandRandom(){
        //-----------------Get a random brand from carListArray-----------//
        return carListArray.get(getRandomNumber(0,(carListArray.size()-1)));
    }

    //--------------------------Get a random integer value from a range--------------------------//
    public static int getRandomNumber(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }

    //Reference : https://stackoverflow.com/questions/41479017/how-to-get-id-of-images-in-drawable
    //--------------------------Check if the name of the image matches with the images in drawable--------------------------//
    protected final static int getBrandID(final String imageName, final String imageType, final Context context) {
        final int brandID = context.getResources().getIdentifier(imageName, imageType, context.getApplicationInfo().packageName);
        if (brandID == 0) {
            throw new IllegalArgumentException("There is no car with that brand " + imageName);
        }else{
            return brandID;
        }
    }

    //-------------------To check the validation of the users answer by clicking the submit button-------------------//
    public void identifyButton(View view) {
        if (identifyButton.getText().equals("Identify")){
            brand_spinner.setEnabled(false);
            identifyButton.setText("Next");
            //-----------Checking whether selected brand name is equal to car image-------------//
            if (brandSpinnerLabel.equals(brandName) ){
                String answer = "CORRECT !";
                resultBrandName = findViewById(R.id.answerLabel);
                resultBrandName.setTextColor(Color.GREEN);
                resultBrandName.setText(answer);

                //---------Pause the timer when identify button is pressed---------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }

            }else {
                String answer = "WRONG !";
                resultBrandName = findViewById(R.id.answerLabel);
                resultBrandName.setTextColor(Color.RED);
                resultBrandName.setText(answer);

                correctBrandName = findViewById(R.id.correctAnswer);
                correctBrandName.setTextColor(Color.YELLOW);
                correctBrandName.setText(brandName.toUpperCase());

                //---------Pause the timer when identify button is pressed---------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }
            }
        }
        else {
            //--------------------When the button label is set to Next------------------//
            begin();
            //-------------------------Enable spinner-------------------------//
            brand_spinner.setEnabled(true);
            //-------------------------Reset timer-------------------------//
            if (isTimerSwitchOn){
                countDownTimer.cancel();
                switchTimer();
            }

            resultBrandName = findViewById(R.id.answerLabel);
            resultBrandName.setText("");

            correctBrandName = findViewById(R.id.correctAnswer);
            correctBrandName.setText("");
        }

    }

    //--------------------Auto submit when time is over-------------------//
    public void submitAuto(){
        brand_spinner.setEnabled(false);
        identifyButton.setText("Next");
        //-----------Checking whether selected brand name is equal to car image-------------//
        if (brandSpinnerLabel.equals(brandName) ){
            String answer = "CORRECT !";
            resultBrandName = findViewById(R.id.answerLabel);
            resultBrandName.setTextColor(Color.GREEN);
            resultBrandName.setText(answer);
        }else {
            String answer = "WRONG !";
            resultBrandName = findViewById(R.id.answerLabel);
            resultBrandName.setTextColor(Color.RED);
            resultBrandName.setText(answer);
            correctBrandName = findViewById(R.id.correctAnswer);
            correctBrandName.setTextColor(Color.YELLOW);
            correctBrandName.setText(brandName.toUpperCase());
        }
    }


}
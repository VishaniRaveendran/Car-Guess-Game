package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IdentifyCarImage extends AppCompatActivity {

    private String carImage1;
    private String carImage2;
    private String carImage3;
    private String selectedCarImgName;
    private TextView brandNameToGuess;
    private TextView resultBrandName;
    private ImageView imageView_1;
    private ImageView imageView_2;
    private ImageView imageView_3;
    private Integer imageCount;
    private CountDownTimer countDownTimer;
    private boolean isTimerSwitchOn = false;
    private TextView timer;
    private static boolean clickedSubmit = false;

    //----------------ArrayList to store 3 random car images--------------//
    private ArrayList<String> selectedRandomBrands;

    //------------------Initialize the brands to a array list--------------------//
    public ArrayList<String> carListArray = new ArrayList<>(Arrays.asList("alfa", "amg", "audi", "bentley", "bmw", "bugatti",
                                                                            "daihatsu", "ferrari", "ford", "genesis", "hyundai",
                                                                            "jaguar", "kia", "lexus", "lincoln", "lotus", "maserati",
                                                                            "mercedesbenz", "mini", "opel", "peugeot", "proton", "rangerover",
                                                                            "rollsroyce", "smart", "tata", "toyota", "troller", "vauxhall", "volvo"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_image);
        //---------TextView is assign to timer using id----------//
        timer = findViewById(R.id.car_timer);
        //---------TextView is assign to brandNameToGuess using id----------//
        brandNameToGuess = findViewById(R.id.brand_display_text);
        //---------TextView is assign to show Correct or Wrong Message using id---------//
        resultBrandName = findViewById(R.id.answerImageLabel);
        //---------1st Image is assign to imageView_1 using id---------//
        imageView_1 = findViewById(R.id.iv_car1);
        //---------2nd Image is assign to imageView_2 using id---------//
        imageView_2 = findViewById(R.id.iv_car2);
        //---------3rd Image is assign to imageView_3 using id---------//
        imageView_3 = findViewById(R.id.iv_car3);



        Intent intent = getIntent();
        //---------To get the boolean value from main activity---------//
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");

        //---------Check whether switch is on or not---------//
        if (isTimerSwitchOn) {
            switchTimer();
            begin();
        } else {
            begin();
        }

    }

    //----------------To handle timer--------------//
    private void switchTimer() {
        //----------------Create the countDownTimer and assigning time for 20 seconds----------------//
        countDownTimer = new CountDownTimer(21000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String seconds = millisUntilFinished / 1000 + "";
                timer.setText(seconds);
            }

            //---------------Method for when timer finish---------------//
            @Override
            public void onFinish() {
                imageCount++;
                resultBrandName = findViewById(R.id.answerImageLabel);
                String notAnswered = "NOT ANSWERED";
                resultBrandName.setTextColor(Color.GRAY);
                resultBrandName.setText(notAnswered);

                colourCorrectBrand();
            }
        };
        //------------Start the countdown----------//
        countDownTimer.start();
    }


    public void begin() {
        //-----------Initializing image count as 0 initially------------//
        imageCount = 0;

        //-----------Assigning the 3 unique car image to a array list-----------//
        selectedRandomBrands = getBrandsRandom();
        //----------select (1 out of 3) car images from the array list---------//
        selectedCarImgName = selectedRandomBrands.get(getRandomNumber(0, 2));
        //------The first element (car image) from the array list----------------//
        carImage1 = selectedRandomBrands.get(0) + "_" + getRandomNumber(1, 2);
        //----------Image is taken from the drawable file--------------//
        imageView_1.setImageDrawable(getResources().getDrawable(getBrandNo(carImage1, "drawable", getApplicationContext())));
        //------The second element (car image) from the array list----------------//
        carImage2 = selectedRandomBrands.get(1) + "_" + getRandomNumber(1, 2);
        //----------Image is taken from the drawable file--------------//
        imageView_2.setImageDrawable(getResources().getDrawable(getBrandNo(carImage2, "drawable", getApplicationContext()))); // image is taken from the drawable file
        //------The three element (car image) from the array list----------------//
        carImage3 = selectedRandomBrands.get(2) + "_" + getRandomNumber(1, 2);
        //----------Image is taken from the drawable file--------------//
        imageView_3.setImageDrawable(getResources().getDrawable(getBrandNo(carImage3, "drawable", getApplicationContext()))); // image is taken from the drawable file
        //---------Displaying the selected brand in a text view--------------//
        brandNameToGuess.setText(selectedCarImgName.toUpperCase());
    }


    public ArrayList<String> getBrandsRandom() {
        //--------Getting 3 brands from brandListArray and checking is it available in the array list and then add them to the array list------//
        ArrayList<String> carBrandsList = new ArrayList<>();


        while (carBrandsList.size() != 3) {

            //---------Pick a image--------//
            String pickedCarImage = getBrandRandom();

            //--------Check if the brand exist in the  list
            if (!carBrandsList.contains(pickedCarImage)) {
                //------------Add the unique brand to the array list--------------//
                carBrandsList.add(pickedCarImage);
            }

        }
        //-----------The list return 3 unique brands----------//
        return carBrandsList;
    }

    //Reference: https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
    //--------------------------Get a random integer value from a range--------------------------//
    public static int getRandomNumber(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;

    }

    //Reference : https://stackoverflow.com/questions/41479017/how-to-get-id-of-images-in-drawable
    //--------------------------Check if the name of the image matches with the images in drawable--------------------------//
    protected final static int getBrandNo(final String imageName, final String imageType, final Context context) {
        final int imageCar = context.getResources().getIdentifier(imageName, imageType, context.getApplicationInfo().packageName);
        if (imageCar == 0) {
            throw new IllegalArgumentException("Image is not found with a Name " + imageName);
        } else {
            return imageCar;
        }
    }

    //--------------------------Get a random brand--------------------//
    public String getBrandRandom() {
        //-----------------Get a random brand from carListArray-----------//
        return carListArray.get(getRandomNumber(0, (carListArray.size() - 1)));
    }

    //---------------OnClick function for the first image---------------//
    public void OnClickFirstImage(View view) {
        //---------------Increase the count when the user select the image-------------//
        imageCount++;
        //---------------Hold the result (is the selected item is correct or wrong)----------//
        String currentAnswer;
        //---------------Get the brand name for the image by separating the Number of the image name-----------//
        String Image1Name = carImage1.split("_")[0];
        //---------------Display the status(correct/wrong) only when the image is selected (by select which is count)-----------//
        if (imageCount == 1) {
            //-----------Check if the brand name is equal to the image name-------------//
            if (selectedCarImgName.equals(Image1Name)) {
                //-------------If above condition true set the currentAnswer to correct------------//
                currentAnswer = "CORRECT!";
                //-------------Display text in green colour--------------//
                resultBrandName.setTextColor(Color.parseColor("#008000"));
                //-----------------------display the status----------//
                resultBrandName.setText(currentAnswer.toUpperCase());
                imageView_1.setBackgroundColor(Color.parseColor("#008000"));

                //-----------------To pause the timer-----------------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }

            } else {
                //-------------If above condition false set the currentAnswer to wrong------------//
                currentAnswer = "WRONG!";
                //------------Display text in red colour-------------//
                resultBrandName.setTextColor(Color.parseColor("#FF0000"));
                //-------------Display the message whether correct or wrong---------//
                resultBrandName.setText(currentAnswer.toUpperCase());
                //-------------Setting background colour of the image to red(to show the selected status is wrong)---------//
                imageView_1.setBackgroundColor(Color.parseColor("#FF0000"));
                //-------------find the correct image and background colour is change to green-----------------/
                colourCorrectBrand();

                //--------------To pause the timer------------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }

            }
        }
    }

    // method for the Second image
    public void OnClickSecondImage(View view) {
        //---------------Hold the result (is the selected item is correct or wrong)----------//
        String currentAnswer;
        //---------------Get the brand name for the image by separating the Number of the image name-----------//
        String Image2Name = carImage2.split("_")[0];
        //---------------Increase the count when the user select the image-------------//
        imageCount++;
        //---------------Display the status(correct/wrong) only when the image is selected (by select which is count)-----------//
        if (imageCount == 1) {
            //-----------Check if the brand name is equal to the image name-------------//
            if (selectedCarImgName.equals(Image2Name)) {
                //-------------If above condition true set the currentAnswer to correct------------//
                currentAnswer = "CORRECT!";
                //-------------Display text in green colour--------------//
                resultBrandName.setTextColor(Color.parseColor("#008000"));
                //-----------------------display the status----------//
                resultBrandName.setText(currentAnswer.toUpperCase());
                imageView_2.setBackgroundColor(Color.parseColor("#008000"));

                //-----------------To pause the timer-----------------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }

            } else {
                //-------------If above condition false set the currentAnswer to wrong------------//
                currentAnswer = "WRONG!";
                //------------Display text in red colour-------------//
                resultBrandName.setTextColor(Color.parseColor("#FF0000"));
                //-------------Display the message whether correct or wrong---------//
                resultBrandName.setText(currentAnswer.toUpperCase());
                //-------------Setting background colour of the image to red(to show the selected status is wrong)---------//
                imageView_2.setBackgroundColor(Color.parseColor("#FF0000"));
                //-------------find the correct image and background colour is change to green-----------------/
                colourCorrectBrand();

                //--------------Pause Timer------------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }
            }
        }
    }




    public void OnClickThirdImage(View view) {
        //---------------Hold the result (is the selected item is correct or wrong)----------//
        String currentAnswer;
        //---------------Get the brand name for the image by separating the Number of the image name-----------//
        String Image3Name = carImage3.split("_")[0];
        //---------------Increase the count when the user select the image-------------//
        imageCount++;
        //---------------Display the status(correct/wrong) only when the image is selected (by select which is count)-----------//
        if (imageCount == 1) {
            //-----------Check if the brand name is equal to the image name-------------//
            if (selectedCarImgName.equals(Image3Name)) {
                //-------------If above condition true set the currentAnswer to correct------------//
                currentAnswer = "CORRECT!";
                //-------------Display text in green colour--------------//
                resultBrandName.setTextColor(Color.parseColor("#008000"));
                //-----------------------display the status----------//
                resultBrandName.setText(currentAnswer.toUpperCase());
                imageView_3.setBackgroundColor(Color.parseColor("#008000"));

                //-----------------To pause the timer-----------------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }

            } else {
                //-------------If above condition false set the currentAnswer to wrong------------//
                currentAnswer = "WRONG!";
                //------------Display text in red colour-------------//
                resultBrandName.setTextColor(Color.parseColor("#FF0000"));
                //-------------Display the message whether correct or wrong---------//
                resultBrandName.setText(currentAnswer.toUpperCase());
                //-------------Setting background colour of the image to red(to show the selected status is wrong)---------//
                imageView_3.setBackgroundColor(Color.parseColor("#FF0000"));
                //-------------find the correct image and background colour is change to green-----------------/
                colourCorrectBrand();

                //--------------Pause Timer------------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }
            }
        }
    }


    public void colourCorrectBrand() {
        //-------------Split the name from the image name-----------//
        String firstBrandName = carImage1.split("_")[0];
        String secondBrandName = carImage2.split("_")[0];
        String thirdBrandName = carImage3.split("_")[0];

        //--------------If the image name is found equal to Brand name the image background is coloured with green------------//
        if (selectedCarImgName.equals(firstBrandName)) {
            imageView_1.setBackgroundColor(Color.parseColor("#008000"));
        }

        if (selectedCarImgName.equals(secondBrandName)) {
            imageView_2.setBackgroundColor(Color.parseColor("#008000"));
        }

        if (selectedCarImgName.equals(thirdBrandName)) {
            imageView_3.setBackgroundColor(Color.parseColor("#008000"));
        }
    }

    //------Method for the Next is selected-------//
    public void OnClickNextButton(View view) {
        //-------------Select is already counted if the above image is selected----------//
        if (imageCount != 0) {
            //--------Next screen--------//
            begin();
            //--------To reset the timer--------//
            if(isTimerSwitchOn){
                countDownTimer.cancel();
                switchTimer();
            }
            ImageView iv1 = findViewById(R.id.iv_car1);
            ImageView iv2 = findViewById(R.id.iv_car2);
            ImageView iv3 = findViewById(R.id.iv_car3);

            //--------Initialize the status black in the next screen--------//
            resultBrandName.setText("");
            //--------Initial the background color of the images---------------//
            iv1.setBackgroundColor(Color.parseColor("#000000"));
            iv2.setBackgroundColor(Color.parseColor("#000000"));
            iv3.setBackgroundColor(Color.parseColor("#000000"));
        } else {
            Toast.makeText(this, "Click A Car Brand", Toast.LENGTH_SHORT).show();
        }
    }
}

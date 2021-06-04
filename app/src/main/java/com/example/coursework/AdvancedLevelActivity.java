package com.example.coursework;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.view.View.INVISIBLE;

public class AdvancedLevelActivity extends AppCompatActivity {
    private CountDownTimer countDownTimer;
    private boolean isTimerSwitchOn = false;
    //------Maximum image guess------//
    private final int max_guess = 3;
    private TextView feedback;
    //------Declaration of button submit------//
    private Button buttonSubmit;
    //-----Declaration of images to imageView-------//
    private ImageView carImage1, carImage2, carImage3;
    //-----Declaration of answer text to TextView-------//
    private TextView answer1, answer2, answer3;
    //----Declaration of correctWrong and score_textView to TextView
    private TextView correctWrong, score_textView;
    //----Declaration of editText---------//
    private EditText car1_editText, car2_editText, car3_editText;
    //----Declaring arrayList-------//
    private ArrayList<Integer> carList = new ArrayList<>();
    //----Declaring score----------//
    private int scoreInt = 0;
    //----Declaring attempts----------//
    private int attempts = 0;
    //----Declaring String variable----------//
    private String answerString1, answerString2, answerString3;
    //-----------Declaration of Car class---------//
    private Cars cars = new Cars();
    private boolean firstRun = true;
    private boolean scoreOpen1 = true, scoreOpen2 = true, scoreOpen3 = true;
    //------Declaration of timer------//
    private TextView timer;

    //-------Declaration of arrayList----------//
    private ArrayList<Boolean> correctnessList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);
        Intent intent = getIntent();
        //-------------To get the boolean value from main activity-------------//
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");
        //---------Check whether switch is on or not---------//

        if (isTimerSwitchOn) {
            switchTimer();
            begin();
        } else {
            begin();
        }
    }

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

//                editTextHint.setEnabled(false);
//                buttonIdentify.setText("Next");
//                displayCorrectCarName = findViewById(R.id.answerImageLabel);
//                startTimer();
                if (buttonSubmit.getText().toString().equalsIgnoreCase("submit")) {
                    buttonSubmit.callOnClick();
                }
            }
        };
        //--------------------To Start the countdown--------------------//
        countDownTimer.start();
    }

    public void begin() {
        //-----------Declaring Views by its Id----------------//
        buttonSubmit = findViewById(R.id.submitButton);

        carImage1 = findViewById(R.id.imageView_car1);
        carImage2 = findViewById(R.id.imageView_car2);
        carImage3 = findViewById(R.id.imageView_car3);

        car1_editText = findViewById(R.id.first_car);
        car2_editText = findViewById(R.id.second_car);
        car3_editText = findViewById(R.id.third_car);
        score_textView = findViewById(R.id.scoreDisplay);

        answer1 = findViewById(R.id.answer_text_1);
        answer2 = findViewById(R.id.answer_text_2);
        answer3 = findViewById(R.id.answer_text_3);

        timer = findViewById(R.id.advance_timer_text);

        correctnessList.add(false);
        correctnessList.add(false);
        correctnessList.add(false);

        correctWrong = findViewById(R.id.answerStatusLabel);

        //---------Calling setRandomCars method---------//
        setRandomCars();
        //---------Calling setAnswers method---------//
        setAnswers();


        //--------------Making text to black color from red or green---------//
        car1_editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                car1_editText.setTextColor(BLACK);
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });

        car2_editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                car2_editText.setTextColor(BLACK);
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });

        car3_editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                car3_editText.setTextColor(BLACK);
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });
    }


    public void doClickBtn() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                buttonSubmit.performClick();
            }
        });
    }

    //-------------Setting Random cars-------//
    public void setRandomCars() {
        carImage1.setImageResource(cars.getRandomCar());
        carList.add(Cars.getLastRandomIndex());
        carImage2.setImageResource(cars.getRandomCar());
        carList.add(Cars.getLastRandomIndex());
        carImage3.setImageResource(cars.getRandomCar());
        carList.add(Cars.getLastRandomIndex());
    }


    //---------Setting answer-----------//
    public void setAnswers() {
        answerString1 = cars.getBrandName(carList.get(0));
        answerString2 = cars.getBrandName(carList.get(1));
        answerString3 = cars.getBrandName(carList.get(2));
        //-----------Empty the array----------//
        carList.clear();

        //-----------Storing answers in textViews---------------//
        answer1.setText(answerString1);
        answer2.setText(answerString2);
        answer3.setText(answerString3);
        System.out.println(answerString1);
        System.out.println(answerString2);
        System.out.println(answerString3);
        System.out.println("\n\n\n\n");
        System.out.println(answer1.getText().toString());
        System.out.println(answer2.getText().toString());
        System.out.println(answer3.getText().toString());
    }

    public void checkUserAnswers(EditText editText, String answerString) {
        //------------Checking each editText whether it is correct-----------//
        if (editText.getText().toString().equalsIgnoreCase(answerString)) {
            correctnessList.add(true);
        } else
            correctnessList.add(false);
    }


    //-------------submit button------//
    public void submit(View view) {
        if (buttonSubmit.getText().toString().equals("Next")) {
            restart();
        } else {
            attempts++;
            if (!firstRun) {
                if (checkIfDone()) {
                    resultLabel();

                }
            }
            firstRun = false;

            //---------Checking user answer is correct with correct name--------//
            checkUserAnswers(car1_editText, answerString1);
            checkUserAnswers(car2_editText, answerString2);
            checkUserAnswers(car3_editText, answerString3);


            //---------change edit text colors according to answers----------//
            changeEditTextColors();
            countScore();
            setEditTextsVisible();


            //---------if attempts are finished, show answerLabel!
            if (attempts >= max_guess) {
                resultLabel();
                //--------------To pause the timer------------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }
            }
        }
    }

    //---------If editText is correct return true---------//
    public boolean checkIfDone() {
        boolean done = false;
        if ((car1_editText.getCurrentTextColor() == Color.rgb(0, 255, 0))
                && (car2_editText.getCurrentTextColor() == Color.rgb(0, 255, 0))
                && (car3_editText.getCurrentTextColor() == Color.rgb(0, 255, 0))) {
            done = true;
        }
        return done;
    }

    public void resultLabel() {
        //---------If all the editText is green, display correct else wrong-------//
        boolean correctOrWrongLabel = true;
        int i = correctnessList.size();
        if (!correctnessList.get(i - 3))
            correctOrWrongLabel = false;
        else if (!correctnessList.get(i - 2))
            correctOrWrongLabel = false;
        else if (!correctnessList.get(i - 1))
            correctOrWrongLabel = false;

        //--------Setting correct and wrong label and make it visible-----//
        if (correctOrWrongLabel) {
            correctWrong.setText("CORRECT!");
            correctWrong.setTextColor(GREEN);
        } else {
            correctWrong.setText("WRONG!");
            correctWrong.setTextColor(RED);
        }
        correctWrong.setVisibility(View.VISIBLE);

        setAnswersVisible();

        car1_editText.setEnabled(true);
        car2_editText.setEnabled(true);
        car3_editText.setEnabled(true);
        //-------Change submit button to next------------//
        buttonSubmit.setText("Next");
    }

    //-----------Resets all the values so, then calls start()------------//
    public void restart() {
        buttonSubmit.setText("Submit");
        attempts = 0;
        carList.clear();
        correctnessList.clear();
        correctWrong.setVisibility(INVISIBLE);
        setAnswersInvisible();
        car1_editText.setText("");
        car2_editText.setText("");
        car3_editText.setText("");
        firstRun = true;
        scoreOpen1 = true;
        scoreOpen2 = true;
        scoreOpen3 = true;
        //--------------To pause the timer------------//
        if (isTimerSwitchOn) {
            countDownTimer.cancel();
        }
        begin();
    }


    //-------------counting score-----------------//
    public void countScore() {

        if (scoreOpen1 && car1_editText.getCurrentTextColor() == GREEN) {
            scoreInt++;
            scoreOpen1 = false;
        }
        if (scoreOpen2 && car2_editText.getCurrentTextColor() == GREEN) {
            scoreInt++;
            scoreOpen2 = false;
        }
        if (scoreOpen3 && car3_editText.getCurrentTextColor() == GREEN) {
            scoreInt++;
            scoreOpen3 = false;
        }
        score_textView.setText("Score: "+"" + scoreInt);


    }


    public void changeEditTextColors() {
        boolean allCorrect = true;

        int size = correctnessList.size();
        if (correctnessList.get(size - 3)) {
            car1_editText.setTextColor(Color.rgb(0, 255, 0));
            car1_editText.setEnabled(false);

        } else {
            allCorrect = false;

            car1_editText.setTextColor(Color.rgb(255, 0, 0));
        }
        if (correctnessList.get(size - 2)) {
            car2_editText.setTextColor(Color.rgb(0, 255, 0));
            car2_editText.setEnabled(false);

        } else {

            allCorrect = false;
            car2_editText.setTextColor(Color.rgb(255, 0, 0));
        }
        if (correctnessList.get(size - 1)) {
            car3_editText.setTextColor(Color.rgb(0, 255, 0));
            car3_editText.setEnabled(false);

        } else {

            allCorrect = false;
            car3_editText.setTextColor(Color.rgb(255, 0, 0));
        }
        if (allCorrect) {
            resultLabel();
        }
    }


    public void setEditTextsVisible() {
        car1_editText.setVisibility(View.VISIBLE);
        car2_editText.setVisibility(View.VISIBLE);
        car3_editText.setVisibility(View.VISIBLE);
    }


    public void setAnswersVisible() {
        System.out.println("Setting answers visible");
        int size = correctnessList.size();
        if (!correctnessList.get(size - 3)) {
            answer1.setVisibility(View.VISIBLE);
        }
        if (!correctnessList.get(size - 2)) {
            answer2.setVisibility(View.VISIBLE);
        }
        if (!correctnessList.get(size - 1)) {
            answer3.setVisibility(View.VISIBLE);
        }
    }

    public void setAnswersInvisible() {
        answer1.setVisibility(INVISIBLE);
        answer2.setVisibility(INVISIBLE);
        answer3.setVisibility(INVISIBLE);
    }


}
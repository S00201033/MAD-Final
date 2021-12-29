package com.example.S00201033;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final int BLUE = 1;
    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;

    Button bRed, bBlue, bYellow, bGreen, fb;
    int sequenceCount = 4, n = 0;
    private Object mutex = new Object();
    int[] gameSequence = new int[120];
    int arrayIndex = 0;
    int SequenceLength = 0;
    int ScoreSendToSequence = 0;
    TextView SeqCorrect;
    int playseq ;


    //different timers for 4,6,8,10 sequences
    CountDownTimer ct1 = new CountDownTimer(6000,  1500) {

        public void onTick(long millisUntilFinished) {
            oneButton();
        }

        public void onFinish() {
            for (int i = 0; i< arrayIndex; i++) {
                Log.d("game sequence", String.valueOf(gameSequence[i]));
                SequenceLength++;
            }
            Intent i = new Intent(MainActivity.this, SequenceActivity.class);
            i.putExtra("key", gameSequence);
            i.putExtra("GameScore", ScoreSendToSequence);
            i.putExtra("sequencelength", SequenceLength);
            startActivity(i);

        }
    }; //sequence of 4
    CountDownTimer ct2 = new CountDownTimer(9000,  1500) {

        public void onTick(long millisUntilFinished) {
            oneButton();
        }

        public void onFinish() {
            for (int i = 0; i < arrayIndex; i++) {
                Log.d("game sequence", String.valueOf(gameSequence[i]));
                SequenceLength++;
            }
            Intent i = new Intent(MainActivity.this, SequenceActivity.class);
            i.putExtra("key", gameSequence);
            i.putExtra("GameScore", ScoreSendToSequence);
            i.putExtra("sequencelength", SequenceLength);
            startActivity(i);
        }
    }; // sequence of 6
    CountDownTimer ct3 = new CountDownTimer(12000,  1500) {

        public void onTick(long millisUntilFinished) {
            oneButton();
        }

        public void onFinish() {
            for (int i = 0; i < arrayIndex; i++) {
                Log.d("game sequence", String.valueOf(gameSequence[i]));
                SequenceLength++;
            }
            Intent i = new Intent(MainActivity.this, SequenceActivity.class);
            i.putExtra("key", gameSequence);
            i.putExtra("GameScore", ScoreSendToSequence);
            i.putExtra("sequencelength", SequenceLength);
            startActivity(i);
        }
    };// sequence of 8
    CountDownTimer ct4 = new CountDownTimer(15000,  1500) {

        public void onTick(long millisUntilFinished) {
            oneButton();
        }

        public void onFinish() {
            for (int i = 0; i < arrayIndex; i++) {
                Log.d("game sequence", String.valueOf(gameSequence[i]));
                SequenceLength++;
            }
            Intent i = new Intent(MainActivity.this, SequenceActivity.class);
            i.putExtra("key", gameSequence);
            i.putExtra("GameScore", ScoreSendToSequence);
            i.putExtra("sequencelength", SequenceLength);
            startActivity(i);
        }
    }; //sequence of 10

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bRed = findViewById(R.id.btnRed);
        bBlue = findViewById(R.id.btnBlue);
        bYellow = findViewById(R.id.btnYellow);
        bGreen = findViewById(R.id.btnGreen);
        SeqCorrect = findViewById(R.id.tvCorrectSequence);

        playseq = getIntent().getIntExtra("nextlevelscore",0);

        if(playseq >=1)
        {
            SeqCorrect.setText("CORRECT!");
        }

    }

    public void doPlay(View view) {

        ScoreSendToSequence = ScoreSendToSequence + playseq;


        if(ScoreSendToSequence == 0) {
            ct1.start();
        }
        if(ScoreSendToSequence == 4)
        {
            ct2.start();
        }
        if(ScoreSendToSequence == 10)
        {
            ct3.start();
        }
        if(ScoreSendToSequence >= 18)
        {
            ct4.start();
        }

    }

    private void oneButton() {
        n = getRandom(sequenceCount);

        switch (n) {
            case 1:
            case 5:
                flashButton(bBlue);
                gameSequence[arrayIndex++] = BLUE;
                break;
            case 2:
            case 6:
                flashButton(bRed);
                gameSequence[arrayIndex++] = RED;
                break;
            case 3:
            case 7:
                flashButton(bYellow);
                gameSequence[arrayIndex++] = YELLOW;
                break;
            case 4:
            case 8:
                flashButton(bGreen);
                gameSequence[arrayIndex++] = GREEN;
                break;
            default:
                break;
        }   // end switch
    }

    private int getRandom(int maxValue) {
        return ((int) ((Math.random() * maxValue) + 1));
    }

    private void flashButton(Button button) {
        fb = button;
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {

                fb.setPressed(true);
                fb.invalidate();
                fb.performClick();
                Handler handler1 = new Handler();
                Runnable r1 = new Runnable() {
                    public void run() {
                        fb.setPressed(false);
                        fb.invalidate();
                    }
                };
                handler1.postDelayed(r1, 600);

            } // end runnable
        };
        handler.postDelayed(r, 600);
    }

}
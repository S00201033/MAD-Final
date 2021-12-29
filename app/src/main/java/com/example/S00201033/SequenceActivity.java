package com.example.S00201033;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SequenceActivity extends AppCompatActivity implements SensorEventListener {
    private final int BLUE = 1;
    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;

    Button bRed, bBlue, bGreen, bYellow,fb , checkseq;
    TextView seqLength ;
    int score =0;
    int seqlengthcounter = 0;

    int[] gameSequence = new int[120];
    int[] selectedSequence = new int[120];
    int arrayIndex = 0, SeqLen = 0;

    boolean highLimitNorth = false;      // detect high limit
    boolean highLimitSouth = false;      // detect high limit
    boolean highLimitEast = false;      // detect high limit
    boolean highLimitWest = false;
    int counterNorth = 0;
    int counterSouth = 0;
    int counterEast = 0;
    int counterWest = 0;


    private final double NORTH_MOVE_FORWARD = 8.0;     // upper mag limit
    private final double NORTH_MOVE_BACKWARD = 6.0;      // lower mag limit

    private final double SOUTH_MOVE_FORWARD = 6.0;     // upper mag limit
    private final double SOUTH_MOVE_BACKWARD = 8.0; // lower mag limit


    private final double WEST_MOVE_FORWARD = -4;     // upper mag limit
    private final double WEST_MOVE_BACKWARD = 0; // lower mag limit

    private final double EAST_MOVE_FORWARD = 4;     // upper mag limit
    private final double EAST_MOVE_BACKWARD = 0;

    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence);

        bRed = findViewById(R.id.btnRed);
        bBlue = findViewById(R.id.btnBlue);
        bGreen = findViewById(R.id.btnGreen);
        bYellow = findViewById(R.id.btnYellow);
        seqLength = findViewById(R.id.tvSequenceLength);
        checkseq = findViewById(R.id.btnCheckSequence);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //length of the sequence
        SeqLen = getIntent().getIntExtra("sequencelength",0);
        seqLength.setText( seqlengthcounter+"/"+SeqLen);

        //adding the sequence to the array & logging it in the logcat
        Bundle bundle = getIntent().getExtras();
        gameSequence = bundle.getIntArray("key");
        for (int i = 0; i< SeqLen; i++)
            Log.d("game sequence in activty 2", String.valueOf(gameSequence[i]));



    }
    protected void onResume() {
        super.onResume();
        // turn on the sensor
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);    // turn off listener to save power
    }
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // North Movement
        if ((x > NORTH_MOVE_FORWARD) && (highLimitNorth == false)) {
            highLimitNorth = true;
        }
        if ((x < NORTH_MOVE_BACKWARD) && (highLimitNorth == true)) {
            // we have a tilt to the NORTH
            counterNorth++;
            // tvNorth.setText(String.valueOf(counterNorth));
            highLimitNorth = false;

            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {

                    bRed.setPressed(true);
                    bRed.invalidate();
                    bRed.performClick();
                    Handler handler1 = new Handler();
                    Runnable r1 = new Runnable() {
                        public void run() {
                            bRed.setPressed(false);
                            bRed.invalidate();
                        }
                    };
                    handler1.postDelayed(r1, 600);

                } // end runnable
            };
            handler.postDelayed(r, 600);


        }
        if ((x < SOUTH_MOVE_FORWARD && z < 0) && (highLimitSouth == false)) {
            highLimitSouth = true;
        }
        if ((x > SOUTH_MOVE_BACKWARD && z < 0) && (highLimitSouth == true)) {
            // we have a tilt to the SOUTH
            counterSouth++;
            highLimitSouth = false;


            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {

                    bYellow.setPressed(true);
                    bYellow.invalidate();
                    bYellow.performClick();
                    Handler handler1 = new Handler();
                    Runnable r1 = new Runnable() {
                        public void run() {
                            bYellow.setPressed(false);
                            bYellow.invalidate();
                        }
                    };
                    handler1.postDelayed(r1, 600);

                } // end runnable
            };
            handler.postDelayed(r, 600);


        }
        if (y > EAST_MOVE_FORWARD && highLimitEast == false) {
            highLimitEast = true;
        }
        if (y < EAST_MOVE_BACKWARD && highLimitEast == true) {
            // we have a tilt to the EAST
            counterEast++;
            highLimitEast = false;


            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {

                    bGreen.setPressed(true);
                    bGreen.invalidate();
                    bGreen.performClick();
                    Handler handler1 = new Handler();
                    Runnable r1 = new Runnable() {
                        public void run() {
                            bGreen.setPressed(false);
                            bGreen.invalidate();
                        }
                    };
                    handler1.postDelayed(r1, 600);

                } // end runnable
            };
            handler.postDelayed(r, 600);


        }

        // West Movement
        if (y < WEST_MOVE_FORWARD && highLimitWest == false) {
            highLimitWest = true;
        }
        if (y > WEST_MOVE_BACKWARD && highLimitWest == true) {
            // we have a tilt to the WEST
            counterWest++;
            //tvWest.setText(String.valueOf(counterWest));
            highLimitWest = false;


            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {

                    bBlue.setPressed(true);
                    bBlue.invalidate();
                    bBlue.performClick();
                    Handler handler1 = new Handler();
                    Runnable r1 = new Runnable() {
                        public void run() {
                            bBlue.setPressed(false);
                            bBlue.invalidate();
                        }
                    };
                    handler1.postDelayed(r1, 600);

                } // end runnable
            };
            handler.postDelayed(r, 600);


        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not used
    }




    public void DoCheck(View view) {
        //boolean check = selectedSequence.equals(gameSequence);
        if(seqlengthcounter < SeqLen )
        {
            Toast.makeText(this, "there is more to the sequence", Toast.LENGTH_SHORT).show();
        }
        else {
            int PlayerScore = getIntent().getIntExtra("GameScore", 0);
            score = score + PlayerScore;
            for (int i = 0; i < arrayIndex; i++) {
                if (gameSequence[i] == selectedSequence[i]) {

                    score = score + 1;
                    Intent Activity = new Intent(view.getContext(), com.example.S00201033.MainActivity.class);

                    int nextLevel = Integer.valueOf(score);
                    Activity.putExtra("nextlevelscore", nextLevel);

                    startActivity(Activity);
                } else {

                    Intent Activity = new Intent(view.getContext(), GameOverActivity.class);
                    int FinalScore = Integer.valueOf(score);
                    Activity.putExtra("score", FinalScore);
                    startActivity(Activity);
                    break;
                }

            }
        }
    }
    public void doGreen(View view) {
        selectedSequence[arrayIndex++] = GREEN;
        seqlengthcounter = seqlengthcounter +1;
        seqLength.setText( seqlengthcounter+"/"+SeqLen);
    }

    public void doYellow(View view) {
        selectedSequence[arrayIndex++] = YELLOW;
        seqlengthcounter = seqlengthcounter +1;
        seqLength.setText( seqlengthcounter+"/"+SeqLen);
    }

    public void doRed(View view) {
        selectedSequence[arrayIndex++] = RED;
        seqlengthcounter = seqlengthcounter +1;
        seqLength.setText( seqlengthcounter+"/"+SeqLen);
    }

    public void DoBlue(View view) {
        selectedSequence[arrayIndex++] = BLUE;
        seqlengthcounter = seqlengthcounter +1;
        seqLength.setText( seqlengthcounter+"/"+SeqLen);
    }
}
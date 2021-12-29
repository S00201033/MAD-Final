package com.example.S00201033;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        final ListView lv = (ListView) findViewById(R.id.LVList);
        DatabaseHandler db = new DatabaseHandler(this);

        db.emptyHiScores();

        Log.i("Insert: ", "Inserting ..");


        db.addHiScore(new com.example.S00201033.HiScore("2-3-2021", "John", 1));
        db.addHiScore(new com.example.S00201033.HiScore("1-3-2021", "Emma", 2));
        db.addHiScore(new com.example.S00201033.HiScore("29-2-2021", "Jake", 7));
        db.addHiScore(new com.example.S00201033.HiScore("15-1-2021", "Aisling", 8));
        db.addHiScore(new com.example.S00201033.HiScore("13-1-2021", "Carthy", 10));

        //Reading Scores
        Log.i("Reading: ", "Reading all scores..");
        List<com.example.S00201033.HiScore> hiScores = db.getAllHiScores();


        for (com.example.S00201033.HiScore hs : hiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();
        //Writing Scores To Log
            Log.i("Score: ", log);
        }

        Log.i("divider", "==========================");

        com.example.S00201033.HiScore singleScore = db.getHiScore(5);
        Log.i("High Score 5 is by ", singleScore.getPlayer_name() + " with a score of " +
                singleScore.getScore());

        Log.i("divider", "==========================");

        // Calling SQL statement
        List<com.example.S00201033.HiScore> top5HiScores = db.getTopFiveScores();

        for (com.example.S00201033.HiScore hs : top5HiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            Log.i("Score: ", log);
        }
        Log.i("divider", "==========================");
        com.example.S00201033.HiScore hiScore = top5HiScores.get(top5HiScores.size() -1);
        Log.i("fifth highest score: ", String.valueOf(hiScore.getScore()));


        String PlayerName = getIntent().getStringExtra("playername");
        int Score = getIntent().getIntExtra("Score",0);

        int myCurrentScore = 40;
        if(hiScore.getScore() < myCurrentScore){
            db.addHiScore(new com.example.S00201033.HiScore("17 DEC 2020", PlayerName, Score));
        }

        Log.i("divider", "==========================");



        top5HiScores = db.getTopFiveScores();
        for (com.example.S00201033.HiScore hs : top5HiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();


            Log.i("Score: ", log);
        }


        Log.i("divider", "==========================");

        // Calling SQL statement


        top5HiScores = db.getTopFiveScores();
        for (com.example.S00201033.HiScore hs : top5HiScores) {
            String log =
                    "Player --" + hs.getPlayer_name() + " " + "Score -- " + hs.getScore();

            // Writing HiScore to log
            itemsList.add(log);
            Log.i("Score: ", log);
        }

        ArrayAdapter<com.example.S00201033.HiScore> adapter = new ArrayAdapter<com.example.S00201033.HiScore>(this, android.R.layout.simple_list_item_1,itemsList );
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }public List itemsList = new ArrayList();
    public void doPlayAgain(View view) {
        Intent Activity = new Intent(view.getContext(), com.example.S00201033.MainActivity.class);
        startActivity(Activity);
    }
}
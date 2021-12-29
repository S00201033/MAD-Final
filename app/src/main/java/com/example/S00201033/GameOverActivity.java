package com.example.S00201033;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    int Final_Score;
    TextView Game_Over_Score;
    EditText playername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        playername = findViewById(R.id.ETHighScoreName);
        Game_Over_Score = findViewById(R.id.tvGameOverScoreLabel);
        Final_Score = getIntent().getIntExtra("score",0);

        Game_Over_Score.setText(String.valueOf(Final_Score));

    }

    public void doPlayAgain(View view) {
        Intent Activity = new Intent(view.getContext(), com.example.S00201033.MainActivity.class);

        startActivity(Activity);
    }

    public void DoHighScoreBoard(View view) {
        Intent Activity = new Intent(view.getContext(), com.example.S00201033.HighScoreActivity.class);
        String userName = playername.getText().toString();
        Activity.putExtra("playername",userName);
        int playerScore = Integer.valueOf(Game_Over_Score.getText().toString());
        Activity.putExtra("Score",playerScore);
        startActivity(Activity);
    }
}
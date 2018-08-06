package com.example.stanvolcere.gamesuite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stanvolcere.gamesuite.ui.ConnectFourActivity;
import com.example.stanvolcere.gamesuite.ui.ReversiActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ChooseDifficultyActivity extends AppCompatActivity {

    @BindView(R.id.singlePlayerButton)  Button singleButton;
    @BindView(R.id.buttonTwoPlayer)     Button twoPlayerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficulty);
    }

    @OnClick(R.id.singlePlayerButton)
    public void easyDifficulty(View v) {

        if(getIntent().getIntExtra("Game", 0) == 1){
            Intent intent = new Intent(getBaseContext(), ReversiActivity.class);
            intent.putExtra("GameMode", 1);
            startActivity(intent);
        }
        if(getIntent().getIntExtra("Game", 0) == 2){
            Intent intent = new Intent(getBaseContext(), ConnectFourActivity.class);
            intent.putExtra("GameMode", 1);
            startActivity(intent);
        }

    }

    @OnClick(R.id.buttonTwoPlayer)
    public void normalDifficulty(View v) {

        if(getIntent().getIntExtra("Game", 0) == 1){
            Intent intent = new Intent(getBaseContext(), ReversiActivity.class);
            intent.putExtra("GameMode", 2);
            startActivity(intent);
        }
        if(getIntent().getIntExtra("Game", 0) == 2){
            Intent intent = new Intent(getBaseContext(), ConnectFourActivity.class);
            intent.putExtra("GameMode", 2);
            startActivity(intent);
        }
    }
}

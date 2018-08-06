package com.example.stanvolcere.gamesuite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.stanvolcere.gamesuite.ui.ConnectFourActivity;
import com.example.stanvolcere.gamesuite.ui.ReversiActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameModeSelectorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @BindView(R.id.singlePlayerButton)  Button singleButton;
    @BindView(R.id.buttonTwoPlayer)     Button twoPlayerButton;
    int difficulty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode_selector);

        ButterKnife.bind(this);



        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.arrayLevels, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);



    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
    // An item was selected. You can retrieve the selected item using
    // parent.getItemAtPosition(pos)
        difficulty = Integer.parseInt(parent.getItemAtPosition(pos).toString());
}

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        difficulty = 3;
    }

    @OnClick(R.id.singlePlayerButton)
    public void proceedSinglePlayer(View v) {

        if(getIntent().getIntExtra("Game", 0) == 1){
            Intent intent = new Intent(getBaseContext(), ReversiActivity.class);
            intent.putExtra("GameMode", 1);
            intent.putExtra("Difficulty", difficulty);
            startActivity(intent);
        }
        if(getIntent().getIntExtra("Game", 0) == 2){
            Intent intent = new Intent(getBaseContext(), ConnectFourActivity.class);
            intent.putExtra("GameMode", 1);
            intent.putExtra("Difficulty", difficulty);
            startActivity(intent);
        }

    }

    @OnClick(R.id.buttonTwoPlayer)
    public void proceedTwoPlayer(View v) {

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

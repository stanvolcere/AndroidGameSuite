package com.example.stanvolcere.gamesuite.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stanvolcere.gamesuite.R;
import com.example.stanvolcere.gamesuite.connectfour.logic.AlphaBetaConnectFour;
import com.example.stanvolcere.gamesuite.connectfour.logic.AlternativeAI;
import com.example.stanvolcere.gamesuite.connectfour.logic.ConnectFourRules;
import com.example.stanvolcere.gamesuite.connectfour.model.ConnectFourBoard;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConnectFourActivity extends AppCompatActivity {

    //game elements
    ConnectFourBoard board;
    ConnectFourRules rules;
    AlphaBetaConnectFour alphaBetaConnectFour;

    //
    AlternativeAI ai;


    //ui elements
    @BindView(R.id.gridview)    GridView gridview;
    @BindView(R.id.quitButton)  Button   quitButton;
    @BindView(R.id.resetButton)  Button   resetButton;
    @BindView(R.id.scoreBoard)  TextView scoreBoard;
    private ImageView imageView;

    //utility
    int currentPlayer       = 0;
    int humanPlayer         = 1;
    int AI                  = 0;
    boolean isSinglePlayer  = true;
    boolean gameOver        = false;

    final int red           = 1;
    final int yellow        = -1;
    private int winner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_four);

        //game elements
        board = new ConnectFourBoard();
        rules = new ConnectFourRules();
        alphaBetaConnectFour = new AlphaBetaConnectFour(6);

        //
        ai = new AlternativeAI();


        if(getIntent().getIntExtra("GameMode", 0) == 1){
            isSinglePlayer = true;
        } else {
            isSinglePlayer = false;
        }

        //utility
        currentPlayer = humanPlayer;


        //ui elements


        ButterKnife.bind(this);

        gridview.setAdapter(new ConnectFourImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
//                Toast.makeText(ConnectFourActivity.this, "" + position,
//                        Toast.LENGTH_SHORT).show();

                if(!gameOver) {

                    //human move
                    addChipToBoard(position, currentPlayer);
                    //rules.checkForWinner(board);


                    //update the UI
                    updateUI();
                    updateCurrentPlayer();
                    switchPlayer();

                    winner = rules.checkForWinner(board);

                    checkForWinner();
//                    Runnable r = new Runnable() {
//                        @Override
//                        public void run() {
//                            //check winner
//                            winner = rules.checkForWinner(board);
//                        }
//                    };
//                    Thread thread = new Thread(r);
//                    thread.setName("RuleCheckThread");
//                    thread.start();
//
//                    try {
//                        thread.join();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }




                    //computer move
                    if (isSinglePlayer && winner == 0) {
                        //computermove
                        computerMove();
                        switchPlayer();
                    }

                    //update the UI
                    updateUI();
                    updateCurrentPlayer();


                    winner = rules.checkForWinner(board);


                    checkForWinner();

                } else {

                }

            }
        });

    }

    private void checkForWinner() {
        if (winner != 0) {
            Toast.makeText(ConnectFourActivity.this, "GAME OVER" ,
                    Toast.LENGTH_SHORT).show();
            if(winner == 1){
                scoreBoard.setText("RED WINS!");
                scoreBoard.setTextColor(Color.RED);
            } else {
                scoreBoard.setText("YELLOW WINS!");
                scoreBoard.setTextColor(Color.YELLOW);
            }

            //quitButton.performClick();
            gameOver = true;
            quitButton.setText("Restart?");
        }
    }

    private void computerMove() {
        //addChipToBoard(alphaBetaConnectFour.performAlphaBeta(board, currentPlayer, -10000, 10000, 6), currentPlayer);
        addChipToBoard(ai.generateComputerMove(board, currentPlayer), currentPlayer);
    }

    private void updateCurrentPlayer() {


        if(currentPlayer == 1){
            scoreBoard.setText("Red Turn");
        } else {
            scoreBoard.setText("Yellow Turn");
        }

    }

    private void switchPlayer(){
        currentPlayer = currentPlayer * -1;
    }


    @OnClick(R.id.quitButton)
    public void quit(View v) {
        // TODO submit data to server...
        if (gameOver){
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        } else {
            finish();
        }

    }

    @OnClick(R.id.resetButton)
    public void reset(View v) {
       ConnectFourActivity.this.recreate();
    }



    private void updateUI() {
        for (int i = 0; i < board.getSize(); i++){
            if(board.getChipType(i) == yellow){

                imageView = (ImageView) gridview.getChildAt(i);
                imageView.setImageResource(R.drawable.yellow);


            } else if (board.getChipType(i) == red){

                imageView = (ImageView) gridview.getChildAt(i);
                imageView.setImageResource(R.drawable.red);

            } else {
                continue;
            }
        }

        //updateCurrentPlayer();
    }

    private void addChipToBoard(int position, int currentPlayer) {
        board.addNewChip(position, currentPlayer);
    }
}

package com.example.stanvolcere.gamesuite.ui;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stanvolcere.gamesuite.R;
import com.example.stanvolcere.gamesuite.reversi.logic_Reversi.*;
import com.example.stanvolcere.gamesuite.reversi.model_Reversi.*;
import com.example.stanvolcere.gamesuite.reversi.tools_Reversi.Pair;
import com.example.stanvolcere.gamesuite.reversi.tools_Reversi.Pair;

import java.util.ArrayList;

//import butterknife.BindView;
//import butterknife.ButterKnife;


public class ReversiActivity extends AppCompatActivity {

    //UI Elements
    private GridView gridview;
    private TextView whitePlayerScore;
    private TextView blackPlayerScore;
    private ImageView imageView;
    private Button quitButton;
    private Button passButton;
    private TextView whoseTurn;
    private Button AIButton;

    //Game Logic Elements
    private ReversiBoard mBoard;
    private ReversiRules reversiRules;
    private AlphaBeta alphaBeta;
    private ReversiBoard clonedBoard;
    private int chosenMove;
    private int currentPosition;
    private int searchDepth;


    //utilities
    final int black = 1;
    final int white = -1;
    Handler handler;
    Pair score;
    int gameMode;

    int currentPlayer;
    final private int humanPlayer = black;

    //this list will store the positions that undergo change on the if said move is made
    private ArrayList<Integer> listOfChanges = new ArrayList<>();

    private ArrayList<Integer> helperList = new ArrayList<>();

    //current board
    ReversiBoard currentBoard = new ReversiBoard();
    private boolean singlePlayer = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reversi);

        searchDepth = getIntent().getIntExtra("Difficulty", 0);

        //setup
        mBoard       = new ReversiBoard();
        reversiRules = new ReversiRules();
        clonedBoard  = new ReversiBoard();
        //alphaBeta    = new AlphaBeta(3);
        alphaBeta    = new AlphaBeta(searchDepth);

        //imageView = new ImageView();
        handler = new Handler();


        //Reversi Rules thread setup
        final Thread t1 = new Thread();
        t1.setName("LogicThread");
        t1.start();


        gameMode = getIntent().getIntExtra("GameMode", 0);
        if(gameMode == 1){
            singlePlayer = true;
        } else {
            singlePlayer = false;
        }


        //create ui element
        whitePlayerScore    = (TextView) findViewById(R.id.whiteChipScore);
        blackPlayerScore    = (TextView) findViewById(R.id.blackChipScore);
        whoseTurn           = (TextView) findViewById(R.id.turnTextView);
        quitButton          = (Button) findViewById(R.id.quitButton);
        passButton          = (Button) findViewById(R.id.passButton);

        gridview            = (GridView) findViewById(R.id.gridview);

        //tools
        currentPlayer = black;
        score = new Pair(0,0);


        setupUI();
        gridview.setAdapter(new ReversiImageAdapter(this));


        //ask for a user input
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(ReversiActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                //imageView = (ImageView) gridview.getAdapter().getView(position,v,parent);


                //update the scores
//                score = mBoard.getScores();
//                updateScores(score);
//                //update ui elements
//                updateUIElementHuman(mBoard);

                //currentBoard = mBoard.cloneBoard(mBoard);
//                Runnable r = new Runnable() {
//                    @Override
//                    public void run() {
//                        //ai move
//                        if (currentPlayer == humanPlayer){
//
//                        }
//                    }
//                };
//
//                Thread thread = new Thread(r);
//                thread.start();
//                try {
//                    thread.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

//
//                if (currentPlayer == humanPlayer) {
//                    listOfChanges = getListofChanges(position);
//                    //reversiRules.clearListOfChanges();
//                }
                currentPosition = position;

                if (currentPlayer == humanPlayer || !singlePlayer) {

                    Runnable r2 = new Runnable() {
                        @Override
                        public void run() {
                            //if (currentPlayer == humanPlayer) {
                            listOfChanges = getListofChanges(currentPosition);
                            //}

                        }

                    };
                    Thread t2 = new Thread(r2);
                    t2.start();


                    try {
                        t2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (listOfChanges.isEmpty()) {
                        Toast.makeText(ReversiActivity.this, "Move unavailable. Try Again", Toast.LENGTH_SHORT).show();
                    } else {
                        listOfChanges.add(position);

//                    Runnable r1 = new Runnable() {
//                        @Override
//                        public void run() {
//                            //update the board
//
//
//                        }
//
//                    };
//                    Thread t1 = new Thread(r1);
//                    t1.start();
                        mBoard.updateBoard(listOfChanges, currentPlayer);
                        currentPlayer = currentPlayer * -1;

//                    runOnUiThread(new Runnable(){
//                        public void run() {
//
//                            //update the scores
//                            score = mBoard.getScores();
//                            updateScores(score);
//                            //update ui elements
//                            updateUIElementHuman(mBoard);
//                        }
//                    });


//                    try {
//                        t1.join();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }


                        //try placing async up here (with compmove() a background)???????
//                    runOnUiThread(new Runnable(){
//                        public void run() {
//
//                            //update the scores
//                            score = mBoard.getScores();
//                            updateScores(score);
//                            //update ui elements
//                            updateUIElementHuman(mBoard);
//                        }
//                    });

                        //update the scores
                        score = mBoard.getScores();
                        updateScores(score);
                        //update ui elements
                        updateUIElements(mBoard);

//                    //here we generate a computer move
//                    Runnable r = new Runnable() {
//                    @Override
//                    public void run() {
//                        //ai move
//                        if (currentPlayer != humanPlayer){
//                            //make computer move
//                            generateComputerMove();
//
//                        }
//                    }
//                    };
//                    Thread thread = new Thread(r);
//                    thread.setName("AlphaBetaThread");
//                    thread.start();

//                    try {
//                        thread.join();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }


//                    runOnUiThread(new Runnable(){
//                        public void run() {
//
//                            //update the scores
//                            score = mBoard.getScores();
//                            updateScores(score);
//                            //update ui elements
//                            updateUIElementHuman(mBoard);
//                        }
//                    });


//                    //update the scores
//                    score = mBoard.getScores();
//                    updateScores(score);
//
//                    //update ui elements
//                    updateUIElements(mBoard);
                    }
                }



//                if (currentPlayer != humanPlayer) {
//                    generateComputerMove();
//                    score = mBoard.getScores();
//                    updateScores(score);
//                    updateUIElements(mBoard);
//                }

//                score = mBoard.getScores();
//                updateScores(score);
//                updateUIElements(mBoard);
                //here we generate a computer move

                if (singlePlayer && currentPlayer != humanPlayer) {
//                    Runnable r = new Runnable() {
//                        @Override
//                        public void run() {
//                            //ai move
//                            if (currentPlayer != humanPlayer) {
//                                //make computer move
//                                generateComputerMove();
//
//                            }
//                        }
//                    };
//                    Thread thread = new Thread(r);
//                    thread.setName("AlphaBetaThread");
//                    thread.start();
//
//                    try {
//                        thread.join();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }



                    //make computer move
                    Toast.makeText(ReversiActivity.this, "Game is Thinking!", Toast.LENGTH_SHORT).show();
                    generateComputerMove();
                }

                score = mBoard.getScores();
                updateScores(score);
                //update ui elements
                updateUIElements(mBoard);

                checkForWinner(mBoard);



//                Runnable r = new Runnable() {
//                    @Override
//                    public void run() {
//                        //ai move
//                        if (currentPlayer != humanPlayer){
//                            generateComputerMove();
//                        }
//                    }
//                };
//
//                Thread thread = new Thread(r);
//                thread.start();


                //update ui elements

            }


        });



        //gridview.performClick();



        quitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mBoard.checkIfBoardIsFull()){
                    Intent i = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                } else {
                    finish();
                }

            }
        });


        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < mBoard.getSize(); i++) {
                    helperList.addAll(getListofChanges(i));
                }

                if(helperList.isEmpty()){
                    currentPlayer = currentPlayer * -1;
                    generateComputerMove();
                    updateUIElements(mBoard);
                } else {
                    Toast.makeText(ReversiActivity.this, "You have moves available!",  Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void checkForWinner(ReversiBoard mBoard) {
        if(singlePlayer) {
            if (mBoard.checkIfBoardIsFull()) {
                if (score.getFirstElement() < score.getSecondElement()) {
                    if (score.getFirstElement() == humanPlayer) {

                        whoseTurn.setText("YOU WIN!!!");
                    } else {
                        whoseTurn.setText("You Lost!!!");
                    }
                } else {
                    if (score.getSecondElement() == humanPlayer) {
                        whoseTurn.setText("You Lost!!!");
                    } else {
                        whoseTurn.setText("YOU WIN!!!");
                    }
                }

                quitButton.setText("Restart?");
            }
        } else {
            if (mBoard.checkIfBoardIsFull()) {
                whoseTurn.setText("");
                quitButton.setText("Restart?");
            }
        }
    }

    private void updateScores(Pair score) {
        blackPlayerScore.setText(score.getFirstElement() + "");
        whitePlayerScore.setText(score.getSecondElement() + "");
    }

    private void updateUIElements(ReversiBoard mBoard) {

        for (int i = 0; i < mBoard.getSize(); i++){
            if(mBoard.getChipType(i) == white){

                imageView = (ImageView) gridview.getChildAt(i);
                //imageView = (ImageView) gridview.getAdapter().getView(position,v,parent);
                imageView.setImageResource(R.drawable.whitechip);


            } else if (mBoard.getChipType(i) == black){

                imageView = (ImageView) gridview.getChildAt(i);
                //imageView = (ImageView) gridview.getAdapter().getView(position,v,parent);
                imageView.setImageResource(R.drawable.blackchip);

            } else {
                continue;
            }
        }
        setupUI();

    }

    private void updateUIElementHuman(ReversiBoard mBoard) {

        for (int i = 0; i < mBoard.getSize(); i++){
            if(mBoard.getChipType(i) == white){

                imageView = (ImageView) gridview.getChildAt(i);
                //imageView = (ImageView) gridview.getAdapter().getView(position,v,parent);
                imageView.setImageResource(R.drawable.whitechip);


            } else if (mBoard.getChipType(i) == black){

                imageView = (ImageView) gridview.getChildAt(i);
                //imageView = (ImageView) gridview.getAdapter().getView(position,v,parent);
                imageView.setImageResource(R.drawable.blackchip);

            } else {
                continue;
            }
        }
        setupUI();

    }

    private void generateComputerMove() {

        //compute an AI move
        clonedBoard = mBoard.cloneBoard(mBoard);
        chosenMove = alphaBeta.performAlphaBeta(clonedBoard, currentPlayer, 0,-10000, 10000, 3 );
        listOfChanges = getListofChanges(chosenMove);
        listOfChanges.add(chosenMove);
        mBoard.updateBoard(listOfChanges, currentPlayer);

        currentPlayer = currentPlayer * -1;
    }

    private void setupUI() {
        if(currentPlayer == black){
            whoseTurn.setText("Turn: Black Chips");
        } else {
            whoseTurn.setText("Turn: White Chips");
        }
    }



    private ArrayList<Integer> getListofChanges(int position) {

//        Triple board_Position_Player = new Triple(mBoard, position, currentPlayer);
//
//        //send messages to the handler for processing
//        Message message = Message.obtain();
//        message.obj = board_Position_Player;board_Position_Player


        return reversiRules.availablePositions(mBoard, position, currentPlayer);
    }

//    //private void updateBoard(ReversiBoard mBoard, ArrayList<Integer> listOfChanges, int chipType) {
//        for(int position: listOfChanges){
//            mBoard.addToBoard(position, chipType);
//        }
//    }

    private class checkPositionValid extends AsyncTask<String, Void, Void> {

//        @Override
//        protected void doInBackground(ReversiBoard params) {
//            updateUIElementHuman(params);
//        }


        @Override
        protected Void doInBackground(String... boards) {
            //generateComputerMove();
            if (currentPlayer == humanPlayer) {
                            listOfChanges = getListofChanges(currentPosition);
                            //reversiRules.clearListOfChanges();
                        }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}

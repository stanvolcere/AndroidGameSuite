package com.example.stanvolcere.gamesuite.reversi.model_Reversi;

import com.example.stanvolcere.gamesuite.reversi.logic_Reversi.ReversiRules;
import com.example.stanvolcere.gamesuite.reversi.tools_Reversi.Pair;

import java.util.ArrayList;

/**
 * Created by stanvolcere on 14/02/2018.
 */

public class ReversiBoard {

    private int black = 1;
    private int empty = 0;
    private int white = -1;

    final private ReversiRules reversiRules;

    private int[][] board;
    private int[][] mBoardValues = new int[][]{
            {  120, -20,  20,   5,   5,  20, -20, 120},
            {  -20, -40,  -5,  -5,  -5,  -5, -40, -20},
            {   20,  -5,  15,   3,   3,  15,  -5,  20},
            {    5,  -5,   3,   3,   3,   3,  -5,   5},
            {    5,  -5,   3,   3,   3,   3,  -5,   5},
            {   20,  -5,  15,   3,   3,  15,  -5,  20},
            {  -20, -40,  -5,  -5,  -5,  -5, -40, -20},
            {  120, -20,  20,   5,   5,  20, -20, 120}
    };

    public ReversiBoard() {
        board = new int[8][8];
        //sets the middle tiles accordingly

        for (int i = 0; i < this.getSize(); i++){
            this.addToBoard(i, empty);
        }


        //black tiles
        board[3][3] = black;
        board[4][4] = black;

        //white tiles
        board[3][4] = white;
        board[4][3] = white;


        reversiRules = new ReversiRules();
    }

    //get board method
    public int[][] getBoard() {
        return board;
    }

    //method to add chip to board
    public void addToBoard(int position, int chiptype){
        this.board[position/8][position%8] = chiptype;
    }


    //method to clone a board
    public ReversiBoard cloneBoard(ReversiBoard currentBoard) {
        ReversiBoard clonedBoard = new ReversiBoard();
        Pair pair;

        for (int i = 0; i < currentBoard.getSize(); i++){
            //i here [y position] [x position]
            pair = convertToCoordinate(i);
            clonedBoard.addToBoard(i, currentBoard.getBoard()[pair.getSecondElement()][pair.getFirstElement()]);
            //currentBoard[i%8][i/8];
        }

        return clonedBoard;

    }


    //get board size
    public int getSize() {
        return 64;
    }


    //get chip type at current position
    public int getChipType(int position){
        Pair pair = convertToCoordinate(position);
        return this.board[pair.getSecondElement()][pair.getFirstElement()];
    }


    //method to check if the board is full
    public boolean checkIfBoardIsFull(){
        for (int i = 0; i < this.getSize(); i++){
            if(getChipType(i) ==  empty){
                return false;
            }
        }
        return true;
    }


    //method to check white chips vs. black chips -> compute scores


    //check selscted position is avaliable
    public boolean isPositionAvaliable(int position){
        if (position >= 0 && position < getSize()) {
            Pair pair = convertToCoordinate(position);
            if (this.board[pair.getSecondElement()][pair.getFirstElement()] == empty) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //convert position to coordinates
    public Pair convertToCoordinate(int position) {
                        //always X, Y
        return new Pair((position%8), (position/8));
    }


    public void updateBoard(ArrayList<Integer> listOfChanges, int currentPlayer) {
        for(int position: listOfChanges){
            if (position >= 0 && position < getSize()) {
                this.addToBoard(position, currentPlayer);
            }
        }
    }

    public int evaluate(int currentPlayer) {
        int currentPlayerScore = 0;
        int opponentScore = 0;


        for (int i = 0; i < this.getSize(); i++){
            if(this.getChipType(i) == currentPlayer){
                currentPlayerScore = currentPlayerScore + this.getBoardValueAt(i);
            } else if (this.getChipType(i) == white){
                opponentScore = opponentScore + this.getBoardValueAt(i);
            } else {
                continue;
            }
        }

        return currentPlayerScore - opponentScore;
    }

    private int getBoardValueAt(int i) {
        Pair pair = convertToCoordinate(i);
        return this.mBoardValues[pair.getSecondElement()][pair.getFirstElement()];
    }

    public int terminalEvaluation(int currentPlayer) {

        int playerCount = 0;
        int opponentCount = 0;

        for(int i = 0; i < getSize(); i++){
            if(this.getChipType(i) == currentPlayer){
                playerCount = playerCount + this.getBoardValueAt(i);
            } else if (this.getChipType(i) == (-currentPlayer)){
                opponentCount = opponentCount + this.getBoardValueAt(i);
            } else {
                continue;
            }
        }

        if (playerCount > opponentCount){
            return 500;
        } else if ((playerCount < opponentCount)){
            return -500;
        } else {
            return 0;
        }
    }

    public Pair getScores() {
        int blackCount = 0 ;
        int whiteCount = 0;


        for(int i = 0; i < getSize(); i++){
            if(this.getChipType(i) == black){
                blackCount++;
            } else if (this.getChipType(i) == white){
                whiteCount++;
            } else {
                continue;
            }
        }

        return new Pair(blackCount, whiteCount);
    }

}

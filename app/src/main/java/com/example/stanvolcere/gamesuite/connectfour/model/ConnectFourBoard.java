package com.example.stanvolcere.gamesuite.connectfour.model;

import com.example.stanvolcere.gamesuite.connectfour.logic.ConnectFourRules;
import com.example.stanvolcere.gamesuite.reversi.tools_Reversi.Pair;

import java.util.ArrayList;

/**
 * Created by stanvolcere on 27/02/2018.
 */

public class ConnectFourBoard {

    final private int red = 1;
    final private int empty = 0;
    final private int yellow = -1;

    //connect four board Rules
    ConnectFourRules rules = new ConnectFourRules();

    private int[][] board;
    private static int[][] evaluationTable = {
            {3, 4, 5,  7,  5,  4, 3},
            {4, 6, 8,  10, 8,  6, 4},
            {5, 8, 11, 13, 11, 8, 5},
            {5, 8, 11, 13, 11, 8, 5},
            {4, 6, 8,  10, 8,  6, 4},
            {3, 4, 5,  7,  5,  4, 3}
    };

    private int[] availableSpotInEachColumn = {35,36,37,38,39,40,41};

    //weights for the combinations
    final private int twoComboWeight = 1;
    final private int threeComboWeight = 5;
    final private int fourComboWeight = 100000;

    public ConnectFourBoard() {
        board = new int[6][7];

        for (int i = 0; i < this.getSize(); i++){
            this.addToBoard(i, empty);
        }
    }

    private void addToBoard(int position, int chipType) {
        this.board[position/7][position%7] = chipType;
    }

    public void undoMove(int position){
        this.board[position/7][position%7] = empty;
    }

    public int getBoardValue(int position){
        return evaluationTable[position/7][position%7];
    }

    public int getSize() {
        return 42;
    }

    //method to clone a board
    public ConnectFourBoard cloneBoard() {
        ConnectFourBoard clonedBoard = new ConnectFourBoard();
        Pair pair;

        for (int i = 0; i < this.getSize(); i++){
            //i here [y position] [x position]
            pair = convertToCoordinate(i);
            clonedBoard.addToBoard(i, getChipType(i));
        }

        for (int j = 0; j < this.availableSpotInEachColumn.length; j++){
            clonedBoard.availableSpotInEachColumn[j] = this.availableSpotInEachColumn[j];
        }

        return clonedBoard;

    }

    private Pair convertToCoordinate(int i) {
        return new Pair((i%7), (i/7));
    }

    private int[][] getBoard() {
        return board;
    }



    //get chip type at current position
    public int getChipType(int position){
        if (position >= 0 && position <42) {
            Pair pair = convertToCoordinate(position);
            return this.board[pair.getSecondElement()][pair.getFirstElement()];
        }
        return 0;
    }

    public void addNewChip(int position, int chipType) {

        int column = position%7;
        int positionToAdd;

        if ((column >= 0 && column < 7)){
            //addition takes place here
            positionToAdd = availableSpotInEachColumn[column];

            if (positionToAdd >= 0 && positionToAdd < 42){
                addToBoard(positionToAdd, chipType);

                //update availableSpotInEachColumn
                availableSpotInEachColumn[column] = positionToAdd - 7;
            }

        }
    }

    public ArrayList<Integer> getAvailablePositions() {
        ArrayList<Integer> availablePositions = new ArrayList<>();

        for (int item : availableSpotInEachColumn){
            if(item >= 0){
                availablePositions.add(item);
            }
        }

        return availablePositions;
    }


    //alpha beta section
    public int evaluate(int currentPlayer) {

        //way of evaluating the board
        int currentPlayerScore;
        int opponentScore;
        int opponent = currentPlayer * -1;

        //int result = rules.checkCombo(this, currentPlayer, 4);


        //rules.checkForWinner(this);
//        if (result == opponent){
//            return -100000;
//        } else if (result == currentPlayer){
//            return 100000;
//        } else {
//
//        }


        if (rules.checkCombo(this, currentPlayer, 4) > 0) {
            return 10000000;

        } else if (rules.checkCombo(this, opponent, 4) > 0){
            return -10000000;

        } else {
            //check all the combinations
            currentPlayerScore =    ((rules.checkCombo(this, currentPlayer,2) * twoComboWeight) +
                                    (rules.checkCombo(this, currentPlayer,3) * threeComboWeight)
//                    +
//                                    (rules.checkCombo(this, currentPlayer, 4) * fourComboWeight)
            );


            opponentScore =         ((rules.checkCombo(this, (opponent),2) * twoComboWeight) +
                                    (rules.checkCombo(this, (opponent), 3) * threeComboWeight)

            );


            return (currentPlayerScore - opponentScore);
        }



//        if (rules.checkForWinner(this) == (-currentPlayer)){
//            return -1000000;
//        } else {
//            //check all the combinations
//            currentPlayerScore =    ((rules.checkCombo(this, currentPlayer,2) * twoComboWeight) +
//                    (rules.checkCombo(this, currentPlayer,3) * threeComboWeight) +
//                    (rules.checkCombo(this, currentPlayer, 4) * fourComboWeight)
//            );
//
//
////            opponentScore =         ((rules.checkCombo(this, -currentPlayer,2) * twoComboWeight) +
////                    (rules.checkCombo(this, -currentPlayer, 3) * threeComboWeight)
////                    //+ (rules.checkCombo(this, -currentPlayer, 4) * fourComboWeight)
////            );
//
//            //return (currentPlayerScore - opponentScore);
//            return (currentPlayerScore);
//        }


    }
}


package com.example.stanvolcere.gamesuite.connectfour.logic;

import com.example.stanvolcere.gamesuite.connectfour.model.ConnectFourBoard;

import java.util.ArrayList;

/**
 * Created by stanvolcere on 27/02/2018.
 */

public class ConnectFourRules {

    final private int red = 1;
    final private int empty = 0;
    final private int yellow = -1;

    boolean win = false;
    int currentColumn       = 0;
    final int bottomLeft    = 35;
    final int bottomRight    = 41;

    //Helper arraylist used to figure out if there is a sequence of reds/yellows
    ArrayList<Integer> currentSequence = new ArrayList<>();

    public ConnectFourRules() {
    }

    public int checkForWinner(ConnectFourBoard currentBoard){

        if(checkVertical(currentBoard)
                || checkHorizontal(currentBoard)
                || checkDiagonalUp(currentBoard)
                || checkDiagonalDown(currentBoard)){
            return currentSequence.get(0);
        } else {
            return 0;
        }

    }

    private boolean checkHorizontal(ConnectFourBoard currentBoard) {

        //equals 35 because that is the bottom left most position on the board
        currentColumn = bottomLeft;

        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                if(currentBoard.getChipType(currentColumn) != empty){
                    if(currentSequence.isEmpty()){
                        currentSequence.add(currentBoard.getChipType(currentColumn));
                        currentColumn++;
                    } else {
                        if (currentBoard.getChipType(currentColumn) == currentSequence.get(currentSequence.size() - 1)) {
                            currentSequence.add(currentBoard.getChipType(currentColumn));
                            currentColumn++;
                            if (currentSequence.size() == 4) {
                                return true;
                            }
                        } else {
                            currentSequence.clear();
                            currentSequence.add(currentBoard.getChipType(currentColumn));
                            currentColumn++;
                        }
                    }
                } else {
                    currentColumn++;
                    currentSequence.clear();
                }
            }
            currentColumn = bottomLeft;
            currentColumn-=(7*(i+1));
            currentSequence.clear();

        }
        return false;
    }

    private boolean checkDiagonalDown(ConnectFourBoard currentBoard) {

        //equals 35 because that is the bottom left most position on the board
        currentColumn = 14;

        /// first part of checking diagonally !!
        for (int i = 0; i < 3; i++){

            do{
                if(currentBoard.getChipType(currentColumn) != empty){

                    if(currentSequence.isEmpty()){
                        currentSequence.add(currentBoard.getChipType(currentColumn));
                        currentColumn+=8;
                    } else {
                        if (currentBoard.getChipType(currentColumn) == currentSequence.get(currentSequence.size() - 1)) {
                            currentSequence.add(currentBoard.getChipType(currentColumn));
                            currentColumn+=8;
                            if (currentSequence.size() == 4) {
                                return true;
                            }
                        } else {
                            currentSequence.clear();
                            currentSequence.add(currentBoard.getChipType(currentColumn));
                            currentColumn+=8;
                        }
                    }

                } else {
                    currentSequence.clear();
                    currentColumn+=8;
                }

            } while(currentColumn < 42);

            currentColumn = 14;
            currentColumn-=(7*(i+1));
            currentSequence.clear();

        }

        currentColumn = 1;

        /// second part of checking diagonally !!
        for (int i = 0; i < 3; i++){

            do{
                if(currentBoard.getChipType(currentColumn) != empty){

                    if(currentSequence.isEmpty()){
                        currentSequence.add(currentBoard.getChipType(currentColumn));
                        currentColumn+=8;
                    } else {
                        if (currentBoard.getChipType(currentColumn) == currentSequence.get(currentSequence.size() - 1)) {
                            currentSequence.add(currentBoard.getChipType(currentColumn));
                            currentColumn+=8;
                            if (currentSequence.size() == 4) {
                                return true;
                            }
                        } else {
                            currentSequence.clear();
                            currentSequence.add(currentBoard.getChipType(currentColumn));
                            currentColumn+=8;
                        }
                    }

                } else {
                    currentSequence.clear();
                    currentColumn+=8;
                }

            } while(currentColumn < 42);

            currentColumn = 1;
            currentColumn+=(i+1);
            currentSequence.clear();

        }

        return false;
    }

    private boolean checkDiagonalUp(ConnectFourBoard currentBoard) {

        //equals 35 because that is the bottom left most position on the board
        currentColumn = 21;

        /// first part of checking diagonally !!
        for (int i = 0; i < 3; i++){

            do{
                if(currentBoard.getChipType(currentColumn) != empty){

                    if(currentSequence.isEmpty()){
                        currentSequence.add(currentBoard.getChipType(currentColumn));
                        currentColumn-=6;
                    } else {
                        if (currentBoard.getChipType(currentColumn) == currentSequence.get(currentSequence.size() - 1)) {
                            currentSequence.add(currentBoard.getChipType(currentColumn));
                            currentColumn-=6;
                            if (currentSequence.size() == 4) {
                                return true;
                            }
                        } else {
                            currentSequence.clear();
                            currentSequence.add(currentBoard.getChipType(currentColumn));
                            currentColumn-=6;
                        }
                    }

                } else {
                    currentSequence.clear();
                    currentColumn-=6;
                }

            } while(currentColumn > 0);

            currentColumn = 21;
            currentColumn+=(7*(i+1));
            currentSequence.clear();

        }

        currentColumn = 36;

        /// second part of checking diagonally !!
        for (int i = 0; i < 3; i++){

            do{
                if(currentBoard.getChipType(currentColumn) != empty){

                    if(currentSequence.isEmpty()){
                        currentSequence.add(currentBoard.getChipType(currentColumn));
                        currentColumn-=6;
                    } else {
                        if (currentBoard.getChipType(currentColumn) == currentSequence.get(currentSequence.size() - 1)) {
                            currentSequence.add(currentBoard.getChipType(currentColumn));
                            currentColumn-=6;
                            if (currentSequence.size() == 4) {
                                return true;
                            }
                        } else {
                            currentSequence.clear();
                            currentSequence.add(currentBoard.getChipType(currentColumn));
                            currentColumn-=6;
                        }
                    }

                } else {
                    currentSequence.clear();
                    currentColumn-=6;
                }

            } while(currentColumn > 0);

            currentColumn = 36;
            currentColumn+=(i+1);
            currentSequence.clear();

        }

        return false;
    }

    private boolean checkVertical(ConnectFourBoard currentBoard) {

        //equals 35 because that is the bottom left most position on the board
        currentColumn = bottomLeft;

        for(int i = 0; i <= 6; i++){
            for(int j = currentColumn; j >= 0; j=j-7){
                if(currentBoard.getChipType(j) != empty) {
                    if (currentSequence.isEmpty()) {
                        currentSequence.add(currentBoard.getChipType(j));
                    } else {
                        if (currentBoard.getChipType(j) == currentSequence.get(currentSequence.size() - 1)) {
                            currentSequence.add(currentBoard.getChipType(j));
                            if (currentSequence.size() == 4) {
                                return true;
                            }
                        } else {
                            currentSequence.clear();
                            currentSequence.add(currentBoard.getChipType(j));
                        }
                    }
                } else {
                    currentSequence.clear();
                    currentColumn++;
                    break;
                }
            }
            //if we reach this point it means in the current column that was being checked for a winner
            currentSequence.clear();
            //currentColumn++;

        }
        return false;
    }


    public int checkCombo(ConnectFourBoard currentBoard, int currentPlayer, int comboSize) {

        int counter = 0;

        //performs a check vertically ==============================================================
        currentColumn = bottomLeft;

        for(int i = 0; i < 6; i++){
            for(int j = currentColumn; j >= 0; j=j-7){
                if(currentBoard.getChipType(j) != empty) {
//                    if (currentSequence.isEmpty()) {
//                        currentSequence.add(currentBoard.getChipType(j));
//                    } else {
//                        if (currentBoard.getChipType(j) == currentPlayer) {
//                            currentSequence.add(currentBoard.getChipType(j));
//                            if (currentSequence.size() == comboSize
//                                    && currentBoard.getChipType(j-7) != currentPlayer) {
//                                counter++;
//                                currentSequence.clear();
//                            }
//                        } else {
//                            currentSequence.clear();
//                            currentSequence.add(currentBoard.getChipType(j));
//                        }
//                    }
                    if (currentBoard.getChipType(j) == currentPlayer){
                        currentSequence.add(currentBoard.getChipType(j));
                            if (currentSequence.size() == comboSize
                                    && currentBoard.getChipType(j-7) != currentPlayer) {
                                counter++;
                                currentSequence.clear();
                            }
                    } else {
                        currentSequence.clear();
                    }
//                    else {
//                        currentSequence.clear();
//                        //currentSequence.add(currentBoard.getChipType(j));
//                    }
                } else {
                    currentSequence.clear();
                    currentColumn++;
                    break;
                }
            }
            currentSequence.clear();
        }

        //horizontal check ===========================================================================
        currentColumn = bottomLeft;

        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                if(currentBoard.getChipType(currentColumn) != empty){

//                    if(currentSequence.isEmpty()){
//                        currentSequence.add(currentBoard.getChipType(currentColumn));
//                        currentColumn++;
//                    } else {
//                        if (currentBoard.getChipType(currentColumn) == currentPlayer) {
//                            currentSequence.add(currentBoard.getChipType(currentColumn));
//                            currentColumn++;
//                            if (currentSequence.size() == comboSize
//                                    && currentBoard.getChipType(currentColumn+1) != currentPlayer) {
//                                counter++;
//                                currentSequence.clear();
//                            }
//                        } else {
//                            currentSequence.clear();
//                            currentSequence.add(currentBoard.getChipType(currentColumn));
//                            currentColumn++;
//                        }
//                    }
                    if (currentBoard.getChipType(currentColumn) == currentPlayer){
                        currentSequence.add(currentBoard.getChipType(currentColumn));
                        if (currentSequence.size() == comboSize && currentBoard.getChipType(currentColumn+1) != currentPlayer) {
                                counter++;
                                currentSequence.clear();
                        }
                        currentColumn++;
                    } else {
                        currentSequence.clear();
                        currentColumn++;
                    }

                } else {
                    currentColumn++;
                    currentSequence.clear();
                }
            }
            currentColumn = bottomLeft;
            currentColumn-=(7*(i+1));
            currentSequence.clear();

        }

        //diagonal up ===========================================================================
        currentColumn = 21;

        /// first part of checking diagonally !!
        for (int i = 0; i < 3; i++){

            do{
//                if(currentBoard.getChipType(currentColumn) != empty){
//
//                    if(currentSequence.isEmpty()){
//                        currentSequence.add(currentBoard.getChipType(currentColumn));
//                        currentColumn-=6;
//                    } else {
//                        if (currentBoard.getChipType(currentColumn) == currentPlayer) {
//                            currentSequence.add(currentBoard.getChipType(currentColumn));
//                            currentColumn-=6;
//                            if (currentSequence.size() == comboSize
//                                    && currentBoard.getChipType(currentColumn-6) != currentPlayer) {
//                                counter++;
//                                currentSequence.clear();
//                            }
//                        } else {
//                            currentSequence.clear();
//                            currentSequence.add(currentBoard.getChipType(currentColumn));
//                            currentColumn-=6;
//                        }
//                    }
                if (currentBoard.getChipType(currentColumn) == currentPlayer){
                    currentSequence.add(currentBoard.getChipType(currentColumn));
                    if (currentSequence.size() == comboSize && currentBoard.getChipType(currentColumn-6) != currentPlayer) {
                        counter++;
                        currentSequence.clear();
                    }
                    currentColumn-=6;

                } else {
                    currentSequence.clear();
                    currentColumn-=6;
                }

            } while(currentColumn > 0);

            currentColumn = 21;
            currentColumn+=(7*(i+1));
            currentSequence.clear();

        }

        currentColumn = 36;

        /// second part of checking diagonally !!
        for (int i = 0; i < 3; i++){

            do{
//                if(currentBoard.getChipType(currentColumn) != empty){
//
//                    if(currentSequence.isEmpty()){
//                        currentSequence.add(currentBoard.getChipType(currentColumn));
//                        currentColumn-=6;
//                    } else {
//                        if (currentBoard.getChipType(currentColumn) == currentPlayer) {
//                            currentSequence.add(currentBoard.getChipType(currentColumn));
//                            currentColumn-=6;
//                            if (currentSequence.size() == comboSize
//                                    && currentBoard.getChipType(currentColumn-6) != currentPlayer) {
//                                counter++;
//                                currentSequence.clear();
//                            }
//                        } else {
//                            currentSequence.clear();
//                            currentSequence.add(currentBoard.getChipType(currentColumn));
//                            currentColumn-=6;
//                        }
//                    }
                if (currentBoard.getChipType(currentColumn) == currentPlayer) {
                    currentSequence.add(currentBoard.getChipType(currentColumn));
                    if (currentSequence.size() == comboSize
                                    && currentBoard.getChipType(currentColumn-6) != currentPlayer) {
                        counter++;
                        currentSequence.clear();
                    }
                    currentColumn-=6;
                } else {
                    currentSequence.clear();
                    currentColumn-=6;
                }

            } while(currentColumn > 0);

            currentColumn = 36;
            currentColumn+=(i+1);
            currentSequence.clear();

        }

        //diagonal down ===========================================================================
        //equals 35 because that is the bottom left most position on the board
        currentColumn = 14;

        /// first part of checking diagonally !!
        for (int i = 0; i < 3; i++){

            do{
//                if(currentBoard.getChipType(currentColumn) != empty){
//
//                    if(currentSequence.isEmpty()){
//                        currentSequence.add(currentBoard.getChipType(currentColumn));
//                        currentColumn+=8;
//                    } else {
//                        if (currentBoard.getChipType(currentColumn) == currentPlayer) {
//                            currentSequence.add(currentBoard.getChipType(currentColumn));
//                            currentColumn+=8;
//                            if (currentSequence.size() == comboSize
//                                    && currentBoard.getChipType(currentColumn+8) != currentPlayer) {
//                                counter++;
//                                currentSequence.clear();
//                            }
//                        } else {
//                            currentSequence.clear();
//                            currentSequence.add(currentBoard.getChipType(currentColumn));
//                            currentColumn+=8;
//                        }
//                    }
                if (currentBoard.getChipType(currentColumn) == currentPlayer){
                    currentSequence.add(currentBoard.getChipType(currentColumn));
                    if (currentSequence.size() == comboSize
                                    && currentBoard.getChipType(currentColumn+8) != currentPlayer) {
                        counter++;
                        currentSequence.clear();
                    }
                    currentColumn+=8;

                } else {
                    currentSequence.clear();
                    currentColumn+=8;
                }

            } while(currentColumn < 42);

            currentColumn = 14;
            currentColumn-=(7*(i+1));
            currentSequence.clear();

        }

        currentColumn = 1;

        /// second part of checking diagonally !!
        for (int i = 0; i < 3; i++){

            do{
//                if(currentBoard.getChipType(currentColumn) != empty){
//
//                    if(currentSequence.isEmpty()){
//                        currentSequence.add(currentBoard.getChipType(currentColumn));
//                        currentColumn+=8;
//                    } else {
//                        if (currentBoard.getChipType(currentColumn) == currentSequence.get(currentSequence.size() - 1)) {
//                            currentSequence.add(currentBoard.getChipType(currentColumn));
//                            currentColumn+=8;
//                            if (currentSequence.size() == comboSize
//                                    && currentBoard.getChipType(currentColumn+8) != currentPlayer) {
//                                counter++;
//                                currentSequence.clear();
//                            }
//                        } else {
//                            currentSequence.clear();
//                            currentSequence.add(currentBoard.getChipType(currentColumn));
//                            currentColumn+=8;
//                        }
//                    }
                if (currentBoard.getChipType(currentColumn) == currentPlayer){
                    currentSequence.add(currentBoard.getChipType(currentColumn));
                    if (currentSequence.size() == comboSize
                                    && currentBoard.getChipType(currentColumn+8) != currentPlayer) {
                        counter++;
                        currentSequence.clear();
                    }
                    currentColumn+=8;

                } else {
                    currentSequence.clear();
                    currentColumn+=8;
                }

            } while(currentColumn < 42);

            currentColumn = 1;
            currentColumn+=(i+1);
            currentSequence.clear();

        }

        return counter;
    }







    public int getThreeCombos(ConnectFourBoard connectFourBoard, int currentPlayer) {
        return 0;
    }

    public int getFourCombos(ConnectFourBoard connectFourBoard, int currentPlayer) {

        return 0;
    }
}


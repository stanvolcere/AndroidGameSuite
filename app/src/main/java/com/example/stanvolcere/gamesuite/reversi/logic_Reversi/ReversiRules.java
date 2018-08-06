package com.example.stanvolcere.gamesuite.reversi.logic_Reversi;

import com.example.stanvolcere.gamesuite.reversi.model_Reversi.ReversiBoard;
import com.example.stanvolcere.gamesuite.reversi.tools_Reversi.Pair;

import java.util.ArrayList;

/**
 * Created by stanvolcere on 15/02/2018.
 */

public class ReversiRules {

    Pair helperPair;
    final int black = 1;
    final int white = -1;
    final int empty = 0;

    //helper variables
    int chipHolder;

    int currentXValue;
    int currentYValue;

    //Arraylist will h


    public ReversiRules(){

    }

    public ArrayList<Integer> availablePositions(ReversiBoard board, int position, int player) {

        ArrayList<Integer> listOfChanges = new ArrayList<>();
        if(board.isPositionAvaliable(position)){
            listOfChanges.addAll(horizontalPositions(board, position, player));
            listOfChanges.addAll(verticalPositions(board, position, player));
            listOfChanges.addAll(diagonalPositions(board, position, player));
        }

        return listOfChanges;

    }


    //check all directions (board, position)
    private ArrayList<Integer> horizontalPositions(ReversiBoard board, int position, int player) {
        ArrayList<Integer> listOfChanges = new ArrayList<>();

        //add the horizontal lists
        listOfChanges.addAll(horizontalRightPositions(board, position, player));
        listOfChanges.addAll(horizontalLeftPositions(board, position, player));


        return listOfChanges;
    }

    //check right directions (board, position)
    public ArrayList<Integer> horizontalRightPositions(ReversiBoard board, int position, int player) {

        ArrayList<Integer> listOfChanges = new ArrayList<>();
        helperPair = convertToCoordinate(position);
        int newPosition = position;

        for (int i = helperPair.getFirstElement()+1; i < 8; i++){
            newPosition++;
            if(newPosition < board.getSize()) {
                chipHolder = board.getChipType(newPosition);
                if (chipHolder == player) {
                    return listOfChanges;

                } else if (chipHolder == (player * -1)) {
                    listOfChanges.add(newPosition);

                } else if (chipHolder == empty) {
                    if (listOfChanges.isEmpty()) {
                        return listOfChanges;
                    } else {
                        listOfChanges.clear();
                        return listOfChanges;
                    }
                }
            } else {
                listOfChanges.clear();
                break;
            }

        }
        return listOfChanges;
    }


    //check left directions (board, position)
    public ArrayList<Integer> horizontalLeftPositions(ReversiBoard board, int position, int player) {

        ArrayList<Integer> listOfChanges = new ArrayList<>();
        helperPair = convertToCoordinate(position);
        int newPosition = position;


        for (int i = helperPair.getFirstElement()-1; i >= 0; i--){
            newPosition--;
            if(newPosition >= 0) {
                chipHolder = board.getChipType(newPosition);
                if (chipHolder == player) {
                    return listOfChanges;

                } else if (chipHolder == (player * -1)) {
                    listOfChanges.add(newPosition);

                } else if (chipHolder == empty) {
                    if (listOfChanges.isEmpty()) {
                        return listOfChanges;
                    } else {
                        listOfChanges.clear();
                        return listOfChanges;
                    }
                }
            } else {
                listOfChanges.clear();
                break;
            }

        }
        return listOfChanges;
    }



    public ArrayList<Integer> verticalPositions(ReversiBoard board, int position, int player) {
        ArrayList<Integer> listOfChanges = new ArrayList<>();

        listOfChanges.addAll(verticalDownPositions(board, position, player));
        listOfChanges.addAll(verticalUpPositions(board, position, player));

        return listOfChanges;

    }

    //check right directions (board, position)
    public ArrayList<Integer> verticalDownPositions(ReversiBoard board, int position, int player) {

        ArrayList<Integer> listOfChanges = new ArrayList<>();
        helperPair = convertToCoordinate(position);
        int newPosition = position;

        for (int i = helperPair.getSecondElement(); i < 8; i++){
            newPosition= newPosition + 8;
            if (newPosition < board.getSize()){
                chipHolder = board.getChipType(newPosition);
                if(chipHolder == player){
                    return listOfChanges;

                } else if (chipHolder == (player*-1)){
                    listOfChanges.add(newPosition);

                } else if (chipHolder == empty) {
                    if(listOfChanges.isEmpty()){
                        return listOfChanges;
                    } else {
                        listOfChanges.clear();
                        return listOfChanges;
                    }
                }
            } else {
                listOfChanges.clear();
                break;
            }


        }
        return listOfChanges;
    }


    //check left directions (board, position)
    public ArrayList<Integer> verticalUpPositions(ReversiBoard board, int position, int player) {

        ArrayList<Integer> listOfChanges = new ArrayList<>();
        helperPair = convertToCoordinate(position);
        int newPosition = position;

        for (int i = helperPair.getSecondElement(); i >= 0; i++){
            newPosition= newPosition - 8;
            if(newPosition >= 0) {

                chipHolder = board.getChipType(newPosition);
                if (chipHolder == player) {
                    return listOfChanges;

                } else if (chipHolder == (player * -1)) {
                    listOfChanges.add(newPosition);

                } else if (chipHolder == empty) {
                    if (listOfChanges.isEmpty()) {
                        return listOfChanges;
                    } else {
                        listOfChanges.clear();
                        return listOfChanges;
                    }
                }
            } else {
                listOfChanges.clear();
                break;
            }

        }
        return listOfChanges;
    }


    ////
    public ArrayList<Integer> diagonalPositions(ReversiBoard board, int position, int player) {

        ArrayList<Integer> listOfChanges = new ArrayList<>();

        listOfChanges.addAll(upRightPositions(board, position, player));
        listOfChanges.addAll(upLeftPositions(board, position, player));

        listOfChanges.addAll(downRightPositions(board, position, player));
        listOfChanges.addAll(downLeftPositions(board, position, player));

        return listOfChanges;

    }

    //check left directions (board, position)
    //  ^  --->
    //  |
    //  |

    public ArrayList<Integer> upRightPositions(ReversiBoard board, int position, int player) {

        ArrayList<Integer> listOfChanges = new ArrayList<>();
        helperPair = convertToCoordinate(position);
        int newPosition = position;

        currentXValue = helperPair.getFirstElement();
        currentYValue = helperPair.getSecondElement();

        if(currentYValue-1 >= 0 && currentXValue+1 < 8) {
            do {
                newPosition = newPosition - 7;
                if (newPosition >= 0) {
                    chipHolder = board.getChipType(newPosition);
                    if (chipHolder == player) {
                        return listOfChanges;

                    } else if (chipHolder == (player * -1)) {
                        listOfChanges.add(newPosition);

                    } else if (chipHolder == empty) {
                        if (listOfChanges.isEmpty()) {
                            return listOfChanges;
                        } else {
                            listOfChanges.clear();
                            return listOfChanges;
                        }
                    }

                    currentXValue++;
                    currentYValue--;

                    //condition to check the edges!!!!!
                    if (!areNewPositionsLegalOnBoard(currentXValue, currentYValue)) {
                        if (listOfChanges.isEmpty()) {
                            return listOfChanges;
                        } else {
                            listOfChanges.clear();
                            return listOfChanges;
                        }
                    }
                } else {
                    listOfChanges.clear();
                    break;
                }

            } while (currentYValue >= 0 && currentXValue < 8);
        }
        return listOfChanges;
    }

    public ArrayList<Integer> upLeftPositions(ReversiBoard board, int position, int player) {

        ArrayList<Integer> listOfChanges = new ArrayList<>();
        helperPair = convertToCoordinate(position);
        int newPosition = position;

        currentXValue = helperPair.getFirstElement();
        currentYValue = helperPair.getSecondElement();

        if(currentYValue-1 >= 0 && currentXValue-1 >= 0) {
            do {
                newPosition = newPosition - 9;
                if (newPosition >= 0) {
                    chipHolder = board.getChipType(newPosition);
                    if (chipHolder == player) {
                        return listOfChanges;

                    } else if (chipHolder == (player * -1)) {
                        listOfChanges.add(newPosition);

                    } else if (chipHolder == empty) {
                        if (listOfChanges.isEmpty()) {
                            return listOfChanges;
                        } else {
                            listOfChanges.clear();
                            return listOfChanges;
                        }
                    }

                    currentXValue--;
                    currentYValue--;

                    //condition to check the edges!!!!!
                    if (!areNewPositionsLegalOnBoard(currentXValue, currentYValue)) {
                        if (listOfChanges.isEmpty()) {
                            return listOfChanges;
                        } else {
                            listOfChanges.clear();
                            return listOfChanges;
                        }
                    }
                } else {
                    listOfChanges.clear();
                    break;
                }

            } while (currentYValue >= 0 && currentXValue >= 0);
        }
        return listOfChanges;
    }

    public ArrayList<Integer> downRightPositions(ReversiBoard board, int position, int player) {

        ArrayList<Integer> listOfChanges = new ArrayList<>();
        helperPair = convertToCoordinate(position);
        int newPosition = position;

        currentXValue = helperPair.getFirstElement();
        currentYValue = helperPair.getSecondElement();

        if(currentYValue+1 < 8 && currentXValue+1 < 8) {

            do {
                newPosition = newPosition + 9;

                if (newPosition < board.getSize()) {
                    chipHolder = board.getChipType(newPosition);
                    if (chipHolder == player) {
                        return listOfChanges;

                    } else if (chipHolder == (player * -1)) {
                        listOfChanges.add(newPosition);

                    } else if (chipHolder == empty) {
                        if (listOfChanges.isEmpty()) {
                            return listOfChanges;
                        } else {
                            listOfChanges.clear();
                            return listOfChanges;
                        }
                    }

                    currentXValue++;
                    currentYValue++;

                    //condition to check the edges!!!!!
                    if (!areNewPositionsLegalOnBoard(currentXValue, currentYValue)) {
                        if (listOfChanges.isEmpty()) {
                            return listOfChanges;
                        } else {
                            listOfChanges.clear();
                            return listOfChanges;
                        }
                    }
                } else {
                    listOfChanges.clear();
                    break;
                }

            } while (currentYValue < 8 && currentXValue < 8);
        }
        return listOfChanges;
    }

    public ArrayList<Integer> downLeftPositions(ReversiBoard board, int position, int player) {

        ArrayList<Integer> listOfChanges = new ArrayList<>();
        helperPair = convertToCoordinate(position);
        int newPosition = position;

        currentXValue = helperPair.getFirstElement();
        currentYValue = helperPair.getSecondElement();

        if(currentYValue+1 < 8 && currentXValue-1 >= 0) {
            do {
                newPosition = newPosition + 7;

                if (newPosition < board.getSize()) {
                    chipHolder = board.getChipType(newPosition);
                    if (chipHolder == player) {
                        return listOfChanges;

                    } else if (chipHolder == (player * -1)) {
                        listOfChanges.add(newPosition);

                    } else if (chipHolder == empty) {
                        if (listOfChanges.isEmpty()) {
                            return listOfChanges;
                        } else {
                            listOfChanges.clear();
                            return listOfChanges;
                        }
                    }

                    currentXValue--;
                    currentYValue++;

                    //condition to check the edges!!!!!
                    if (!areNewPositionsLegalOnBoard(currentXValue, currentYValue)) {
                        if (listOfChanges.isEmpty()) {
                            return listOfChanges;
                        } else {
                            listOfChanges.clear();
                            return listOfChanges;
                        }
                    }
                } else {
                    listOfChanges.clear();
                    break;
                }

            } while (currentYValue < 8 && currentXValue >= 0);
        }
        return listOfChanges;
    }



    ////////////////===============================================================================
    private boolean areNewPositionsLegalOnBoard(int currentXValue, int currentYValue) {

        if (currentXValue < 0
                || currentXValue > 8
                || currentYValue < 0
                || currentYValue > 8){
            return  false;
        } else {
            return true;
        }
    }

    //convert position to coordinates
    public Pair convertToCoordinate(int position) {
        return new Pair((position%8), (position/8));
    }



}

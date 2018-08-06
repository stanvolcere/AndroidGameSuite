package com.example.stanvolcere.gamesuite.reversi.logic_Reversi;

import com.example.stanvolcere.gamesuite.reversi.model_Reversi.ReversiBoard;
import com.example.stanvolcere.gamesuite.ui.ReversiActivity;

import java.util.ArrayList;

/**
 * Created by stanvolcere on 15/02/2018.
 */

public class AlphaBeta {

    final private ReversiRules reversiRules = new ReversiRules();
    final private ReversiActivity game = new ReversiActivity();
    final private int humanPlayer = 1;
    private int maxDepth;

    public AlphaBeta(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public int performAlphaBeta(ReversiBoard currentBoard, int currentPlayer, int lastMove, int alpha, int beta, int depth) {
        int chosenMove;
        ArrayList<Integer>  availablePositions = new ArrayList<>();
        ArrayList<Integer>  listOfChanges = new ArrayList<>();

//        int bestValue;
//        int bestMove;

        //availblePositions is an array list with positions = key and Array of all changes it causes
        // an ArrayList object
        availablePositions = getAvaliablePostions(currentBoard, currentPlayer);

        if (depth == 0){
            return currentBoard.evaluate(currentPlayer);
        }

        if (availablePositions.isEmpty() && currentBoard.checkIfBoardIsFull()){
            return currentBoard.terminalEvaluation(currentPlayer);
        }

        //check that the AI is the one making the move
        if (currentPlayer != humanPlayer) {

//            bestValue = -10000;
            int bestMove = 0;

            if(!availablePositions.isEmpty()) {
                for (int move : availablePositions) {

                    //make changes to board depending on position
                    ReversiBoard newBoard = currentBoard.cloneBoard(currentBoard);
                    listOfChanges.add(move);
                    listOfChanges = reversiRules.availablePositions(newBoard, move, currentPlayer);

                    newBoard.updateBoard(listOfChanges, currentPlayer);

                    //switch player
                    currentPlayer = currentPlayer * -1;

                    //call new AlphaBeta with new board states
                    int value = performAlphaBeta(newBoard, currentPlayer, move, alpha, beta, depth - 1);

                    //getting best value from evaluations
                    if (value > alpha) {
                        alpha    = value;
                        bestMove = move;
                    }

//                    bestValue = returnMax(bestValue, bestMoveAndValue.getSecondElement());
//
//
//                    //updating our alpha
//                    alpha = returnMax(bestValue, alpha);

                    //pruning
                    if (alpha >= beta) {
                        break;
                    }
                }

                if (depth == maxDepth){
                    return bestMove;
                }

                return alpha;

            } else {
                //switch player
                currentPlayer = currentPlayer * -1;

                //forces to pass to min player
                int value = performAlphaBeta(currentBoard, currentPlayer, lastMove, alpha, beta, depth - 1);

                if (value < beta){
                    beta        = value;
                    //bestMove    = lastMove;
                }

                return beta;

            }

            //return new Pair(bestMove,bestValue);

            //below is what is performed by the opponent (human player)
        } else {

            //bestValue = 10000;
            //int bestMove  = 0;

            if(!availablePositions.isEmpty()) {
                for (int move : availablePositions) {

                    //make changes to board depending on position
                    ReversiBoard newBoard = currentBoard.cloneBoard(currentBoard);
                    listOfChanges.add(move);
                    listOfChanges = reversiRules.availablePositions(newBoard, move, currentPlayer);

                    newBoard.updateBoard(listOfChanges, currentPlayer);

                    //switch player
                    currentPlayer = currentPlayer * -1;

                    //call new AlphaBeta with new board states
                    int value = performAlphaBeta(newBoard, currentPlayer, move, alpha, beta, depth - 1);

                    //getting best value from evaluations
                    if (value < beta){
                        beta        = value;
                    }

                    if (alpha >= beta) {
                        break;
                    }
                }
                return beta;


            } else {
                //switch player
                currentPlayer = currentPlayer * -1;

                //forces to pass to min player
                int value = performAlphaBeta(currentBoard, currentPlayer, lastMove, alpha, beta, depth - 1);

                if (value > alpha) {
                    alpha    = value;
                }

                //pruning
                return alpha;
            }

        }

    }

    private ArrayList<Integer> getAvaliablePostions(ReversiBoard currentBoard, int currentPlayer) {
        ArrayList<Integer>  availablePositions = new ArrayList<>();
        //ArrayList<ArrayList<Integer>>  = new ArrayList<>(64);

        for(int i = 0; i < currentBoard.getSize(); i++){
            //changesCaused = reversiRules.availablePositions(currentBoard, i, currentPlayer);
            if (!reversiRules.availablePositions(currentBoard, i, currentPlayer).isEmpty()){
                availablePositions.add(i);
            }
        }

        return availablePositions;
    }

    private int returnMin(int num1, int num2) {
        if (num1 < num2){
            return num1;
        } else {
            return num2;
        }
    }

    private int returnMax(int num1, int num2) {
        if (num1 > num2){
            return num1;
        } else {
            return num2;
        }
    }

}

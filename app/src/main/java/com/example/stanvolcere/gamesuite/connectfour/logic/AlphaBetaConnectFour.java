package com.example.stanvolcere.gamesuite.connectfour.logic;

import com.example.stanvolcere.gamesuite.connectfour.model.ConnectFourBoard;
import com.example.stanvolcere.gamesuite.reversi.logic_Reversi.ReversiRules;
import com.example.stanvolcere.gamesuite.reversi.model_Reversi.ReversiBoard;

import java.util.ArrayList;

/**
 * Created by stanvolcere on 07/03/2018.
 */

public class AlphaBetaConnectFour {

    private int maxDepth;

    final private int humanPlayer = 1;


    public AlphaBetaConnectFour(int depth) {
        maxDepth = depth;
    }

    public int performAlphaBeta(ConnectFourBoard currentBoard, int currentPlayer, int alpha, int beta, int depth) {

        ArrayList<Integer> availablePositions = currentBoard.getAvailablePositions();

        if(depth == 0){
            return currentBoard.evaluate(currentPlayer);
        }

//        if(availablePositions.isEmpty()){
//            currentBoard.terminalEvaluation();
//        }

        if(currentPlayer != humanPlayer) {
            int bestMove = 0;

            for (int move : availablePositions) {

                if (move >= 0) {

                    //clone the current board
                    ConnectFourBoard newBoard = currentBoard.cloneBoard();

                    //add new position to the board
                    newBoard.addNewChip(move, currentPlayer);

                    //switch player
                    currentPlayer = currentPlayer * -1;

                    //call new AlphaBeta with new board states
                    int value = performAlphaBeta(newBoard, currentPlayer, alpha, beta, depth - 1);

                    //getting best value from evaluations
                    if (value > alpha) {
                        alpha = value;
                        bestMove = move;
                    }

                    if (alpha >= beta) {
                        break;
                    }
                }

            }

            if (depth == maxDepth) {
                return bestMove;
            }

            return alpha;

//            } else {
//                //switch player
//                currentPlayer = currentPlayer * -1;
//
//                //forces to pass to min player
//                int value = performAlphaBeta(currentBoard, currentPlayer, alpha, beta, depth - 1);
//
//                if (value < beta){
//                    beta        = value;
//                    //bestMove    = lastMove;
//                }
//
//                return beta;
//            }
        } else {
//            if(!availablePositions.isEmpty()) {

            for (int move : availablePositions) {

                if(move >= 0) {
                    //clone the current board
                    ConnectFourBoard newBoard = currentBoard.cloneBoard();

                    //add new position to the board
                    newBoard.addNewChip(move, currentPlayer);

                    //switch player
                    currentPlayer = currentPlayer * -1;

                    //call new AlphaBeta with new board states
                    int value = performAlphaBeta(newBoard, currentPlayer, alpha, beta, depth - 1);

                    //getting best value from evaluations
                    if (value < beta) {
                        beta = value;
                    }

                    if (alpha >= beta) {
                        break;
                    }
                }
            }
            return beta;

//            } else {
//                //switch player
//                currentPlayer = currentPlayer * -1;
//
//                //forces to pass to min player
//                int value = performAlphaBeta(currentBoard, currentPlayer, alpha, beta, depth - 1);
//
//                if (value > alpha) {
//                    alpha    = value;
//                }
//
//                //pruning
//                return alpha;
//            }
        }
    }
}

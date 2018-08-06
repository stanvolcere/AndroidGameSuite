package com.example.stanvolcere.gamesuite.connectfour.logic;

import com.example.stanvolcere.gamesuite.connectfour.model.ConnectFourBoard;

import com.example.stanvolcere.gamesuite.ui.ConnectFourActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by stanvolcere on 23/03/2018.
 */

public class AlternativeAI {

    ConnectFourRules rules = new ConnectFourRules();
    Random rand = new Random();
    int n;

    public AlternativeAI() {
    }

    public int generateComputerMove(ConnectFourBoard board, int currentPlayer){

        ArrayList<Integer> movesList = board.getAvailablePositions();
        int bestMove = 0;
        int max = 0;

        for (int move: movesList){
            ConnectFourBoard newBoard = board.cloneBoard();
            newBoard.addNewChip(move, currentPlayer);

            if (rules.checkForWinner(newBoard) != 0){
                return move;
            }

        }

        for (int move: movesList){
            ConnectFourBoard newBoard = board.cloneBoard();
            newBoard.addNewChip(move, -currentPlayer);

            if (rules.checkForWinner(newBoard) != 0){
                return move;
            }

        }

        for (int move: movesList){
            ConnectFourBoard newBoard = board.cloneBoard();
            newBoard.addNewChip(move, currentPlayer);

            if ((rules.checkCombo(newBoard,currentPlayer,3)*5)
                    + (rules.checkCombo(newBoard,currentPlayer,2)*3)
                    + newBoard.getBoardValue(move)
                    > max){
                bestMove = move;
            }
        }


        return bestMove;

    }



}

package com.example.stanvolcere.gamesuite.reversi.tools_Reversi;

import com.example.stanvolcere.gamesuite.reversi.model_Reversi.ReversiBoard;

/**
 * Created by stanvolcere on 20/02/2018.
 */

public class Triple {

    int x;
    int y;
    int z;

    ReversiBoard board;
    int position;
    int player;

    public Triple(ReversiBoard x, int y, int z) {
        board       = x;
        position    = y;
        player      = z;
    }

    public int getFirstElement() {
        return x;
    }

    public void setFirstElement(int x) {
        this.x = x;
    }

    public int getSecondElement() {
        return y;
    }

    public void setSecondElement(int y) {
        this.y = y;
    }

    public int getThirdElement() {
        return z;
    }

    public void setThirdElement(int z) {
        this.z = z;
    }
}

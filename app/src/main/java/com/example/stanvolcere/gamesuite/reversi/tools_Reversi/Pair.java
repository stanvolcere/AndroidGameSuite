package com.example.stanvolcere.gamesuite.reversi.tools_Reversi;


/**
 * Created by Stan Wayne Volcere on 11/8/2017.
 */

public class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
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
}


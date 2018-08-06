package com.example.stanvolcere.gamesuite.reversi.tools_Reversi;

import android.os.Looper;

/**
 * Created by stanvolcere on 21/02/2018.
 */



public class ReversiRulesThread extends Thread {

    public ReversiRulesHandler mHandler;

    @Override
    public void run() {
        Looper.prepare();
        //handler is automatically associated to the looper that it close to
        mHandler = new ReversiRulesHandler();
        Looper.loop();
    }


}

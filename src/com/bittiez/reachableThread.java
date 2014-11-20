package com.bittiez;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by tad on 11/20/2014.
 */
public class reachableThread implements Runnable {
    public InetAddress IA;
    int timeout = 0;
    public reachableThread(InetAddress _IA, int _timeout){
        IA = _IA;
        timeout = _timeout;
    }
    @Override
    public void run() {
        try {
            if (IA.isReachable(timeout)) {
                Main.print(IA.getHostAddress() + " [" + IA.getHostName() + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

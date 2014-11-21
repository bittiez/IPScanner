package com.bittiez;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by tad on 11/20/2014.
 */
public class reachableThread implements Runnable {
    public InetAddress IA;
    int timeout = 0;
    ListEm le = null;
    public reachableThread(InetAddress _IA, int _timeout, ListEm _le){
        IA = _IA;
        timeout = _timeout;
        le = _le;
    }
    @Override
    public void run() {
        try {
            if (IA.isReachable(timeout)) {
                String e = IA.getHostAddress() + " [" + IA.getHostName() + "]";
                Main.print(e);
                le.AddItem(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

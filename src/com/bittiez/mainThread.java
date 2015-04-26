package com.bittiez;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tad on 11/21/2014.
 */
public class mainThread implements Runnable {
    ListEm le = null;
    int MaxIP = 254;
    int ThreadCount = 10;
    public mainThread(ListEm _le){
        le = _le;
    }
    @Override
    public void run() {
        try {
            Main.print("Searching for devices..");
            int timeout = 5000;
            int i = 0;

            final String subnet = InetAddress.getLocalHost().getHostAddress().substring(0, InetAddress.getLocalHost().getHostAddress().lastIndexOf("."));
            String host;
            InetAddress IA;
            ArrayList<Thread> threads = new ArrayList<Thread>();

//            while(i <= MaxIP){
//                for (int j = 0; j < ThreadCount; j++) {
//                    if(i > MaxIP)
//                        break;
//                    host = subnet + "." + i;
//                    Thread one = new Thread(new reachableThread(InetAddress.getByName(host), timeout, le));
//                    i++;
//                    one.start();
//                    threads.add(one);
//                }
//                Main.print("Set " + (i-ThreadCount) + "-" + i);
//                for (int j = 0; j < threads.size(); j++) {
//                    threads.get(j).join();
//                }
//
//                threads.clear();
//            }

            new Thread(new pingerThread(le)).start();

            //Main. print("Finished");
            //le.Finished();


        } catch (Exception e){
            //print(e.toString());
        }

    }


}

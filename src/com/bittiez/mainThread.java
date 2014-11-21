package com.bittiez;

import java.net.InetAddress;
import java.util.ArrayList;

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

            String subnet = InetAddress.getLocalHost().getHostAddress();
            subnet = subnet.substring(0, subnet.lastIndexOf("."));
            String host;
            InetAddress IA;
            ArrayList<Thread> threads = new ArrayList<Thread>();

            while(i <= MaxIP){
                for (int j = 0; j < ThreadCount; j++) {
                    if(i > MaxIP)
                        break;
                    host = subnet + "." + i;
                    Thread one = new Thread(new reachableThread(InetAddress.getByName(host), timeout, le));
                    i++;
                    one.start();
                    threads.add(one);
                }
                Main.print("Set " + (i-ThreadCount) + "-" + i);
                for (int j = 0; j < threads.size(); j++) {
                    threads.get(j).join();
                }

                threads.clear();
            }
            Main. print("Finished");
            le.Finished();


        } catch (Exception e){
            //print(e.toString());
        }

    }
}

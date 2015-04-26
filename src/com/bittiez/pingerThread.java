package com.bittiez;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by tad on 4/26/2015.
 */
public class pingerThread implements Runnable {
    public ListEm le;
    public pingerThread(ListEm le){
        this.le = le;
    }

    @Override
    public void run() {
        int timeout = 5000;
        int MaxIP = 254;
        String iAdd = null;
        try {
            iAdd = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        final String subnet = iAdd.substring(0, iAdd.lastIndexOf("."));

        ArrayList<IP> ips = new ArrayList<>();
        ArrayList<FutureTask> fts = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int j = 0; j <= MaxIP; j++) {
            try {
                ips.add(new IP(InetAddress.getByName(subnet + "." + j), le));
                fts.add(new FutureTask<>(ips.get(j)));
                executor.submit(fts.get(j));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }





//        while(i <= MaxIP){
//            executorService.submit(new Runnable() {
//                public void run() {
//                    try {
//                        InetAddress IA = InetAddress.getByName(subnet + "." + i);
//                        if (IA.isReachable(timeout)) {
//                            String e = IA.getHostAddress() + " [" + IA.getHostName() + "]";
//                            Main.print(e);
//                            le.AddItem(e);
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {

        }

        le.Finished();
        //executorService.shutdown();
    }
}

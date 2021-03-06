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
public class PingerThread implements Runnable {
    public ListEm le;
    public PingerThread(ListEm le){
        this.le = le;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

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
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*3);
        Main.print(String.valueOf(Runtime.getRuntime().availableProcessors()));
        for (int j = 0; j <= MaxIP; j++) {
            try {
                ips.add(new IP(InetAddress.getByName(subnet + "." + j), le));
                fts.add(new FutureTask<>(ips.get(j)));
                executor.submit(fts.get(j));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {

        }

        long totalTime = System.currentTimeMillis() - startTime;
        le.Finished(totalTime);
    }
}

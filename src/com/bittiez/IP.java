package com.bittiez;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.Callable;

/**
 * Created by tad on 4/26/2015.
 */
public class IP implements Callable<Boolean>{
    public InetAddress inetAddress;
    public int timeout = 10000;
    public ListEm le;
    public IP(InetAddress inetAddress, ListEm le){
        this.inetAddress = inetAddress;
        this.le = le;
    }

    public boolean isReachable(){
        try {
            return inetAddress.isReachable(timeout);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String returnAsString(){
        return inetAddress.getHostAddress() + " [" + inetAddress.getHostName() + "]";
    }

    @Override
    public Boolean call() throws Exception {
        Main.print(returnAsString());
        if(isReachable()){
            le.AddItem(returnAsString());
            return true;
        }
        return false;
    }
}

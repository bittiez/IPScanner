package com.bittiez;

import sun.net.util.IPAddressUtil;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            print("Searching for devices..");
            int timeout = 5000;
            int i = 0;

            String subnet = InetAddress.getLocalHost().getHostAddress();
            subnet = subnet.substring(0, subnet.lastIndexOf("."));
            String host;
            InetAddress IA;
            ArrayList<Thread> threads = new ArrayList<Thread>();

            //print(InetAddress.getLocalHost().getHostAddress() + "[" + InetAddress.getLocalHost().getHostName() + "]");

            while(i < 255){
                for (int j = 0; j < 10; j++) {
                    host = subnet + "." + i;
                    //print("   -->" + host);
                    Thread one = new Thread(new reachableThread(InetAddress.getByName(host), timeout));
                    i++;
                    one.start();
                    threads.add(one);
                }

                for (int j = 0; j < threads.size(); j++) {
                    threads.get(j).join();
                }

                threads.clear();
            }


        } catch (Exception e){
            //print(e.toString());
        }
    }

    public static void print(String data){

        System.out.println(data);
    }
}

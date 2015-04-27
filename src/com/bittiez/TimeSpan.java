package com.bittiez;

/**
 * Created by tad on 4/27/2015.
 */
public class TimeSpan {
    private long totalMilliseconds = 0;

    public long milliseconds = 0;
    public long seconds = 0;
    public long minutes = 0;
    public long hours = 0;
    public long days = 0;


    private static long MILLISECONDS_PER_DAY = 86400000;
    private static long MILLISECONDS_PER_HOUR = 3600000;
    private static long MILLISECONDS_PER_MINUTE = 60000;
    private static long MILLISECONDS_PER_SECOND = 1000;

    public TimeSpan(long milliseconds){
        totalMilliseconds = milliseconds;
        calculate();
    }

    public void calculate(){
        long currentNum = totalMilliseconds;
        while(currentNum >= MILLISECONDS_PER_DAY){
            days++;
            currentNum-=MILLISECONDS_PER_DAY;
        }

        while(currentNum >= MILLISECONDS_PER_HOUR){
            hours++;
            currentNum-=MILLISECONDS_PER_HOUR;
        }

        while(currentNum >= MILLISECONDS_PER_MINUTE){
            minutes++;
            currentNum-=MILLISECONDS_PER_MINUTE;
        }

        while(currentNum >= MILLISECONDS_PER_SECOND){
            seconds++;
            currentNum-=MILLISECONDS_PER_SECOND;
        }

        milliseconds = currentNum;
    }



}

package com.bittiez;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by tad on 11/20/2014.
 */
public class ListEm {
    private JPanel panel1;
    private JLabel Status;
    private JPanel MainPanel;
    public JFrame frame = null;
    private ArrayList<JTextField> fields = null;


    public ListEm() {
        fields = new ArrayList<JTextField>();
        frame = new JFrame("Network Devices");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Status.setText("Searching...");

        new Thread(new PingerThread(this)).start();

        MainPanel.setLayout(new GridLayout(0, 1));
        frame.setPreferredSize(new Dimension(250, 500));
        frame.pack();
        frame.setVisible(true);
    }

    public void AddItem(String data){
        JTextField jf = new JTextField(data);
        fields.add(jf);
        MainPanel.add(jf);
        MainPanel.updateUI();
    }

    public void Finished(long time){
        TimeSpan ts = new TimeSpan(time);
        String elapsedTime = ts.hours + ":" + ts.minutes + ":" + ts.seconds + "." + ts.milliseconds;
        Status.setText("Finished Searching("+elapsedTime+").");
    }
}

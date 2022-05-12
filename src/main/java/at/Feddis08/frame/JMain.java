package at.Feddis08.frame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.JApplet;
import javax.swing.JFrame;

public class JMain extends JApplet {
    public static ArrayList<ArrayList<String>> objectsToRender = new ArrayList<>();
    public static ArrayList<ArrayList<String>> objectsReadyToRender = new ArrayList<>();
    public static Integer tick = 0;
    public void init() {
        setBackground(Color.white);
        setForeground(Color.white);
    }
    public static ArrayList<String> getObjectByName(String name){
        Integer count = objectsToRender.size() - 1;
        Integer index = 0;
        ArrayList<String> object = new ArrayList<>();
        while (index <= count){
            if (name.equals(objectsToRender.get(index).get(0))){
                object = objectsToRender.get(index);
            }
            index = index + 1;
        }
        return object;
    }

    public static void addToRenderer(ArrayList<ArrayList<String>> dataLists){
        Integer index = 0;
        Integer count = dataLists.size() - 1;
        Integer a = 0;
        while (index <= count) {
            if (!(a.equals(dataLists.get(index).size()))){
                objectsReadyToRender.add(dataLists.get(index));
            }
            index = index + 1;
        }
    }
    public void paint(Graphics g) {
        try {
            Thread.sleep(182);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Graphics2D g2 = (Graphics2D) g;
        repaint();
        Integer index2 = 0;
        Integer count = objectsReadyToRender.size() - 1;
        while (index2 <= count){
            objectsToRender.add(objectsReadyToRender.get(index2));
            index2 = index2 + 1;
        }
        objectsReadyToRender = new ArrayList<>();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Integer index3 = 0;
        for(ArrayList<String> dataList : objectsToRender){
            Boolean stop = false;
            Boolean isFirstFrame = false;
            if (objectsToRender.get(index3).get(5).equals(objectsToRender.get(index3).get(4))) objectsToRender.get(index3).set(5, "0");
            if (objectsToRender.get(index3).get(5).equals("0")) isFirstFrame = true;
            Integer countFrames = Integer.parseInt(objectsToRender.get(index3).get(5)) + 1;
            objectsToRender.get(index3).set(5, countFrames.toString());
            Integer currentIndex = ((countFrames * Integer.parseInt(objectsToRender.get(index3).get(2)) * Integer.parseInt(objectsToRender.get(index3).get(3))) * 2) + 7;
            System.out.println(isFirstFrame + " d " + currentIndex);
            if (index3.equals(0)){
                if (tick.equals(1)){
                    g2.setPaint(Color.WHITE);
                    g2.fill(new Rectangle2D.Double(0, 0, 800, 600));
                    tick = 0;
                }else{
                    tick = tick + 1;
                }
            }
            index3 = index3 + 1;
            Integer a = index3 - 1;
            Integer index = 0;
            Boolean isColor = false;
            int scale = 0;
            int x = 5;
            int y = 7;
            for (String value : dataList) {
                index = index + 1;
                if (index.equals(2)) scale = Integer.parseInt(value);
                if (index > currentIndex || isFirstFrame && index > 7) {
                    if (index.equals(Integer.parseInt(objectsToRender.get(a).get(2)) + countFrames)) stop = true;
                    if (!(stop)) {
                        if (isColor) {
                            isColor = false;
                            if (!(value.equals("0"))) {
                                g2.setPaint(Color.decode(value));
                                g2.fill(new Rectangle2D.Double(x, y, scale, scale));
                            }
                        } else {
                            isColor = true;
                            String[] coords = value.split(":");
                            x = Integer.parseInt(coords[0]);
                            y = Integer.parseInt(coords[1]);
                        }
                }
                }
            }
        }
    }
    public static void run() {
        JFrame f = new JFrame("");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        JApplet applet = new JMain();
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setSize(new Dimension(800, 600));
        f.setMinimumSize(new Dimension(800, 600));
        f.setMaximumSize(new Dimension(800, Integer.MAX_VALUE));
        f.show();
    }
}
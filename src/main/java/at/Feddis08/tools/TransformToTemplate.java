package at.Feddis08.tools;

import at.Feddis08.frame.JMain;

import java.util.ArrayList;

public class TransformToTemplate {
    public static ArrayList<String> dataList = new ArrayList<>();
    public static ArrayList<String> convertToDataList(Integer x, Integer y, ArrayList<String> rawData){
        dataList = new ArrayList<>();
        Integer index = 0;
        String name = "";
        Integer scale = 0;
        Integer hight = 0;
        Integer width = 0;
        Integer frames = 0;
        Integer frameCount = 0;
        Boolean isLooped = false;
        Boolean stopped = false;
        for(String value : rawData) {
            index = index + 1;
            if (index.equals(1)) {
                name = value;
                Integer index2 = 0;
                Integer count = JMain.objectsToRender.size() - 1;
                while (index2 <= count) {
                    if (JMain.objectsToRender.get(index2).get(0).equals(name)) {
                        System.out.println("Your image is already in the system. Please rename it or type 'help' for more information!");
                        stopped = true;
                    }
                    index2 = index2 + 1;
                }
            }
            if (!(stopped)) {
                if (index.equals(2)) scale = Integer.parseInt(value);
                if (index.equals(3)) hight = Integer.parseInt(value);
                if (index.equals(4)) width = Integer.parseInt(value);
                if (index.equals(5)) frames = Integer.parseInt(value);
                if (index.equals(7)){
                    if (value.equals("1")){
                        isLooped = true;
                    }else{
                        isLooped = false;
                    }
                    dataList.add(name);
                    dataList.add(scale.toString());
                    dataList.add(hight.toString());
                    dataList.add(width.toString());
                    dataList.add(frames.toString());
                    dataList.add(frameCount.toString());
                    dataList.add(isLooped.toString());
                }
                if (index > 7) {
                    scannAndPushData(width, value, x, y, index, scale);
                }
            }
        }
        return dataList;
    }
    public static void scannAndPushData(Integer width, String value, Integer x, Integer y, Integer index, Integer scale){
        String[] row = value.split("-");
        int i = 0;
        Integer count = width - 1;
        while (i <= count){
            Integer x2 = x + (i * scale);
            Integer y2 = y + (index * scale);
            dataList.add(Integer.toString(x2) + ":" + Integer.toString(y2));
            dataList.add(row[i]);
            i = i + 1;

        }
    }
}

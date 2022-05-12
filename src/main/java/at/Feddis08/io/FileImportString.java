package at.Feddis08.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileImportString {
    public static ArrayList<ArrayList<String>> cachedRawData = new ArrayList<>();
    public static ArrayList<String> getCachedData(String name){
        Integer count = cachedRawData.size() - 1;
        Integer index = 0;
        ArrayList<String> object = new ArrayList<>();
        while (index <= count){
            if (name.equals(cachedRawData.get(index).get(0))){
                object = cachedRawData.get(index);
            }
            index = index + 1;
        }
        return object;
    }
    public static ArrayList<String> getFile(String path) throws IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        ArrayList<String> st1 = new ArrayList<>();
        while ((st = br.readLine()) != null) Objects.requireNonNull(st1).add(st);
        Integer count = cachedRawData.size() - 1;
        Integer index = 0;
        if (index.equals(cachedRawData.size())) cachedRawData.add(st1);
        while (index <= count){
            if (!(st1.get(0).equals(cachedRawData.get(index).get(0)))){
                cachedRawData.add(st1);
            }
            index = index + 1;
        }
        return st1;
    }
}

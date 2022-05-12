package at.Feddis08.init;
import at.Feddis08.frame.JMain;
import at.Feddis08.io.FileImportString;
import at.Feddis08.tools.TransformToTemplate;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Boot {
    public static void main(String[] args) throws IOException {
        System.out.println("");
        System.out.println("The PaintEngine");
        System.out.println("written by Feddis08");
        System.out.println("");
        //JInit.start();
        Boolean stop = true;
        JMain.run();
        while (stop){
            Scanner input = new Scanner(System.in);
            System.out.println("Type your command. If you need help type 'help'. Type 'x' to quit: ");
            String answer = input.nextLine();
            String[] commands = answer.split("-");
            if (commands[0].equals("x")){
                System.out.println("Stop");
                System.exit(0);
            }
            if (commands[0].equals("help")){
                System.out.println("Commands: ");
                System.out.println("-------------------------------------");
                System.out.println("-import");
                System.out.println("You can import an image/animation");
                System.out.println("//import-{{path}}-{{x}}-{{y}}");
                System.out.println("//import-/home/data/image.txt-100-100");
                System.out.println("-------------------------------------");
                System.out.println("-copy");
                System.out.println("You can copy an image/animation by the name of it and paste it on a new position.");
                System.out.println("Your target you want copy must be in the System!");
                System.out.println("//copy-{{name}}-{{newName/randomName}}-{{x}}-{{y}}");
                System.out.println("//copy-image1-image2-100-100");
                System.out.println("//copy-image1-random-100-100");
                System.out.println("-------------------------------------");
            }
            if(commands[0].equals("import")){
                System.out.println("Loading... Importing image...");
                ArrayList<ArrayList<String>> dataLists = new ArrayList<>();
                dataLists.add(TransformToTemplate.convertToDataList(Integer.parseInt(commands[2]),Integer.parseInt( commands[3]), FileImportString.getFile(commands[1])));
                JMain.addToRenderer(dataLists);
            }
            if (commands[0].equals("copy")){
                ArrayList<String> object = JMain.getObjectByName(commands[1]);
                Integer a = 0;
                if (a.equals(object.size())){
                    System.out.println(commands[1] + " is not in the system!");
                }else{
                    ArrayList<ArrayList<String>> dataLists = new ArrayList<>();
                    Boolean stop2 = false;
                    ArrayList<String> rawData = FileImportString.getCachedData(commands[1]);
                    if (commands[2].equals("random")){
                        rawData.set(0, rawData.get(0) + "-" + Math.random());
                    }else{
                       ArrayList<String> object2 = JMain.getObjectByName(commands[2]);
                       if (object2.size() == 0){
                           rawData.set(0, commands[2]);
                       }else{
                           System.out.println("Your newName is already in the system. Please rename it!");
                           stop2 = true;
                       }
                    }
                    if (!(stop2)) {
                        dataLists.add(TransformToTemplate.convertToDataList(Integer.parseInt(commands[3]), Integer.parseInt(commands[4]), rawData));
                        JMain.addToRenderer(dataLists);
                        System.out.println("Object copied. It's name is: " + rawData.get(0));
                    }
                }
            }
        }
    }
}

package util;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by tryhanuch on 07.11.16.
 */
public class TextUtil {

    public static ArrayList<String> readFromFile(String entityName){
        File dataFile = new File(getPath() + entityName);
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataFile));
            String s;
            while((s = br.readLine()) != null){
                lines.add(s);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void writeToFile(String entityName, String line){
        File dataFile = new File(getPath() + entityName);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile, true));
            bw.write(line);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateInFile(String entityName, String line){

    }

    public static void deleteFromFile(String entityName, int id){

    }


    public static int getLastId(String entityName){

        return 0;
    }

    public static String getSeparator() {
        return ":-:";
    }
    private static String getPath(){

        return null;
    }




}

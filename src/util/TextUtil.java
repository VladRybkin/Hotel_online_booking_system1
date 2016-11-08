package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile, true))){
            bw.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateInFile(String entityName, String line){

    }

    public static void deleteFromFile(String entityName, int id){

    }


    public static long getLastId(String entityName){
//      TODO переделать чтоб айди был понастоящему уникальным для каждого отдельного класса
        return new Random().nextLong();
    }

    public static String getSeparator() {
        return ":-:";
    }

    public static String getHotelFileName() {
        return "Hotel";
    }

    public static String getRoomFileName() {
        return "Room";
    }

    public static String getUserFileName() {
        return "User";
    }

    private static String getPath(){
        return "db/";
    }




}

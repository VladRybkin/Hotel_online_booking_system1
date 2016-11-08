package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by tryhanuch on 07.11.16.
 */
public class TextUtil {

    public static final String HOTEL_FILE_NAME = "Hotel";
    public static final String ROOM_FILE_NAME = "Room";
    public static final String USER_FILE_NAME = "User";

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

    public static void deleteFromFile(String entityName, long id){

    }


    public static long getLastId(String entityName){
//      TODO переделать чтоб айди был понастоящему уникальным для каждого отдельного класса
        return new Random().nextLong();
    }

    public static String getSeparator() {
        return ":-:";
    }

    private static String getPath(){

        String path = "db\\db";

//        new File("C:\\Directory2\\Sub2\\Sub-Sub2").mkdirs()
        return "";
    }




}

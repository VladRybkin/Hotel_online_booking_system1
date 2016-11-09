package util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TextUtil {

    public static final String HOTEL_FILE_NAME = "Hotel";
    public static final String ROOM_FILE_NAME = "Room";
    public static final String USER_FILE_NAME = "User";
    public static final String DB_FIELDS_SEPARATOR = ":-:";

    public static ArrayList<String> readFromFile(String entityName){
        File dataFile = new File(getPath() + entityName);
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))){
            String s;
            while((s = br.readLine()) != null){
                lines.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void writeToFile(String entityName, String line){
        File dataFile = new File(getPath() + entityName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile, true))) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateInFile(String entityName, String line) {
        List<String> list;
        list = TextUtil.readFromFile(entityName);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            String[] res1 = s.split(DB_FIELDS_SEPARATOR);
            String[] res2 = line.split(DB_FIELDS_SEPARATOR);
            if (res1[0].equals(res2[0])) {
                try {
                    TextUtil.deleteFromFile(entityName, Integer.parseInt(res1[0]));
                    TextUtil.writeToFile(entityName, line);
                } catch (ClassCastException e) {
                    System.out.println("There is classcast exception into updateFile()");
                    e.printStackTrace();
                }

            }
        }
    }

    public static void deleteFromFile(String entityName, long id){

        ArrayList<String> fileContent = new ArrayList<>();
        Path path = Paths.get(getPath() + entityName);
        try {
            fileContent.addAll(Files.readAllLines(path, StandardCharsets.UTF_8));
            Iterator iterator = fileContent.iterator();
            while (iterator.hasNext()) {
                String line = (String) iterator.next();
                String[] fields = line.split(DB_FIELDS_SEPARATOR);
                if (Long.parseLong(fields[0]) == id) {
                    iterator.remove();
                    break;
                }
            }
            Files.write(path, fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public static long getLastId(String entityName){
//      TODO переделать чтоб айди был понастоящему уникальным для каждого отдельного класса
        return new Random().nextLong();
    }

    private static String getPath(){
        return "";
    }




}

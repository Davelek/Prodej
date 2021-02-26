package services;

import java.io.*;
import java.util.ArrayList;

public class MyFile {

    public static ArrayList<String[]> ReadFile(String filename) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\res\\" + filename;
        File file = new File(path);
        ArrayList<String[]> rows = new ArrayList<String[]>();

        if (file.isFile()){
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String row;
            while ((row = reader.readLine()) != null){
                String[] splitRow = row.split(",");
                rows.add(splitRow);
            }
        }

        return rows;
    }

    public static void saveFile(String filename, String items) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\res\\" + filename;
        File file = new File(path);
        ArrayList<String[]> rows = new ArrayList<String[]>();


            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
                writer.newLine();
                writer.write(items);


            writer.close();



        }



}

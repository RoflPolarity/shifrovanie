package lab2;

import java.io.*;
import java.util.*;

public class Gamma {

    static GSPCH gspch = new GSPCH();
    static String username = System.getProperty("user.name");
    static File key = new File("/home/"+username+"/key.txt");
    static File encodedFile = new File("/home/"+username+"/coded.txt");
    static void crypt(String file){
        List<Character> lst = new ArrayList<>();
        int ch;
        try (FileReader reader = new FileReader(file)) {
            while ((ch = reader.read()) != -1) {
                lst.add((char) ch);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int[] arr = gspch.getRand(lst.size());
        String num = "";
        for (int j : arr) num += String.valueOf(j);
        try {
            FileWriter fw = new FileWriter(key,false);
            fw.append(num);
            fw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
        char[] coded = new char[lst.size()];
        for (int i = 0; i < lst.size(); i++) {coded[i] = (char)(lst.get(i)^num.charAt(i));}
        try {
            if (!encodedFile.exists()) encodedFile.createNewFile();
            FileWriter fw = new FileWriter(encodedFile);
            for (char c : coded){
                fw.append(c);
            }
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
class decoder{

    static GSPCH gspch = new GSPCH();
    static String username = System.getProperty("user.name");
    static File encodedFile;
    static File key;
    static File decodedFile = new File("/home/"+username+"/decoded.txt");

    decoder(String file, String key){
        encodedFile = new File(file);
        this.key = new File(key);
    }
    private String getGamma() throws IOException {
        FileReader fr = new FileReader(key);
        BufferedReader bufferedReader = new BufferedReader(fr);
        return bufferedReader.readLine();
    }
    public void decode() throws IOException {
        List<Character> lst = new ArrayList<>();
        int ch;
        try (FileReader reader = new FileReader(encodedFile)) {
            while ((ch = reader.read()) != -1) {
                lst.add((char) ch);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String num = getGamma();
        char[] coded = new char[lst.size()];
        for (int i = 0; i < lst.size(); i++) {coded[i] = (char)(lst.get(i)^num.charAt(i));}
        try {
            if (!decodedFile.exists()) decodedFile.createNewFile();
            FileWriter fw = new FileWriter(decodedFile);
            for (char c : coded){
                System.out.print(c);
                fw.append(c);
            }
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

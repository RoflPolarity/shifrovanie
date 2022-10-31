package lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gamma {

    static GSPCH gspch;
    static Scanner scanner = new Scanner(System.in);
    static String username = System.getProperty("user.name");
    static File key = new File("C:\\Users\\" + username +"\\Desktop\\gamma.key");

    static void saveParam() throws IOException {
        gspch = new GSPCH();
        FileWriter fw = new FileWriter(key);
        if (!key.exists()){
            key.createNewFile();
            fw.write(String.valueOf(gspch.a));
            fw.write(String.valueOf(gspch.c0));
            fw.write(String.valueOf(gspch.b));

        }else{
            System.out.println("Перезаписать файл? Y/N");
            if (scanner.next().equals("Y")){
                fw.write(String.valueOf(gspch.a));
                fw.write(String.valueOf(gspch.c0));
                fw.write(String.valueOf(gspch.b));
            }else System.out.println("Операция отменена.");
        }
    }
    public static void crypt(String file) throws IOException {
        File text = new File(file);
        FileReader fr = new FileReader(text);
        List<String> lst = new ArrayList<>();
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (!br.readLine().equals("")){
            lst.add(line);
            br.readLine();
        }
        System.out.println(lst);
    }
}

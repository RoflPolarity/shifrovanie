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
    static File text = new File("C:\\Users\\RoflPolarity\\IdeaProjects\\shifrovanie\\src\\main\\java\\lab2\\text.txt");

    public static void main(String[] args) throws IOException {

        while (true){
            print();
            if (scanner.nextInt()==1) saveParam();
            else if(scanner.nextInt()==2)crypt();
            else if (scanner.nextInt()==3);
            else if (scanner.nextInt()==4) break;
        }
    }


    public static void print(){
        System.out.println("1.Сгенерировать параметры для линейного конгруэнтного ГПСЧ и сохранить их в файл.\n"+
                "2.Выполнить шифрование гаммированием выбранного файла с использованием файла-ключа"+
                "3."+
                "4.");
    }
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
    static void crypt() throws IOException {
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

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class encode {
    File file;
    encode() throws IOException {
        List<String> lst = read();
        System.out.println(lst);
    }


    private List<String> read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\RoflPolarity\\IdeaProjects\\shifrovanie\\src\\main\\java\\mumu")));
        List<String> lst = new ArrayList<>();
        String line;
        while ((line = reader.readLine())!=null){
            lst.add(line);
        }
        return lst;
    }

    public static void main(String[] args) throws IOException {
        new encode();
    }
}

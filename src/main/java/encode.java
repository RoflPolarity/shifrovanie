import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class encode {
    File file;
    encode() throws IOException {
        read();
    }
    private List<String> read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lst = new ArrayList<>();
        String line;
        while ((line = reader.readLine())!=null){
            lst.add(line);
        }
        return lst;
    }
}

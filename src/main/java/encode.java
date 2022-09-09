import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class encode {
    File file, encodingFile;
    int bias,letters;
    encode(final int bias, final int letters, String pathFile) throws IOException {
        this.bias = bias;
        this.letters = letters;
        file = new File(pathFile);
        encodingFile = new File("Coded_Mumu.txt");
        System.out.println(encrypt(read(),"Привет"));
        save();
    }

    public void save() throws IOException {
        if (!encodingFile.exists()){
            encodingFile.createNewFile();
        }


    }

    public String encrypt(String text ,String key) {
        String encrypt = "";
        final int keyLen = key.length();
        for (int i = 0, len = text.length(); i < len; i++) {
            encrypt += (char) (((text.charAt(i) + key.charAt(i % keyLen) - 2 * this.bias) % this.letters) + this.bias);
        }
        return encrypt;
    }

    public String decrypt(String cipher, final String key) {
        String decrypt = "";
        final int keyLen = key.length();
        for (int i = 0, len = cipher.length(); i < len; i++) {
            decrypt += (char) (((cipher.charAt(i) - key.charAt(i % keyLen) + this.letters) % this.letters) + this.bias);
        }
        return decrypt;
    }

    private String read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\RoflPolarity\\IdeaProjects\\shifrovanie\\src\\main\\java\\mumu")));
        List<String> lst = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        String line;
        while ((line = reader.readLine())!=null){
            res.append(line);
        }
        return res.toString();
    }

    public static void main(String[] args) throws IOException {
        new encode(1072,33, "C:\\Users\\RoflPolarity\\IdeaProjects\\shifrovanie\\src\\main\\java\\mumu.txt");
    }
}
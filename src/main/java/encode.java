import java.io.*;
import java.nio.charset.StandardCharsets;

public class encode {
    File file, encodingFile;
    String alphabet = "ÀÁÂÃÄÅ¨ÈÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßABCDEFGHIJKLMNOPQRSTUVWXYZ 1234567890\"'?;,.:-!";
    int [] numsOfText;
    int [] numsOfKey;
    String text, codedString, key;
    encode(String pathFile, String key) throws IOException {

        alphabet = new String(alphabet.getBytes(),"windows-1251");
        file = new File(pathFile);
        this.text = read(file);
        numsOfText = new int[text.length()];
        numsOfKey = new int[key.length()];
        this.key = key;
        this.codedString = code();
    }


    private String code(){
        String username = System.getProperty("user.name");
        this.encodingFile = new File("C:\\Users\\" + username +"\\Desktop\\coded.txt");
        for (int i = 0; i < text.length(); i++) {
            numsOfText[i] = finder(text.charAt(i));
        }
        for (int i = 0; i < key.length(); i++) {
            numsOfKey[i] = finder(key.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for (int i = 0; i < numsOfText.length; i++) {
            if (j==key.length()) j = 0;
            sb.append(alphabet.charAt((numsOfText[i]+numsOfKey[j])% alphabet.length()));
            j++;
        }
        return new String(sb.toString().getBytes(),StandardCharsets.UTF_8);
    }

    private int finder(char c){
        try {
            return text.indexOf(c);
        }catch (Exception e){
            return text.toLowerCase().indexOf(c);
        }
    }


    public void save() throws IOException {
        if (!encodingFile.exists()){
            encodingFile.createNewFile();
        }
        FileWriter fw = new FileWriter(encodingFile);
        fw.write(codedString);
        fw.flush();
        fw.close();
    }

    private String read(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder res = new StringBuilder();
        String line;
        while ((line = reader.readLine())!=null){
            res.append(line);
        }
        return new String(res.toString().getBytes(), StandardCharsets.UTF_8);
    }
}
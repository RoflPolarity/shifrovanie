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
            if (finder(text.charAt(i))==-1){
                numsOfText[i] = -1;
            }else{
                numsOfText[i] = finder(text.charAt(i));
            }
        }
        for (int i = 0; i < key.length(); i++) {
            if (finder(key.charAt(i))==-1){
                numsOfKey[i] = -1;
            }else{
                numsOfKey[i] = finder(key.charAt(i));
            }
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
        for (int i = 0; i < alphabet.length(); i++) {
            if (alphabet.charAt(i)==c){
                return i+1;
            }else{
                if (alphabet.toLowerCase().charAt(i)==c){
                    return i+1;
                }
            }
        }
        return alphabet.indexOf('?');
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

    public static void main(String[] args) throws IOException {
    }
}
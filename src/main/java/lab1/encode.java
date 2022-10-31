package lab1;

import java.io.*;

public class encode {
    File file, encodingFile;
    int [] numsOfText;
    int [] numsOfKey;
    String language;
    String text, codedString, key;
    encode(String pathFile, String key, String language) throws IOException {
        file = new File(pathFile);
        this.text = read(file);
        numsOfText = new int[text.length()];
        numsOfKey = new int[key.length()];
        this.language = language;
        this.key = key;
        this.codedString = code();
        System.out.println(key);
        save();
    }



    private String code() {
        String username = System.getProperty("user.name");
        this.encodingFile = new File("C:\\Users\\" + username +"\\Desktop\\coded.txt");
        int langNum = language.equals("Русский") ? 33 : 26;
        char firstChar = langNum==26 ? 'A' : 'А';
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for (int i = 0; i < numsOfText.length; ++i) {
            sb.append((char)(((text.charAt(i) + key.charAt(i%key.length())) % langNum) + firstChar));
            j++;
        }
        return sb.toString();
    }

    public void save() throws IOException {
        if (!encodingFile.exists()){
            encodingFile.createNewFile();
            System.out.println("Saved from new file");
            FileWriter fw = new FileWriter(encodingFile);
            fw.write(codedString);
            fw.flush();
            fw.close();
        }else {
            System.out.println("Saved from exists file");
            FileWriter fw = new FileWriter(encodingFile);
            fw.write(codedString);
            fw.flush();
            fw.close();
        }
    }

    private String read(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder res = new StringBuilder();
        String line;
        while ((line = reader.readLine())!=null){
            res.append(line);
        }
        return res.toString();
    }
}
package lab1;

import java.io.*;
import java.util.ArrayList;

public class decode {
    static class Letter{
        String alphabet;
        int num;
        char letter;
        Letter(String alphabet, int num, char letter){
            this.alphabet = alphabet;
            this.num = num;
            this.letter = letter;
        }
        @Override
        public String toString() {
            return letter+" ";
        }
    }
    private final String RussianAlphabetLarge = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ";
    private final String EnglishAlphabetLarge = "QWRERTYUIOPASDFGHJKLZXCVBNM";
    private final String RussianAlphabetSmall = RussianAlphabetLarge.toLowerCase();
    private final String EnglishAlphabetSmall = EnglishAlphabetLarge.toLowerCase();
    File file, encodingFile;
    ArrayList<String> text, coded;
    String key;
    decode(String pathFile, String key) throws IOException {
        file = new File(pathFile);
        this.text = read(file);
        this.key = key;
        this.coded= code();
        System.out.println(coded);
        save();
    }

    private Letter[] find(String str){
        Letter[] letters = new Letter[str.length()];
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < RussianAlphabetLarge.length(); j++){
                if (str.charAt(i)==RussianAlphabetLarge.charAt(j)){
                    letters[i] = new Letter(RussianAlphabetLarge, j, RussianAlphabetLarge.charAt(j));
                    break;
                }
            }
            if (letters[i]!=null) continue;
            for (int j = 0; j < RussianAlphabetSmall.length(); j++){
                if (str.charAt(i)==RussianAlphabetSmall.charAt(j)){
                    letters[i] = new Letter(RussianAlphabetSmall, j, RussianAlphabetSmall.charAt(j));
                    break;
                }
            }
            if (letters[i]!=null) continue;
            for (int j = 0; j < EnglishAlphabetLarge.length(); j++){
                if (str.charAt(i)==EnglishAlphabetLarge.charAt(j)){
                    letters[i] = new Letter(EnglishAlphabetLarge, j, EnglishAlphabetLarge.charAt(j));
                    break;
                }
            }
            if (letters[i]!=null) continue;
            for (int j = 0; j < EnglishAlphabetSmall.length(); j++){
                if (str.charAt(i)==EnglishAlphabetSmall.charAt(j)){
                    letters[i] = new Letter(EnglishAlphabetSmall, j, EnglishAlphabetSmall.charAt(j));
                    break;
                }
            }
            if (letters[i]!=null) continue;
            String other = "!@#$%^&*()_+\"[],./;:'\\=-<> 1234567890{}\t";
            for (int j = 0; j < other.length(); j++){
                if (str.charAt(i)== other.charAt(j)){
                    letters[i] = new Letter(other, j, other.charAt(j));
                    break;
                }
            }
        }
        return letters;
    }
    private ArrayList<String> code() {
        ArrayList<String> res = new ArrayList<>();
        String username = System.getProperty("user.name");
        this.encodingFile = new File("/home/"+username+"/Рабочий стол/DECODED.txt");
        Letter[] lettersOfKey = find(this.key);
        int keyCounter = 0;
        for (String s : this.text) {
            Letter[] lettersOfString = find(s);
            StringBuilder sb = new StringBuilder();
            for (Letter letter : lettersOfString) {
                if (keyCounter == lettersOfKey.length) keyCounter = 0;
                sb.append(letter.alphabet.charAt((((letter.num - lettersOfKey[keyCounter].num) % letter.alphabet.length()) + letter.alphabet.length()) % letter.alphabet.length()));
                keyCounter++;
            }
            res.add(sb.append('\n').toString());
        }
        return res;
    }

    public void save() throws IOException {
        if (!encodingFile.exists()){
            encodingFile.createNewFile();
            System.out.println("Saved from new file");
            FileWriter fw = new FileWriter(encodingFile);
            for (String s : coded) {
                fw.write(s);
                fw.flush();
            }
            fw.close();
        }else {
            System.out.println("Saved from exists file");
            FileWriter fw = new FileWriter(encodingFile);
            for (String s : coded) {
                fw.write(s);
                fw.flush();
            }
            fw.close();
        }
    }

    private ArrayList<String> read(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> lst= new ArrayList<>();
        String line;
        while ((line = reader.readLine())!=null){
            lst.add(line);
        }
        return lst;
    }
}